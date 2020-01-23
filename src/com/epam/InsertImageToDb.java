package com.epam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/abc")
public class InsertImageToDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sql = "insert into web-apps values (?,?)";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String file = request.getParameter("myFile");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bharath", "root", "root");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "image1");
			File file1 = new File(file);
			FileInputStream fileInputStream = new FileInputStream(file1);
            
			preparedStatement.setBinaryStream(2, fileInputStream, file1.length());
			int executeUpdate = preparedStatement.executeUpdate();
			System.out.println("count is" + executeUpdate);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
