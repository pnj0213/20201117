package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class List {
	public List(Scanner input, Connection conn) throws SQLException {
		System.out.println("=======�Խ��� ��ü���=========");
		System.out.print("��ȣ\t����\t����\t�۰�\t��¥\t\t��ȸ��\n");
		// 3.����,�����غ�
		Statement stmt = conn.createStatement();
		String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD order by title asc";// ���� �ʿ��� WHERE ��
																									// �־���.
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
