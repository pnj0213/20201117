package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
	public Delete (Scanner input, Connection conn) throws SQLException {
	System.out.println("������ ������ �Է��ϼ��� : ");
	String deleteTitle = input.next();
	// 3.�غ�
	// 3-1 ������ �غ��Ѵ�.
	// 3-2 ������ �غ��Ѵ�.
	Statement stmt = conn.createStatement();
	String sql = "DELETE FROM BOARD WHERE TITLE = '" + deleteTitle + "'";// ������ ������ ������ ��� ���� �־��ִ°���.
	int cnt = stmt.executeUpdate(sql);
	System.out.println(cnt + "�� �Խñ��� ���� �Ǿ����ϴ�.");
	System.out.println();
}

}
