package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Search {
	public Search (Scanner input, Connection conn) throws SQLException {
	System.out.print("찾는 게시글 제목 입력:");
	String searchTitle = input.next();
	System.out.print("번호\t제목\t내용\t작가\t날짜\t\t조회수\n");
	// 데이터베이스로 컨트롤 로드=적재 연결 할 필요는 없다 . 준비해주기
	Statement stmt = conn.createStatement();
	String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE = '" + searchTitle + "'";
	// String sql = "select * from board where title = '"+searchTitle+"'"; //3.준비
	// 3-2 쿼리를 준비
	ResultSet/* 인터페이스 */ rs = stmt.executeQuery(sql);// 테이블(표) 안에 있는 첫 번째, 두 번째 세 번째 ... 열을 가리킨다.
	int readcount = 0;
	while (rs.next()) {// 데이터베이스에서 꺼내서 변수로 집어넣는다. 그래서 DB에서 원하는 데이터를 갖고와야됨. 그래서 rs로 가리켜야 한다.
		int no = rs.getInt("no");
		String title = rs.getString("title");
		String content = rs.getString("content");
		String author = rs.getString("author");
		String nal = rs.getString("nal");
		readcount = rs.getInt("readcount");
		System.out.println(no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount);
		readcount++;
		System.out.println();
		// int newReadcount
//        System.out.println("No = " + rs.getInt(1) + "Title = " + rs.getString(2) + " Content = " + rs.getString(3) + " Author = " + rs.getString(4) + " NAL = " + rs.getString(5) + " 조회수 = " + newReadCount);
	}
	stmt = conn.createStatement();
	sql = "UPDATE BOARD SET readcount = " + readcount + " WHERE TITLE = '" + searchTitle + "'";
	int cnt = stmt.executeUpdate(sql);

}

}
