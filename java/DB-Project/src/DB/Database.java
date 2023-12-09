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
			while(rs.next()) {
				String hos_name= rs.getString("병원명");
				String address = rs.getString("주소");
				String hos_tel = rs.getString("전화번호");
				
	            dataList.add(new String[]{hos_name, address, hos_tel});
			}
		} catch(SQLException e) {
			e.printStackTrace();
			}

        System.out.println("병원 정보 불러오기 완료");
		return dataList;
	}
	
	
	/* 병원찾기 검색 메소드 */
	public List<String[]> searchhospital(String _text) {
		List<String[]> dataList = new ArrayList<>();
		String text = _text;
		String sql = "select 병원명, 주소, 전화번호 from 병원 where 주소 like ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + text + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String hname= rs.getString("병원명");
				String haddress = rs.getString("주소");
				String htel = rs.getString("전화번호");
				
				dataList.add(new String[]{hname, haddress, htel});
			}
		} catch(SQLException e) {
			e.printStackTrace();
			}
		
		System.out.println(text + " 검색");
		return dataList;
	}
	
	
	/* 게시글 테이블에 데이터 넣기 */
	public List<String[]> qnaData() {
	    List<String[]> dataList = new ArrayList<>();
		
		String sql = "Select 게시글번호, ID, 제목, 내용 From 게시글";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String postnum= rs.getString("게시글번호");
				String userid = rs.getString("ID");
				String posttitle = rs.getString("제목");
				String postcontent = rs.getString("내용");
				
	            dataList.add(new String[]{postnum, userid, posttitle, postcontent});
			}
		} catch(SQLException e) {
			e.printStackTrace();
			}

        System.out.println("게시글 내용 불러오기 완료");
		return dataList;
	}
	
	

	/* 게시글 작성 후 데이터 저장 메소드 */
	public boolean postdata(String _title, String _content, String _date) {
		String sql = "SELECT ID FROM 회원 WHERE 접속여부 = 1";
		String updatesql = "INSERT INTO 게시글 (게시글번호, 제목, 내용, 작성일자, ID) VALUES (게시글번호_시퀀스.NEXTVAL, ?, ?, ?, ?)";

		String id;
		String posttitle = _title;
		String postcontent = _content;
		String postdate = _date;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			PreparedStatement pstmt = conn.prepareStatement(updatesql);
			while(rs.next()) {
				id = rs.getString("ID");
				
				pstmt.setString(1, posttitle);
				pstmt.setString(2, postcontent);
				pstmt.setString(3, postdate);
				pstmt.setString(4, id);
				pstmt.executeUpdate();

				System.out.println("게시글 저장 완료");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		return false;
	}
	
	
	/* 게시글 검색 메소드 */
	public List<String[]> searchpost(String _text) {
		List<String[]> dataList = new ArrayList<>();
		String text = _text;
		String sql = "select 게시글번호, ID, 제목, 내용 from 게시글 where 제목 like ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + text + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String postnum= rs.getString("게시글번호");
				String userid = rs.getString("ID");
				String posttitle = rs.getString("제목");
				String postcontent = rs.getString("내용");
				
				dataList.add(new String[]{postnum, userid, posttitle, postcontent});
			}
		} catch(SQLException e) {
			e.printStackTrace();
			}
		
		System.out.println(text + " 검색");
		return dataList;
	}
	
	
	/* 게시글 삭제 메소드 */
	public boolean postdel(String _num) {
		String num = _num;
		
		try {
			CallableStatement cstmt = conn.prepareCall("{call 게시글_삭제(?)}"); // 게시글 삭제 프로시저
			cstmt.setString(1, num);
			cstmt.execute();
			System.out.println("게시글 삭제 완료");
			
		} catch(SQLException e) {
			e.printStackTrace();
			}
		return false;
	}
	
	
	/* 진료내역 테이블에 데이터 넣기 */
	public List<String[]> historyData() {
	    List<String[]> dataList = new ArrayList<>();
		
		String sql = "Select 의사.이름, to_char(진료.진료일자, 'yyyy/mm/dd'), 진료.진단내용, 진료.진료금액 From 의사, 진료, 회원"
					+ " Where 의사.의사번호 = 진료.의사코드 And 회원.ID = 진료.ID and 회원.접속여부 = 1";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			CallableStatement cstmt = conn.prepareCall("{call 평균평점_저장}"); // 평균평점 저장 프로시저
			while(rs.next()) {
				String drname = rs.getString(1);
				String medicdate = rs.getString(2);
				String mediccontent = rs.getString(3);
				String price = rs.getString(4);

				cstmt.execute();
	            dataList.add(new String[]{drname, medicdate, mediccontent, price});
			}
		} catch(SQLException e) {
			e.printStackTrace();
			}

        System.out.println("진료내역 불러오기 완료");
		return dataList;
	}
	
	
	/* 총진료비 조회 메소드 */
	public String totalprice() {
		String sql = "SELECT 회원.ID, 진료.총진료비 FROM 진료, 회원 WHERE 진료.ID = 회원.ID AND 접속여부 = 1";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			CallableStatement cstmt = conn.prepareCall("{call 진료비_총액_조회_및_저장(?)}"); // 총진료비 저장, 조회 프로시저
			while(rs.next()) {
				String id = rs.getString(1);
				cstmt.setString(1, id);
				cstmt.execute();
				System.out.println("진료비 조회");
				
				return rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* 테이블 열 클릭 후 평균평점 조회 메소드 */
	public String avgstar(String _name) {
		String sql = "SELECT 평균평점 FROM 의사 WHERE 이름 = '" + _name + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			CallableStatement cstmt = conn.prepareCall("{call 평균평점_저장}");
			while(rs.next()) {
				if(rs.getString("평균평점") == null) {
					return "평점이 없습니다.";
				}
				else {
					cstmt.execute();
					return rs.getString("평균평점");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
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
