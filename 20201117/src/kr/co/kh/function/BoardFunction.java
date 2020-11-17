package kr.co.kh.function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardFunction {
	public static void register(Scanner input, Connection conn) throws SQLException {
		System.out.println("����|����: ");
		String titleContent = input.next();
		int indexI = titleContent.indexOf("|");// indexI�� |�̰� 2�� ��.
		String title = titleContent.substring(0, indexI);// �ڸ��� �޼ҵ�
		String content = titleContent.substring(indexI + 1);
		// ������� �������
		System.out.println("�ۼ����Է�: ");
		String author = input.next();
		System.out.println("��¥: ");
		String nal = input.next();// data�� ������ DB����
		System.out.println("��ȸ��: ");
		int readcount = input.nextInt();
		// 3.�غ�(Statement����) �����غ�. 3-1 ������ �غ�
		// 3.�غ�(Statement����) �����غ�. 3-2 �����غ�
		Statement /* ����,��ɾ� */ stmt = conn.createStatement();// �� conn�� �־��� ������ ������ش�.
		String sql = "INSERT INTO board(title,content,author,nal,readcount) values('" + title
				+ "','" + content + "','" + author + "','" + nal + "'," + readcount + ")";
//		String sql = "INSERT INTO board(no,title,content,author,nal,readcount) values(board_no.nextval,'" + title
//				+ "','" + content + "','" + author + "','" + nal + "'," + readcount + ")";
		// �� �κ� ������ �ٲ���� values(board_no.nextval,'����1','����1','kh','2020.11.12',0) ��ó�� ����� �Ѵ�.
		// '""'<<���ڿ� ""<<����
		// 4.���� execute
		int cnt = stmt.executeUpdate(sql);// ���������� ����
		System.out.println(cnt + "�� �Խñ��� ��ϵǾ����ϴ�.");
		System.out.println();
		// ���� ���� ��Ʈ��
		// stmt.close();
		// connection �� ����
		// conn.close();

	}

	public static void search(Scanner input, Connection conn) throws SQLException {
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
//            System.out.println("No = " + rs.getInt(1) + "Title = " + rs.getString(2) + " Content = " + rs.getString(3) + " Author = " + rs.getString(4) + " NAL = " + rs.getString(5) + " ��ȸ�� = " + newReadCount);
		}
		stmt = conn.createStatement();
		sql = "UPDATE BOARD SET readcount = " + readcount + " WHERE TITLE = '" + searchTitle + "'";
		int cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "���� �Խñ��� �˻� �Ǿ����ϴ�."+"\n");
	}

	public static void delete(Scanner input, Connection conn) throws SQLException {
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

	public static char update(Scanner input, Connection conn) throws SQLException {
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
		return option;
	}

	public static void list(Connection conn) throws SQLException {
		System.out.println("=======�Խ��� ��ü���=========");
		System.out.print("��ȣ\t����\t����\t�۰�\t��¥\t\t��ȸ��\n");
		// 3.����,�����غ�
		Statement stmt = conn.createStatement();
		String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD order by title asc";// ���� �ʿ��� WHERE �� �־���.
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
		// �ݺ���
	}

}
