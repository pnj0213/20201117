package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Register {
	private Scanner input;
	private Connection conn;
	private String titleContent;
	private int indexI;
	private String title;
	private String content;
	private String author;
	private String nal;
	private int readcount;
	private Statement stmt;
	private String sql;
	private int cnt;
	
	
	public Register() { //�������Լ�
		input = new Scanner(System.in);
	}
	
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void setTitleContent() {
		System.out.println("����|����: ");
		titleContent = input.next();
	}
	public void titleContentProcess() {
		indexI = titleContent.indexOf("|");// indexI�� |�̰� 2�� ��.'
		title = titleContent.substring(0, indexI);// �ڸ��� �޼ҵ�
		content = titleContent.substring(indexI + 1);
	}
	public void setAuthor() {
		System.out.println("�ۼ����Է�: ");
		author = input.next();
	}
	public void setNal() {
		System.out.println("��¥: ");
		nal = input.next();// data�� ������ DB����
	}
	public void setReadCount() {
		System.out.println("��ȸ��: ");
		readcount = input.nextInt();
	}
	public void boardQuery() throws SQLException {
		stmt = conn.createStatement();
		sql = "INSERT INTO board(no,title,content,author,nal,readcount) values(board_no.nextval,'" + title
				+ "','" + content + "','" + author + "','" + nal + "'," + readcount + ")";
	}
	public void boardExcuter() throws SQLException {
		cnt = stmt.executeUpdate(sql);// ���������� ����
		System.out.println(cnt + "�� �Խñ��� ��ϵǾ����ϴ�.");
	}
	
	public static void main(String[] args) {
		Register register = new Register();
		Connection conn = null;
		register.setTitleContent();
		register.titleContentProcess();
		register.setAuthor();
		register.setNal();
		register.setReadCount();
		try {
			conn = register.getConnection();
			register.boardQuery();
			register.boardExcuter();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}



