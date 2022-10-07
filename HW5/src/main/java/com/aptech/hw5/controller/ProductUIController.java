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
import com.aptech.hw5.repo.ProductRepo;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.CategoryServiceImpl;
import com.aptech.hw5.service.ProductServiceImpl;
@MultipartConfig()
@WebServlet("/productUI")
public class ProductUIController extends HttpServlet {
	CRUDService<Product> pro = new ProductServiceImpl();
	ProductRepo repo = new ProductRepo();
	CRUDService<Category> cate = new CategoryServiceImpl();
	List<Product> list = new ArrayList<>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		String page = req.getParameter("index");
		if (session.getAttribute("userLogin") == null) {
			resp.sendRedirect("./login");
		} else {
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
			req.setAttribute("view", "/template/productUI.jsp");
			if(id != null) {
				list.add(pro.getById(id));
			}		
			session.setAttribute("shoppingcart",list);
			
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int quantity = Integer.valueOf(String.valueOf(req.getParameter("quantity")));
		System.out.println(quantity);
	}

}
