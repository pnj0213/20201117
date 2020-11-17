package kr.co.kh.obj;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
	public Delete (Scanner input, Connection conn) throws SQLException {
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

}
