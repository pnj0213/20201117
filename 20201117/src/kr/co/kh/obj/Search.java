package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Search {
	public Search (Scanner input, Connection conn) throws SQLException {
	System.out.print("ã�� �Խñ� ���� �Է�:");
	String searchTitle = input.next();
	System.out.print("��ȣ\t����\t����\t�۰�\t��¥\t\t��ȸ��\n");
	// �����ͺ��̽��� ��Ʈ�� �ε�=���� ���� �� �ʿ�� ���� . �غ����ֱ�
	Statement stmt = conn.createStatement();
	String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE = '" + searchTitle + "'";
	// String sql = "select * from board where title = '"+searchTitle+"'"; //3.�غ�
	// 3-2 ������ �غ�
	ResultSet/* �������̽� */ rs = stmt.executeQuery(sql);// ���̺�(ǥ) �ȿ� �ִ� ù ��°, �� ��° �� ��° ... ���� ����Ų��.
	int readcount = 0;
	while (rs.next()) {// �����ͺ��̽����� ������ ������ ����ִ´�. �׷��� DB���� ���ϴ� �����͸� ����;ߵ�. �׷��� rs�� �����Ѿ� �Ѵ�.
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
//        System.out.println("No = " + rs.getInt(1) + "Title = " + rs.getString(2) + " Content = " + rs.getString(3) + " Author = " + rs.getString(4) + " NAL = " + rs.getString(5) + " ��ȸ�� = " + newReadCount);
	}
	stmt = conn.createStatement();
	sql = "UPDATE BOARD SET readcount = " + readcount + " WHERE TITLE = '" + searchTitle + "'";
	int cnt = stmt.executeUpdate(sql);

}

}
