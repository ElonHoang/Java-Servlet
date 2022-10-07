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
import javax.servlet.http.Part;

import com.aptech.hw5.model.Category;
import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.Validate;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.CategoryServiceImpl;
import com.aptech.hw5.service.ProductServiceImpl;

@MultipartConfig()
@WebServlet("/add")
public class AddProductController extends HttpServlet {
	CRUDService<Product> product = new ProductServiceImpl();
	CRUDService<Category> category = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("category", category.getAll());
		req.setAttribute("view","/template/addProduct.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			req.setAttribute("view", "/template/addProduct.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		} else {
			String realPath = req.getServletContext().getRealPath("/uploads");
			System.out.println(realPath);
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			if(fileName == null || fileName.length() == 0) {
				fileName = "NULL";
			}
			System.out.println(fileName);
			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			part.write(realPath + "/" + fileName);
			if(description == null || description.length() == 0) {
				description = "NULL";
			}
			Category c = new Category();
			c.setId(Integer.valueOf(cate));
			Product p = new Product(id, c, name, Double.valueOf(price), fileName, description);		
			product.add(p);
			resp.sendRedirect("./product");
		}

	}

}
