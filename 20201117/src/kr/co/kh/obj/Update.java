package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
	public Update (Scanner input, Connection conn) throws SQLException {
	System.out.println("변경할 게시글 제목을 입력하세요 : ");
	String searchTitle = input.next();
	// 3. 공간 쿼리준비
	Statement stmt = null;
	String sql = null;
	stmt = conn.createStatement();
	sql = "SELECT TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE = '" + searchTitle + "'";
	ResultSet rs = stmt.executeQuery(sql);
	System.out.println("======변경하기 전 게시글입니다.======");
	while (rs.next()) {
		String title = rs.getString("title");
		String content = rs.getString("content");
		String author = rs.getString("author");
		String nal = rs.getString("nal");
		int readcount = rs.getInt("readcount");
		System.out.print(title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
	}
	System.out.println("정말로 변경 하시겠습니까y/n");// y가 들어오면 수정하고 n이 들어오면 수정안함
	char option = input.next().charAt(0);
	if (option == 'Y' || option == 'y') {
		System.out.println("제목|내용: ");
		String titleContent = input.next();
		int indexI = titleContent.indexOf("|");// indexI에 |이건 2에 들어감.'
		String updateTitle = titleContent.substring(0, indexI);// 자르는 메소드
		String contentUpdate = titleContent.substring(indexI + 1);
		// 제목따로 내용따로
		System.out.println("작성자입력: ");
		String authorUpdate = input.next();
		System.out.println("날짜: ");
		String nalUpdate = input.next();// data는 예약임 DB에서
		System.out.println("조회수: ");
		int readcountUpdate = input.nextInt();
		stmt = conn.createStatement();
		sql = "UPDATE BOARD SET TITLE ='" + updateTitle + "', CONTENT = '" + contentUpdate + "', AUTHOR = '"
				+ authorUpdate + "', NAL = '" + nalUpdate + "', READCOUNT = '" + readcountUpdate
				+ "' WHERE TITLE = '" + searchTitle + "'";
		int cnt = stmt.executeUpdate(sql);
		System.out.print("번호\t제목\t내용\t작가\t날짜\t\t조회수\n");
		System.out.println(updateTitle + "\t" + updateTitle + "\t" + contentUpdate + "\t" + authorUpdate + "\t"
				+ nalUpdate + "\t" + readcountUpdate);
		System.out.println();

	}
	return;
}
}
