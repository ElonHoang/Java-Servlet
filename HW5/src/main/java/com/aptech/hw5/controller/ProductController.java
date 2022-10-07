package com.aptech.hw5.controller;

import java.io.IOException;

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
@WebServlet("/product")
public class ProductController extends HttpServlet {
	CRUDService<Product> pro = new ProductServiceImpl();
	ProductRepo repo = new ProductRepo();
	CRUDService<Category> cate = new CategoryServiceImpl();
	String global = "";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("index");
		HttpSession session = req.getSession();
		if (page == null) {
			page = "1";
		}
		if (session.getAttribute("userLogin") == null) {
			resp.sendRedirect("./login");
		}else {
			int endPage = 0;
			if (global != null) {
				int sum = repo.countSearchByName(global);
				endPage = sum / 4;
				if (sum / 4 != 0) {
					endPage++;
				}
				req.setAttribute("products", repo.searchByName(global, Integer.valueOf(page), 4));
			} else {
				int sum = repo.countProducts();
				endPage = sum / 4;
				if (sum / 4 != 0) {
					endPage++;
				}
			}
			
			req.setAttribute("txt", global);
			req.setAttribute("products", repo.pagingProducts(Integer.valueOf(page), 4));
			req.setAttribute("endPage", endPage);
			req.setAttribute("currentPage", Integer.valueOf(page));
			req.setAttribute("view", "/template/productList.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search = req.getParameter("txtSearch");
		if (search == null || search.length() == 0) {
			global = "";
			doGet(req, resp);
		} else {
			String page = req.getParameter("index");
			int sum = repo.countSearchByName(search);
			int endPage = sum / 4;
			if (sum / 4 != 0) {
				endPage++;
			}
			if (page == null) {
				page = "1";
			}
			global = search;
			req.setAttribute("txt", search);
			req.setAttribute("products", repo.searchByName(search, Integer.valueOf(page), 4));
			req.setAttribute("endPage", endPage);
			req.setAttribute("currentPage", Integer.valueOf(page));
			req.setAttribute("view", "/template/productList.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}
	}

}
