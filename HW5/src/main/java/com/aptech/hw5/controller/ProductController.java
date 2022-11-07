package com.aptech.hw5.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.aptech.hw5.model.Action;
import com.aptech.hw5.model.Category;
import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.Validate;
import com.aptech.hw5.repo.ProductRepo;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.impl.CategoryServiceImpl;
import com.aptech.hw5.service.impl.ProductServiceImpl;

@MultipartConfig()
@WebServlet("/product")
public class ProductController extends HttpServlet {
	CRUDService<Product> product = new ProductServiceImpl();
	CRUDService<Category> category = new CategoryServiceImpl();
	ProductRepo repo = new ProductRepo();
	String global = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		if (session.getAttribute("userLogin") == null) {
			resp.sendRedirect("./login");
		} else {
			switch (action) {
			case "add":
				addFormProduct(req, resp);
				break;
			case "edit":
				editFormProduct(req, resp);
				break;
			case "delete":
				deleteFormProduct(req, resp);
				break;
			default:
				productList(req, resp);
				break;

			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
		case "add":
			addProduct(req, resp);
			break;
		case "edit":
			editProduct(req, resp);
			break;
		case "search":
			searchByName(req, resp);
			break;
		case "delete":
			deleteProduct(req, resp);
			break;
		default:
			productList(req, resp);
			break;

		}

	}

	public void addFormProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("category", category.getAll());
		req.setAttribute("view", "/template/saveProduct.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String cate = req.getParameter("option");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String description = req.getParameter("description");
		Part part = req.getPart("photo");
		Map<String, String> errors = new HashMap<String, String>();
		boolean flag = true;
		if (!Validate.isName(name)) {
			errors.put("name", "Name is not valid !");
			flag = false;
		}
		if (!Validate.isNumber(price)) {
			errors.put("price", "Price is not valid !");
			flag = false;
		}
		if (!Validate.isId(id)) {
			errors.put("id", "Id is not valid !");
			flag = false;
		}
		if (flag == false) {
			req.setAttribute("category", category.getAll());
			req.setAttribute("id", id);
			req.setAttribute("name", name);
			req.setAttribute("price", price);
			req.setAttribute("errors", errors);
			req.setAttribute("view", "/template/saveProduct.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		} else {
			String realPath = req.getServletContext().getRealPath("/uploads");
			System.out.println(realPath);
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			if (fileName == null || fileName.length() == 0) {
				fileName = "NULL";
			}
			System.out.println(fileName);
			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			part.write(realPath + "/" + fileName);
			if (description == null || description.length() == 0) {
				description = "NULL";
			}
			Category c = new Category();
			c.setId(Integer.valueOf(cate));
			Product p = new Product(id, c, name, Double.valueOf(price), fileName, description);
			product.add(p);
			resp.sendRedirect("./product");
		}

	}

	public void productList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String page = req.getParameter("index");

		if (page == null) {
			page = "1";
		}

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

	public void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search = req.getParameter("txtSearch");
		if (search == null || search.length() == 0) {
			global = "";
			productList(req, resp);
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

	public void editFormProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String action = req.getParameter("action");
		System.out.println(action);
		req.setAttribute("category", category.getAll());
		req.setAttribute("products", product.getById(id));
		req.setAttribute("view", "/template/saveProduct.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	public void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String cate = req.getParameter("option");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String description = req.getParameter("description");
		Part part = req.getPart("photo");
		Map<String, String> errors = new HashMap<String, String>();
		boolean flag = true;
		if (!Validate.isName(name)) {
			errors.put("name", "Name is not valid !");
			flag = false;
		}
		if (!Validate.isNumber(price)) {
			errors.put("price", "Price is not valid !");
			flag = false;
		}
		if (flag == false) {
			req.setAttribute("category", category.getAll());
			req.setAttribute("name", name);
			req.setAttribute("price", price);
			req.setAttribute("errors", errors);
			req.setAttribute("view", "/template/saveProduct.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		} else {
			String realPath = req.getServletContext().getRealPath("/uploads");
			System.out.println(realPath);
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			if (fileName == null || fileName.length() == 0) {
				fileName = "NULL";
			}
			System.out.println(fileName);
			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			part.write(realPath + "/" + fileName);
			if (description == null || description.length() == 0) {
				description = "NULL";
			}
			Category c = new Category();
			c.setId(Integer.valueOf(cate));
			Product p = new Product(id, c, name, Double.valueOf(price), fileName, description);
			product.update(p);
			resp.sendRedirect("./product");
		}
	}
	private void deleteFormProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Product p = product.getById(id);
		req.setAttribute("products",p );
		req.setAttribute("category", category.getAll());
		req.setAttribute("view", "/template/deleteProduct.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
		
	}
	private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		Product p = product.getById(id);
		product.delete(p);
		resp.sendRedirect("./product");
		
	}
}
