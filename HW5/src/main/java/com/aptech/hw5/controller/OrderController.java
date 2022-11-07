package com.aptech.hw5.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aptech.hw5.model.Order;
import com.aptech.hw5.model.OrderDetail;
import com.aptech.hw5.model.User;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.OrderDetailService;
import com.aptech.hw5.service.OrderService;
import com.aptech.hw5.service.impl.OrderDetailServiceImpl;
import com.aptech.hw5.service.impl.OrderServiceImpl;

@WebServlet("/order")
public class OrderController extends HttpServlet {
	OrderDetailService od = new OrderDetailServiceImpl();
	CRUDService<Order> o = new OrderServiceImpl();
	OrderService sv = new OrderServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action == null) {
			action = "";
		}
		HttpSession ss = req.getSession();
		if(ss.getAttribute("userLogin") == null) {
			resp.sendRedirect("./login");
		}else {
			switch(action) {
			case "history" :
				showHistory(req,resp);
				break;
			case "views":
				showView(req,resp);
				break;
			default:
				showDataSuccess(req,resp);
				break;
			}
			
		}
	}

	private void showView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		req.setAttribute("detail", od.getAllByOrderID(Integer.valueOf(id)));
		req.setAttribute("view", "/template/detailHistory.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
		
	}

	private void showHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		User u = (User)ss.getAttribute("userLogin");
		req.setAttribute("orders", sv.getAll(u));
		System.out.println(o.getAll());
		req.setAttribute("view", "/template/orderHistory.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
		
	}

	private void showDataSuccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		if(ss.getAttribute("orderDt") != null && ss.getAttribute("total") != null) {
			req.setAttribute("orderDt",ss.getAttribute("orderDt"));
			req.setAttribute("total", ss.getAttribute("total"));
			req.setAttribute("view", "/template/orderSuccess.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}
		
	}
	

}
