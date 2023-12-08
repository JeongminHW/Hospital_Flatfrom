package User;

//public class QnA {

//}

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import DB.*;
import Main.Main;

public class QnA extends JFrame{
	DefaultTableModel tableModel = null;
	Database db = new Database();
	
    public QnA() {
    	JFrame frame = new JFrame("게시판");
    	setSize(700, 430);
    	setResizable(false); //창 크기 조절 X
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBackground(Color.white);
        
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기
	    contentPane.setLayout(null);
	    contentPane.setBackground(Color.white);
    	
    	String[] colName = {"번호", "아이디", "제목", "내용"}; //게시글 정보를 나타낼 열 값
    	tableModel = new DefaultTableModel(colName, 0);
	    
        JTable postTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(postTable);
        scrollPane.setBounds(0, 33, 688, 305); // 테이블 크기 조정
        postTable.setRowHeight(35); //행 너비 조절
        postTable.getColumnModel().getColumn(0).setPreferredWidth(10); // 번호 열 너비 조절
        postTable.getColumnModel().getColumn(1).setPreferredWidth(80); // 아이디 열 너비 조절
        postTable.getColumnModel().getColumn(2).setPreferredWidth(230); // 제목 열 너비 조절
        postTable.getColumnModel().getColumn(3).setPreferredWidth(230); // 내용 열 너비 조절
        postTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        postTable.getTableHeader().setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        postTable.getTableHeader().setReorderingAllowed(false); //이동 불가
        postTable.getTableHeader().setBackground(Color.white); //테이블 컬럼 색 변경
        postTable.getParent().setBackground(Color.white); //테이블 배경 색 변경
        
        contentPane.setLayout(null); 
        contentPane.add(scrollPane);
        
        //String[] data = {"1", "aa1234", "주소~~~~", "전번~~~~"};
        //tableModel.addRow(data);

        
        JTextField searchField = new JTextField(20);
        searchField.setBounds(189, 6, 226, 21);
        
        JButton searchButton = new JButton("검색");
        searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        searchButton.setBounds(420, 5, 71, 23);
        searchButton.setContentAreaFilled(false);
        
        JButton newPostButton = new JButton("글작성");
        newPostButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        newPostButton.setBounds(581, 5, 91, 23);
        newPostButton.setContentAreaFilled(false);
        
        contentPane.setLayout(null);
        contentPane.add(searchField);
        contentPane.add(searchButton);
        contentPane.add(newPostButton);
        
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
		JLabel chat_img = new JLabel(new ImageIcon("image/chat(blue).png"));
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
		JLabel mh_img = new JLabel(new ImageIcon("image/local-hospital(gray).png"));
		mh_img.setBounds(444, 319, 74, 74);

		contentPane.add(mh_img);
		contentPane.add(Mhistory_btn);
		
		
		/* 하단바 마이페이지 버튼 생성 */
		JButton Mypage_btn = new JButton("마이페이지");
		Mypage_btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Mypage_btn.setBounds(548, 337, 138, 55);
		Mypage_btn.setContentAreaFilled(false);
		Mypage_btn.setVerticalAlignment(SwingConstants.BOTTOM);
		
		// 진료내역 이미지 생성
		JLabel mypage_img = new JLabel(new ImageIcon("image/profile(gray).png"));
		mypage_img.setBounds(582, 317, 74, 74);

		contentPane.add(mypage_img);
		contentPane.add(Mypage_btn);
        
        
       /* 검색 버튼 이벤트 */ 
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
			}
        });
        
        /* 글 작성 버튼 이벤트 */
        newPostButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AddPost();
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

        setVisible(true);
    }

    public static void main(String[] args) {
        new QnA();
    }
}