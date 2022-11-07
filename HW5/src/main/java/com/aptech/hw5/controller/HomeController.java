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

import com.aptech.hw5.model.Category;
import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.ShoppingCart;
import com.aptech.hw5.model.User;
import com.aptech.hw5.repo.ProductRepo;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.ShoppingCartService;
import com.aptech.hw5.service.impl.CategoryServiceImpl;
import com.aptech.hw5.service.impl.ProductServiceImpl;
import com.aptech.hw5.service.impl.ShoppingCartServiceImpl;

@MultipartConfig()
@WebServlet("/home")
public class HomeController extends HttpServlet {
	CRUDService<Product> pro = new ProductServiceImpl();
	ProductRepo repo = new ProductRepo();
	CRUDService<Category> cate = new CategoryServiceImpl();
	ShoppingCartService sc = new ShoppingCartServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action == null) {
			action = "";
		}
		HttpSession session = req.getSession();
		if (session.getAttribute("userLogin") == null) {
			resp.sendRedirect("./login");
		} else {
			switch (action) {
			case "addCart":
				addCart(req, resp);
				break;
			default:
				homePage(req, resp);
				break;
			}

		}
	}

	private void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("index");
		int sum = repo.countProducts();
		int endPage = sum / 6;
		if (sum / 6 != 0) {
			endPage++;
		}
		if (page == null) {
			page = "1";
		}
		req.setAttribute("endPage", endPage);
		req.setAttribute("currentPage", Integer.valueOf(page));
		req.setAttribute("products", repo.pagingProducts(Integer.valueOf(page), 6));
		req.setAttribute("view", "/template/home.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	private void addCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		if (id != null) {
			User s = (User) session.getAttribute("userLogin");
			Product p = pro.getById(id);
			if(sc.getProductIDByProductID(id) != null) {
					sc.updateQuantityInCart(id,sc.getQuantityByProductID(id) + 1);
				}else {
					sc.addCart(p, s, 1);
				}
				
			}
			
		resp.sendRedirect("./home");

	}

}
