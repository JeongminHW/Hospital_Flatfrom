package DB;

import java.sql.*;
import java.util.*;

import javax.swing.table.*;

public class Database {
	Connection conn = null;
	Statement stmt = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String id = "Hospital";
	String password = "1234";
	
	public Database(){
		
		/* 데이터 베이스 접속 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 적재 성공");
			conn = DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
		} catch(ClassNotFoundException e) { 
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch(SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
	}
	
	
	/* 유저 로그인 정보 확인 메소드 */
	public boolean logincheck(String _id, String _pw) {
		boolean flag = false;
		
		String id = _id;
		String pw = _pw;
		
		try {
			String checkID = "SELECT PW, 접속여부 FROM 회원 WHERE ID='" + id + "'";
			stmt = conn.prepareStatement(checkID);
			ResultSet rs = stmt.executeQuery(checkID);
			CallableStatement cstmt = conn.prepareCall("{call 로그인_처리(?, ?)}");
			cstmt.setString(1, id);
			cstmt.setInt(2, 1);
			
			while(rs.next()) {
				if(pw.equals(rs.getString("PW"))) {
					flag = true;
					cstmt.execute();
					System.out.println("접속여부 변경 완료 (0 -> 1)");
					System.out.println("로그인 성공");
				}
				
				else {
					flag = false;
					System.out.println("로그인 실패");
				}
				
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}
		
		return flag;
	}
	
	
	/* 회원가입 메소드 */
	public boolean joinCheck(String _id, String _pw, String _birth, String _name, String _tel) {
		boolean flag = false;
		
		String id = _id;
		String pw = _pw;
		String birth = _birth;
		String name = _name;
		String phone = _tel;
		
		try {
			String insertStr = "INSERT INTO 회원 (ID, PW," + "생년월일" + ", " + "이름" + ", " + "전화번호" + ")" + "VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(insertStr);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, birth);
			pstmt.setString(4, name);
			pstmt.setString(5, phone);
			pstmt.executeUpdate();
				
			flag = true;
			System.out.println("회원가입 성공");
		} catch(Exception e) {
			flag = false;
			System.out.println("회원가입 실패 > " + e.toString());
		}
			
		return flag;
	}
	
	
	/* 아이디 중복 체크 메소드*/
	public boolean findExistID(String id)
	 {
		String sql = "SELECT ID from 회원 where ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if (rs.getString("ID").equals(id)) {
						return true;
					}
				}
				
		 	} catch (SQLException e) {
		 		e.printStackTrace();
		 	} 	 	
		return false;
	 } 
	
	
	/* 병원찾기 테이블에 데이터 넣기 */
	public List<String[]> hospitalData() {
	    List<String[]> dataList = new ArrayList<>();
		
		String sql = "SELECT 병원명, 주소, 전화번호 FROM 병원";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String row[] = new String[3];
			while(rs.next()) {
				String hos_name= rs.getString("병원명");
				String address = rs.getString("주소");
				String hos_tel = rs.getString("전화번호");
				
	            dataList.add(new String[]{hos_name, address, hos_tel});
			}
		} catch(SQLException e) {
			e.printStackTrace();
			}
		
		return dataList;
	}
	
	
	/* 마이페이지 아이디 나타내는 메소드 */
	public String mypageID() {
		String sql = "SELECT ID FROM 회원 WHERE 접속여부 = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("ID");
				return id;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/* 회원 탈퇴 메소드 */
	public boolean usercancel() {
		String sql = "SELECT ID FROM 회원 WHERE 접속여부 = 1";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			CallableStatement cstmt = conn.prepareCall("{call 회원_탈퇴_처리(?)}");
			
			while(rs.next()) {
				String id = rs.getString("ID");
				cstmt.setString(1, id);
				cstmt.execute();
				System.out.println("회원 탈퇴 완료");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/* 로그아웃 (접속여부 변경) */
	public boolean logout()
	{
		boolean flag = false;
		
		// 쿼리를 넣어주는 부분
		try {
			CallableStatement cstmt = conn.prepareCall("{call 로그아웃_처리(?)}");
			flag = true;
			cstmt.setInt(1, 1);
			cstmt.execute();
			System.out.println("접속여부 변경 완료(1 -> 0)");
			
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			System.out.println("실패 > " + e.toString());
		}
			
		return flag;
	}
}
