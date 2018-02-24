package com.javalec.spring_pjt_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.spring_pjt_board.dto.BDto;

public class BDao {

	Connection con; // 데이터베이스에 접근할수 있도록 설정해주는 객체 선언
	PreparedStatement pstmt; // 데이터베이스에서 쿼리를 실행시켜주는 객체 선언
	ResultSet rs; // 데이터베이스의 테이블의 결과를 리턴받아 자바에 저장해주는 객체

	// 오라클 데이터 베이스에 연결하고 select, insert, update, delete작업을 실행해주는 클래스
	private static BDao instance;

	private BDao() {
	}

	// getInstance public
	public static BDao getInstance() {
		if (instance == null) {
			instance = new BDao();
		}
		return instance;
	}

	public void getCon() {
		try {

			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");

			con = ds.getConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 리스트의 경우 데이타베이스에 데이타를 가져와서 뿌려줘야 함으로 ArrayList를 사용함

	
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>();

		// 무조건 데이터 베이스는 예외처리를 반드시 한다.
		try {
			
			// 1단계= 커넥션 연결
			getCon();
			
			// 2단계= 쿼리 준비
			String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
			//String query = "select * from mvc_board order by bGroup desc, bStep asc";
			
			// 쿼리를 실행 시켜주는 객체 선언
			pstmt = con.prepareStatement(query);

			// 쿼리를 실행 시킨 결과를 리턴해서 받아줌(오라클 테이블의 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			
			
			// 반복문을 사용해서 rs에 저장된 데이터를 추출
			while (rs.next()) { // 저장된 데이터 만큼까지 반복문을 돌리겠다라는 뜻
				
				BDto dto = new BDto();
				dto.setbId(rs.getInt(1));
				dto.setbName(rs.getString(2));
				dto.setbTitle(rs.getString(3));
				dto.setbContent(rs.getString(4));
				dto.setbDate(rs.getTimestamp(5));
				dto.setbHit(rs.getInt(6));
				dto.setbGroup(rs.getInt(7));
				dto.setbStep(rs.getInt(8));
				dto.setbIndent(rs.getInt(9));
				
				dtos.add(dto);
			}
/*			
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs !=null ) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con !=null) con.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}

		}
		return dtos;
	}
	
	public void write(String bName, String bTitle, String bContent) {
		
	try {
		
		// 1단계= 커넥션 연결
		getCon();
		
		// 2단계= 쿼리 준비
		String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";
		
		// 쿼리를 실행 시켜주는 객체 선언
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, bName);
		pstmt.setString(2, bTitle);
		pstmt.setString(3, bContent);
		
		pstmt.executeUpdate();
		
	} catch (Exception e) {
			System.out.println(e.getMessage());
	  } finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con !=null) con.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}

		} 
	
 }
	
	public BDto contentView(String strID) {
		
		upHit(strID); // 히트수 증가
		BDto dto = new BDto();
		try {
			getCon();			
			String sql = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strID));
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) { // 저장된 데이터 만큼까지 반복문을 돌리겠다라는 뜻

				dto.setbId(rs.getInt(1));
				dto.setbName(rs.getString(2));
				dto.setbTitle(rs.getString(3));
				dto.setbContent(rs.getString(4));
				dto.setbDate(rs.getTimestamp(5));
				dto.setbHit(rs.getInt(6));
				dto.setbGroup(rs.getInt(7));
				dto.setbStep(rs.getInt(8));
				dto.setbIndent(rs.getInt(9));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs !=null ) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con !=null) con.close();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		
		return dto;
	}

	
	
	
	private void upHit(String bId) {
		
		try {
			getCon();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}	
	
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		
		try {
			getCon();
			
			String sql = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId =?";
			
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			
			//id는 int타입이기에 Integer로 파싱이 필요함
			pstmt.setInt(4, Integer.parseInt(bId));
		
			//성공할경우 result가 int값 1로 반환됨.
			int result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	public void delete(String bId) {
		
		try {
			getCon();
			String sql = "delete from mvc_board where bId=?";
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bId));
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("게시글 삭제 성공");
			}
			
			else {
				
				System.out.println("게시글 삭제 실패");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	
	public BDto reply_view(String strBid) {
		
		BDto dto = new BDto();
		
		
		try {
			getCon();
			
			String sql = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strBid));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setbId(rs.getInt(1));
				dto.setbName(rs.getString(2));
				dto.setbTitle(rs.getString(3));
				dto.setbContent(rs.getString(4));
				dto.setbDate(rs.getTimestamp(5));
				dto.setbHit(rs.getInt(6));
				dto.setbGroup(rs.getInt(7));
				dto.setbStep(rs.getInt(8));
				dto.setbIndent(rs.getInt(9));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs !=null ) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con !=null) con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	//데이타베이스에 작성된 답변글을 넣어주는거기때문에 따로 반환타입을 지정할 필요가 없다
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		try {	
			
			//이거 왜써야하는지 몰겠음 ㅡㅡ;
			replyShape(bGroup, bStep);
			
			getCon();
			String sql = "insert into mvc_board(bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values(mvc_board_seq.nextval,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			
			int result = pstmt.executeUpdate();
		
			if(result == 1) {
				System.out.println("답변 달기 성공");
			}
			
			else {
				
				System.out.println("답변 달기 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con !=null) con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	
	private void replyShape( String strGroup, String strStep) {
		
		try {
			getCon();
			
			//스텝을 한단계 올리는데 어디서? => 그룹숫자가 같은곳에서 
			String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}




