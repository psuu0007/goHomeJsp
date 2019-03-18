package com.tg.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value="/auth/logout")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 세션 객체 호출
		HttpSession session = req.getSession();
		// 세션 객체 파기
		session.invalidate();
		// 일정 시간이 지나면 자동적으로 파기한다
//		session.setMaxInactiveInterval(60*60);
		
		res.sendRedirect("./login");
		
	}
	
	
	
	
	
}
