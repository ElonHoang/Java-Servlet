package com.aptech.hw5.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aptech.hw5.model.Product;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.ProductServiceImpl;
@MultipartConfig()
@WebServlet("/delete")
public class DeleteProductController extends HttpServlet {
CRUDService<Product> product = new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		req.setAttribute("products", product.getById(id));
		req.setAttribute("view", "/template/deleteProduct.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Product p = product.getById(id);
		product.delete(p);
		resp.sendRedirect("./product");
	}

}
