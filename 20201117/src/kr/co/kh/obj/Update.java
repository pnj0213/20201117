package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
	public Update (Scanner input, Connection conn) throws SQLException {
	System.out.println("������ �Խñ� ������ �Է��ϼ��� : ");
	String searchTitle = input.next();
	// 3. ���� �����غ�
	Statement stmt = null;
	String sql = null;
	stmt = conn.createStatement();
	sql = "SELECT TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE = '" + searchTitle + "'";
	ResultSet rs = stmt.executeQuery(sql);
	System.out.println("======�����ϱ� �� �Խñ��Դϴ�.======");
	while (rs.next()) {
		String title = rs.getString("title");
		String content = rs.getString("content");
		String author = rs.getString("author");
		String nal = rs.getString("nal");
		int readcount = rs.getInt("readcount");
		System.out.print(title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
	}
	System.out.println("������ ���� �Ͻðڽ��ϱ�y/n");// y�� ������ �����ϰ� n�� ������ ��������
	char option = input.next().charAt(0);
	if (option == 'Y' || option == 'y') {
		System.out.println("����|����: ");
		String titleContent = input.next();
		int indexI = titleContent.indexOf("|");// indexI�� |�̰� 2�� ��.'
		String updateTitle = titleContent.substring(0, indexI);// �ڸ��� �޼ҵ�
		String contentUpdate = titleContent.substring(indexI + 1);
		// ������� �������
		System.out.println("�ۼ����Է�: ");
		String authorUpdate = input.next();
		System.out.println("��¥: ");
		String nalUpdate = input.next();// data�� ������ DB����
		System.out.println("��ȸ��: ");
		int readcountUpdate = input.nextInt();
		stmt = conn.createStatement();
		sql = "UPDATE BOARD SET TITLE ='" + updateTitle + "', CONTENT = '" + contentUpdate + "', AUTHOR = '"
				+ authorUpdate + "', NAL = '" + nalUpdate + "', READCOUNT = '" + readcountUpdate
				+ "' WHERE TITLE = '" + searchTitle + "'";
		int cnt = stmt.executeUpdate(sql);
		System.out.print("��ȣ\t����\t����\t�۰�\t��¥\t\t��ȸ��\n");
		System.out.println(updateTitle + "\t" + updateTitle + "\t" + contentUpdate + "\t" + authorUpdate + "\t"
				+ nalUpdate + "\t" + readcountUpdate);
		System.out.println();

	}
	return;
}
}
