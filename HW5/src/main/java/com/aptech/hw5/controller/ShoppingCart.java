package com.aptech.hw5.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aptech.hw5.model.Product;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.ProductServiceImpl;

@MultipartConfig()
@WebServlet("/shoppingcart")
public class ShoppingCart extends HttpServlet {
	CRUDService<Product> pro = new ProductServiceImpl();
//	static List<Product> list = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String id = req.getParameter("id");
		String quantity = req.getParameter("quantity");
		if(quantity == null) {
			quantity = "1";
		}
		req.setAttribute("view", "/template/shoppingCart.jsp");
		req.setAttribute("quantity", Integer.valueOf(quantity));
		//req.setAttribute("product", pro.getById(id));
//		if(id != null) {
//			list.add(pro.getById(id));
//		}
		if(ss.getAttribute("shoppingcart") != null) {
			req.setAttribute("products",(List<Product>)ss.getAttribute("shoppingcart"));
		}
		
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setAttribute("quantity", req.getParameter("quantity"));
		doGet(req, resp);
	}

}
