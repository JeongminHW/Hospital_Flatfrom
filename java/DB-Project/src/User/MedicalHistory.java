package User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import DB.*;
import Main.*;

public class MedicalHistory extends JFrame{
	DefaultTableModel tableModel = null;
	Database db = new Database();
	
    public MedicalHistory() {
    	JFrame frame = new JFrame("병원찾기");
    	setSize(700, 430);
    	setResizable(false); //창 크기 조절 X
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBackground(Color.white);
        
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기
	    contentPane.setLayout(null);
	    contentPane.setBackground(Color.white);
    	
    	String[] colName = {"의사", "진료일자", "진단내역", "진료금액", "평점"}; //진료내역 정보를 나타낼 열 값
    	tableModel = new DefaultTableModel(colName, 0);
	    
        JTable Hospital_table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(Hospital_table);
        scrollPane.setBounds(0, 33, 688, 305); // 테이블 크기 조정
        Hospital_table.setRowHeight(40); //행 너비 조절
        Hospital_table.getColumnModel().getColumn(0).setPreferredWidth(60); // 의사 열 너비 조절
        Hospital_table.getColumnModel().getColumn(1).setPreferredWidth(130); // 진료일자 열 너비 조절
        Hospital_table.getColumnModel().getColumn(2).setPreferredWidth(130); // 진단내역 열 너비 조절
        Hospital_table.getColumnModel().getColumn(3).setPreferredWidth(60); // 진료금액 열 너비 조절
        Hospital_table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        Hospital_table.getTableHeader().setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        Hospital_table.getTableHeader().setReorderingAllowed(false); //이동 불가
        Hospital_table.getTableHeader().setBackground(Color.white); //테이블 컬럼 색 변경
        Hospital_table.getParent().setBackground(Color.white); //테이블 배경 색 변경
        
        contentPane.setLayout(null); 
        contentPane.add(scrollPane);
        
        //String[] data = {"aa1234", "주소~~~~", "전번~~~~"};
        //tableModel.addRow(data);
        
        //총 진료비 검색 버튼
        JButton searchButton = new JButton("총진료비 조회");
        searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        searchButton.setBounds(550, 5, 120, 23);
        searchButton.setContentAreaFilled(false);
        
        contentPane.setLayout(null);
        contentPane.add(searchButton);
        
        
        /* 하단바 홈 버튼 */
		JButton Home_btn = new JButton("홈");
		Home_btn.setBounds(0, 337, 138, 55);
		Home_btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Home_btn.setContentAreaFilled(false);
		//Home_btn.setBorder(null); // 테두리 X
		Home_btn.setVerticalAlignment(SwingConstants.BOTTOM);
		
		// 홈 이미지 생성
		JLabel home_img = new JLabel(new ImageIcon("image/home(gray).png"));
		home_img.setBounds(33, 320, 74, 74);
		
		contentPane.add(home_img);
		contentPane.add(Home_btn);
		
		
		/* 하단바 병원 찾기 버튼 */
		JButton hospital_btn = new JButton("병원찾기");
		hospital_btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		hospital_btn.setBounds(137, 337, 138, 55);
		hospital_btn.setContentAreaFilled(false);
		hospital_btn.setVerticalAlignment(SwingConstants.BOTTOM);
		
		// 병원 찾기 이미지 생성
		JLabel Hlacation_img = new JLabel(new ImageIcon("image/location(gray).png"));
		Hlacation_img.setBounds(169, 320, 74, 74);

		contentPane.add(Hlacation_img);
		contentPane.add(hospital_btn);
		
		
		/* 하단바 질문하기 버튼 생성 */
		JButton qna_btn = new JButton("질문하기");
		qna_btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		qna_btn.setBounds(274, 337, 138, 55);
		qna_btn.setContentAreaFilled(false);
		qna_btn.setVerticalAlignment(SwingConstants.BOTTOM);
		
		// 질문하기 이미지 생성
		JLabel chat_img = new JLabel(new ImageIcon("image/chat(gray).png"));
		chat_img.setBounds(305, 318, 74, 74);

		contentPane.add(chat_img);
		contentPane.add(qna_btn);
		
		
		/* 하단바 진료내역 버튼 생성 */
		JButton Mhistory_btn = new JButton("진료내역");
		Mhistory_btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Mhistory_btn.setBounds(411, 337, 138, 55);
		Mhistory_btn.setContentAreaFilled(false);
		Mhistory_btn.setVerticalAlignment(SwingConstants.BOTTOM);
		
		// 진료내역 이미지 생성
		JLabel mh_img = new JLabel(new ImageIcon("image/local-hospital(blue).png"));
		mh_img.setBounds(444, 319, 74, 74);

		contentPane.add(mh_img);
		contentPane.add(Mhistory_btn);
		
		
		/* 하단바 마이페이지 버튼 생성 */
		JButton Mypage_btn = new JButton("마이페이지");
		Mypage_btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Mypage_btn.setBounds(548, 337, 138, 55);
		Mypage_btn.setContentAreaFilled(false);
		Mypage_btn.setVerticalAlignment(SwingConstants.BOTTOM);
		
		// 마이페이지 이미지 생성
		JLabel mypage_img = new JLabel(new ImageIcon("image/profile(gray).png"));
		mypage_img.setBounds(582, 317, 74, 74);

		contentPane.add(mypage_img);
		contentPane.add(Mypage_btn);
        
        
       /* 총 진료비 검색 버튼 이벤트 */ 
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JButton b = (JButton)e.getSource();
        		
        		if(b.getText().equals("총진료비 조회")) {
        			
        		}
			}
        });
        
        
        /* 하단바 홈 버튼 이벤트 */
		Home_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				frame.setVisible(false);
			}
		});
		
		
		/* 하단바 병원 찾기 이벤트 */
		hospital_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HospitalSearch();
            	frame.setVisible(false);
			}
		});
		
		
		/* 하단바 질문하기 버튼 이벤트 */
		qna_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QnA();
            	frame.setVisible(false);
			}
		});
		
		
		/* 하단바 진료내역 버튼 이벤트 */
		Mhistory_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MedicalHistory();
            	frame.setVisible(false);
			}
		});
		
		
		/* 하단바 마이페이지 버튼 이벤트 */
		Mypage_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPage();
            	frame.setVisible(false);
			}
		});
		
		
		/* 창 닫기 이벤트 */
		frame.addWindowListener(new WindowCloseHandler());
        
        
        setVisible(true);
    }
    
	class WindowCloseHandler extends WindowAdapter{
	    public void windowClosing(WindowEvent e) {
	        System.out.println("로그아웃 완료");
	        db.logout();
	    }
	}

    public static void main(String[] args) {
        new MedicalHistory();
    }
}
