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
@WebServlet("/category")
public class CategoryController extends HttpServlet {
	
	public CRUDService<Category> c = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", c.getAll());
		req.setAttribute("view", "/template/categoryList.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
