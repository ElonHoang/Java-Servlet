package com.aptech.hw5.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.aptech.hw5.model.Category;
import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.Validate;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.CategoryServiceImpl;

@WebServlet("/addCate")
public class AddCategoryController extends HttpServlet {
	CRUDService<Category> category = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("view", "/template/addCategory.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			req.setAttribute("view", "/template/addCategory.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		} else {
			Category c = new Category();
			c.setName(name);
			c.setDescription(description);
			category.add(c);
			resp.sendRedirect("./product");
		}

	}

}
