package com.aptech.hw5.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.util.encoders.Hex;

import com.aptech.hw5.model.User;
import com.aptech.hw5.model.Validate;
import com.aptech.hw5.service.UserService;
import com.aptech.hw5.service.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	UserService service = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		req.setAttribute("view", "/template/login.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass  = req.getParameter("password");
		Map<String,String> errors = new HashMap<String,String>();
System.out.println(Validate.sha_256(pass));
		boolean flag = true;
		if(user.length() == 0) {
			errors.put("user", "User cannot empty !");
			flag = false;
		}
		if(pass.length() == 0) {
			errors.put("pass", "Password cannot empty !");
			flag = false;
		}
		if(flag == false) {
			req.setAttribute("errors", errors);
			req.setAttribute("view", "/template/login.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}else {
			if(service.checkLogin(user, Validate.sha_256(pass)) == true) {
				User s = new User(user,pass);
				HttpSession session = req.getSession();
				session.setAttribute("userLogin",s);
				resp.sendRedirect("./product?index=1");
			}else {
				errors.put("login", "Login Failed !");
				req.setAttribute("errors", errors);
				req.setAttribute("view", "/template/login.jsp");
				req.getRequestDispatcher("layout.jsp").forward(req, resp);
			}
		}
		
	}

}
