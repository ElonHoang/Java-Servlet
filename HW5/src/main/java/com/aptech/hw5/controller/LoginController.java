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
import com.aptech.hw5.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	UserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		System.out.println(action);
		req.setAttribute("action", action);
		switch (action) {
		case "createAccount":
			showCreateAccount(req, resp);
			break;
		default:
			showLogin(req, resp);
			break;
		}

	}

	private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("user", "hoangNV");
		req.setAttribute("password", "12345678");
		req.setAttribute("view", "/template/login.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	private void showCreateAccount(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("view", "/template/login.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
		case "createAccount":
			createAccount(req, resp);
			break;
		default:
			login(req, resp);
			break;
		}

	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass = req.getParameter("password");
		Map<String, String> errors = new HashMap<String, String>();
		boolean flag = true;
		if (user.length() == 0) {
			errors.put("user", "User cannot empty !");
			flag = false;
		}
		if (pass.length() == 0) {
			errors.put("pass", "Password cannot empty !");
			flag = false;
		}
		if (flag == false) {
			req.setAttribute("errors", errors);
			req.setAttribute("view", "/template/login.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		} else {
			if (service.checkLogin(user, Validate.sha_256(pass)) == true) {
				User s = new User(user, Validate.sha_256(pass));
				HttpSession session = req.getSession();
				session.setAttribute("userLogin", s);
				resp.sendRedirect("./home");
			} else {
				errors.put("login", "Login Failed !");
				req.setAttribute("errors", errors);
				req.setAttribute("view", "/template/login.jsp");
				req.getRequestDispatcher("layout.jsp").forward(req, resp);
			}
		}

	}

	private void createAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String user = req.getParameter("user");
		String pass = req.getParameter("password");
		
		Map<String, String> errors = new HashMap<String, String>();
		boolean flag = true;
		if (user.length() == 0) {
			errors.put("user", "User cannot empty !");
			flag = false;
		}
		if (pass.length() == 0) {
			errors.put("pass", "Password cannot empty !");
			flag = false;
		}
		if (flag == false) {
			req.setAttribute("errors", errors);
			req.setAttribute("view", "/template/login.jsp");
			req.setAttribute("action", "createAccount");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		} else {
			service.createAccount(new User(user,Validate.sha_256(pass)));
			resp.sendRedirect("./login");
		}

	}

}
