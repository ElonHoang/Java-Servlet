package com.aptech.hw5.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aptech.hw5.model.Order;
import com.aptech.hw5.model.OrderDetail;
import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.ShoppingCart;
import com.aptech.hw5.model.User;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.ShoppingCartService;
import com.aptech.hw5.service.impl.OrderDetailServiceImpl;
import com.aptech.hw5.service.impl.OrderServiceImpl;
import com.aptech.hw5.service.impl.ProductServiceImpl;
import com.aptech.hw5.service.impl.ShoppingCartServiceImpl;

@MultipartConfig()
@WebServlet("/shoppingcart")
public class ShoppingCartController extends HttpServlet {
	CRUDService<Product> pro = new ProductServiceImpl();
	ShoppingCartService service = new ShoppingCartServiceImpl();
	CRUDService<Order> order = new OrderServiceImpl();
	CRUDService<OrderDetail> odDetail = new OrderDetailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession ss = req.getSession();
		if (action == null) {
			action = "";
		}

		if (ss.getAttribute("userLogin") == null) {
			resp.sendRedirect("./login");
		} else {
			switch (action) {
			case "deleteCart":
				deleteCart(req, resp);
				break;
			case "plus":
				plusQuantity(req, resp);
				break;
			case "minus":
				minusQuantity(req, resp);
				break;
			default:
				getAllCart(req, resp);
				break;
			}

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		List<OrderDetail> orderDt = new ArrayList<>();
		String[] check = req.getParameterValues("check");
		User u = (User) ss.getAttribute("userLogin");
		if (check == null) {
			resp.sendRedirect("./shoppingcart");
		} else {
			List<ShoppingCart> carts = service.getAllCart(u);

			
			u.setPass("");
			double sum = service.getTotalCartPrice(carts, check);
			Order o = new Order();
			o.setUser(u);
			o.setDatetime(LocalDateTime.now());
			o.setTotal_price((int) sum);
			for (String str : check) {
				OrderDetail od = new OrderDetail();
				Product p = new Product();
				p.setId(str);
				p.setName(pro.getById(str).getName());
				od.setOrder(o);
				od.setProduct(p);
				Product prod = pro.getById(str);
				od.setQuantity(service.getQuantityByProductID(str));
				od.setPrice((int) prod.getPrice());
				orderDt.add(od);
				service.deleteCartByProductID(str);
			}
			order.add(o);
			for(OrderDetail od : orderDt) {
				odDetail.add(od);
			}
			ss.setAttribute("orderDt", orderDt);
			ss.setAttribute("total", sum);
			resp.sendRedirect("./order");

		}

	}

	private void minusQuantity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		int quantity = service.getQuantityByProductID(id);
		req.setAttribute("shoppingcart", service.updateQuantityInCart(id, quantity - 1));
		resp.sendRedirect("./shoppingcart");

	}

	private void plusQuantity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		int quantity = service.getQuantityByProductID(id);
		req.setAttribute("shoppingcart", service.updateQuantityInCart(id, quantity + 1));
		resp.sendRedirect("./shoppingcart");
	}

	private void getAllCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		User  u = (User)ss.getAttribute("userLogin");
		System.out.println((User)ss.getAttribute("userLogin"));
		List<ShoppingCart> carts = service.getAllCart(u);
		req.setAttribute("view", "/template/shoppingCart.jsp");
		req.setAttribute("shoppingCart", carts);
		req.setAttribute("total_price", service.getTotalCartPrice(carts, null));
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	private void deleteCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String[] check = req.getParameterValues("check");
		if (id != null) {
			service.deleteCart(Integer.valueOf(id));
			resp.sendRedirect("./shoppingcart");
		}

	}

}
