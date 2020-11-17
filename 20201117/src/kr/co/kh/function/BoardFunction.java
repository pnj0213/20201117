package kr.co.kh.function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardFunction {
	public static void register(Scanner input, Connection conn) throws SQLException {
		System.out.println("제목|내용: ");
		String titleContent = input.next();
		int indexI = titleContent.indexOf("|");// indexI에 |이건 2에 들어감.
		String title = titleContent.substring(0, indexI);// 자르는 메소드
		String content = titleContent.substring(indexI + 1);
		// 제목따로 내용따로
		System.out.println("작성자입력: ");
		String author = input.next();
		System.out.println("날짜: ");
		String nal = input.next();// data는 예약임 DB에서
		System.out.println("조회수: ");
		int readcount = input.nextInt();
		// 3.준비(Statement문장) 연결준비. 3-1 공간을 준비
		// 3.준비(Statement문장) 연결준비. 3-2 쿼리준비
		Statement /* 문장,명령어 */ stmt = conn.createStatement();// 저 conn을 넣어줄 공간을 만들어준다.
		String sql = "INSERT INTO board(title,content,author,nal,readcount) values('" + title
				+ "','" + content + "','" + author + "','" + nal + "'," + readcount + ")";
//		String sql = "INSERT INTO board(no,title,content,author,nal,readcount) values(board_no.nextval,'" + title
//				+ "','" + content + "','" + author + "','" + nal + "'," + readcount + ")";
		// 이 부분 내용이 바뀌려면 values(board_no.nextval,'제목1','내용1','kh','2020.11.12',0) 위처럼 해줘야 한다.
		// '""'<<문자열 ""<<숫자
		// 4.실행 execute
		int cnt = stmt.executeUpdate(sql);// 정수형으로 리턴
		System.out.println(cnt + "건 게시글이 등록되었습니다.");
		System.out.println();
		// 문장 끊기 스트림
		// stmt.close();
		// connection 도 끊기
		// conn.close();

	}

	public static void search(Scanner input, Connection conn) throws SQLException {
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
//            System.out.println("No = " + rs.getInt(1) + "Title = " + rs.getString(2) + " Content = " + rs.getString(3) + " Author = " + rs.getString(4) + " NAL = " + rs.getString(5) + " 조회수 = " + newReadCount);
		}
		stmt = conn.createStatement();
		sql = "UPDATE BOARD SET readcount = " + readcount + " WHERE TITLE = '" + searchTitle + "'";
		int cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "건의 게시글이 검색 되었습니다."+"\n");
	}

	public static void delete(Scanner input, Connection conn) throws SQLException {
		System.out.println("삭제할 제목을 입력하세요 : ");
		String deleteTitle = input.next();
		// 3.준비
		// 3-1 공간을 준비한다.
		// 3-2 쿼리를 준비한다.
		Statement stmt = conn.createStatement();
		String sql = "DELETE FROM BOARD WHERE TITLE = '" + deleteTitle + "'";// 제목이 같으면 지워라 라고 조건 넣어주는것임.
		int cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "건 게시글이 삭제 되었습니다.");
		System.out.println();
	}

	public static char update(Scanner input, Connection conn) throws SQLException {
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
		return option;
	}

	public static void list(Connection conn) throws SQLException {
		System.out.println("=======게시판 전체출력=========");
		System.out.print("번호\t제목\t내용\t작가\t날짜\t\t조회수\n");
		// 3.공간,쿼리준비
		Statement stmt = conn.createStatement();
		String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD order by title asc";// 조건 필요없어서 WHERE 안 넣어줌.
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
		// 반복문
	}

}
