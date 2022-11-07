package com.aptech.hw5.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aptech.hw5.model.Category;
import com.aptech.hw5.model.Validate;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.impl.CategoryServiceImpl;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CRUDService<Category> category = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if (action == null) {
			action = "";
		}
		if (session.getAttribute("userLogin") == null) {
			resp.sendRedirect("./login");
		} else {
			switch (action) {
			case "add":
				addCategoryForm(req, resp);
				break;
			case "edit":
				editCategoryForm(req, resp);
				break;
			case "delete":
				deleteCategoryForm(req, resp);
				break;
			default:
				categoryList(req, resp);
				break;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
		case "add":
			addCategory(req, resp);
			break;
		case "edit":
			editCategory(req, resp);
			break;
		case "delete":
			deleteCategory(req, resp);
			break;
		default:
			categoryList(req, resp);
			break;
		}
	}

	private void categoryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", category.getAll());
		req.setAttribute("view", "/template/categoryList.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	private void deleteCategoryForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		req.setAttribute("category", category.getById(id));
		req.setAttribute("view", "/template/deleteCategory.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	private void editCategoryForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		req.setAttribute("category", category.getById(id));
		req.setAttribute("view", "/template/addCategory.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	private void addCategoryForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("view", "/template/saveCategory.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String id = req.getParameter("id");
		Category c = category.getById(id);
		category.delete(c);
		if(category.delete(c) == null) {
			req.setAttribute("msg", "Cannot delete Category with id = " + id );
			req.setAttribute("view", "/template/categoryList.jsp");
			req.setAttribute("categories", category.getAll());
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("./category");
		}
			
		

	}

	private void editCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		System.out.println(id);
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Category c = new Category(Integer.valueOf(id), name, description);
		category.update(c);
		System.out.println(c);
		resp.sendRedirect("./category");

	}

	private void addCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Map<String, String> errors = new HashMap<String, String>();
		boolean flag = true;
		if (!Validate.isName(name)) {
			errors.put("name", "Name is not valid !");
			flag = false;
		}
		if (flag == false) {
			req.setAttribute("name", name);
			req.setAttribute("errors", errors);
			req.setAttribute("view", "/template/saveCategory.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		} else {
			Category c = new Category();
			c.setName(name);
			c.setDescription(description);
			category.add(c);
			resp.sendRedirect("./category");
		}
	}
}
