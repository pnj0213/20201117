package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class List {
	public List(Scanner input, Connection conn) throws SQLException {
		System.out.println("=======게시판 전체출력=========");
		System.out.print("번호\t제목\t내용\t작가\t날짜\t\t조회수\n");
		// 3.공간,쿼리준비
		Statement stmt = conn.createStatement();
		String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD order by title asc";// 조건 필요없어서 WHERE 안
																									// 넣어줌.
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int no = rs.getInt("no");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String author = rs.getString("author");
			String nal = rs.getString("nal");
			int readcount = rs.getInt("readcount");
			System.out.print(no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
		}
	}
}
