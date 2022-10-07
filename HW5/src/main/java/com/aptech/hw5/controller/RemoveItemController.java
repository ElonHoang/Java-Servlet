package com.aptech.hw5.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aptech.hw5.model.Product;
@WebServlet("/remove")
public class RemoveItemController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String id = req.getParameter("id");
		if(id != null) {
			List<Product> list = (List<Product>)ss.getAttribute("shoppingcart");
			System.out.println(list);
			list.removeIf(t->t.getId().equals(id));
			System.out.println(list);
			req.setAttribute("shoppingcart", (List<Product>)ss.getAttribute("shoppingcart"));
		}	
		req.setAttribute("view", "/template/shoppingCart.jsp");
		//req.getRequestDispatcher("layout.jsp").forward(req, resp);
		resp.sendRedirect("./shoppingcart");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
