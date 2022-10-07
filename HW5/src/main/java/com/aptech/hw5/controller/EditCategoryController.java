package com.aptech.hw5.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aptech.hw5.model.Category;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.CategoryServiceImpl;

@WebServlet("/editCate")
public class EditCategoryController extends HttpServlet {
	CRUDService<Category> category = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		req.setAttribute("category", category.getById(id));
		req.setAttribute("view", "/template/editCategory.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String id = req.getParameter("id");
			System.out.println(id);
			String name = req.getParameter("name");
			String description = req.getParameter("description");
			Category c = new Category(Integer.valueOf(id), name , description);
			category.update(c);
			resp.sendRedirect("./category");			
		
	}

}
