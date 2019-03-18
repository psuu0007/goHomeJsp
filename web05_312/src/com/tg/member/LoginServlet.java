package com.tg.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = "/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession();
		
		MemberDto memberDto = (MemberDto)session.getAttribute("member");
		
		if(memberDto == null) {
			RequestDispatcher rd = req.getRequestDispatcher("./LoginForm.jsp");
			rd.forward(req, res);
		}else {
			String contextPathStr = req.getContextPath() + "/member/list";
			res.sendRedirect(contextPathStr);
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jsp";
		String password = "jsp";

		String email = req.getParameter("email");
		String pwd = req.getParameter("password");
		String name = "";
		
		String sql = "";
		int colIndex = 1;  // 컬럼 위치
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);

			// 1
			sql = "SELECT MNAME, EMAIL";
			sql += " FROM MEMBERS";
			sql += " WHERE EMAIL = ?";
			sql += " AND PWD = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(colIndex++, email);
			pstmt.setString(colIndex, pwd);
			
			rs = pstmt.executeQuery();
			System.out.println("쿼리 수행 성공");

//			res.setContentType("text/html");
//			res.setCharacterEncoding("UTF-8");

			if(rs.next()) {
				email = rs.getString("email");
				name = rs.getString("mname");
				
				// 2
				MemberDto memberDto = new MemberDto();
				
				memberDto.setEmail(email);
				memberDto.setName(name);
				// 3
				HttpSession session = req.getSession();
				session.setAttribute("member", memberDto);
				
				res.sendRedirect("../member/list");
			}else {
				RequestDispatcher dispatcher = 
						req.getRequestDispatcher("./LoginFail.jsp");
				
				dispatcher.forward(req, res);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 예외처리 페이지로 위임
			req.setAttribute("error", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");

			dispatcher.forward(req, res);

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // if(rs != null) end

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} // finally end

	}

}
