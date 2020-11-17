package kr.co.kh.function1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardExecuter {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// 1.로드(적재=자바에게 내가 데이터베이스를 무엇을 쓰겠다.)
		// 내가 자바에게 데이터베이스 무엇을 쓰겠다 알려주기
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn = null;
		
		while (true) {
			// 2.연결(connection)연결하다. DriverManager.getConnection
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("======게시판작성======");
			System.out.println("R:등록\tS:검색\tD:삭제\tU:수정\tL:목록");
			// String protocol = input.next();
			char protocol = input.next().charAt(0);
			// if(protocol.equals("R"))

			if (protocol == 'R' || protocol == 'r') { // 등록
				try {
					BoardFunction.register(input, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally { //무조건 수행하는거
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} // 등록
			else if (protocol == 'S' || protocol == 's') { // 검색
				try {
					BoardFunction.search(input, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			} // 검색
			else if (protocol == 'D' || protocol == 'd') { // 삭제
				try {
					BoardFunction.delete(input, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} // 삭제
			else if (protocol == 'U' || protocol == 'u') { // 수정
				char option = 'n';
				try {
					option = BoardFunction.update(input, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (option == 'n' || option == 'N') {
					continue;
				}
			} // 수정
			else if (protocol == 'L' || protocol == 'l') {// 전체출력
				try {
					BoardFunction.list(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} //전체출력
		}

	}

}