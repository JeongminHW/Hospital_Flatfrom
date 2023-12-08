package User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Main.*;
import DB.*;

public class MyPage extends JFrame {
	Database db = new Database();
	
	public MyPage() {
		setResizable(false); //창 크기 조절 X
	    setSize(700, 430);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane(); // 컨텐트팬 알아내기
	    contentPane.setLayout(null);
	    contentPane.setBackground(Color.white);
	    
	    /* 프로필 이미지 */
	    JLabel Profile_img = new JLabel(new ImageIcon("image/profile(blue).png"));
	    Profile_img.setBounds(60, 10, 74, 74);
	    contentPane.add(Profile_img);
	    
	    /* 아이디 들어갈 라벨 */
	    JLabel ID_lb = new JLabel(db.mypageID() + " 님");
	    ID_lb.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
	    ID_lb.setBounds(130, 25, 200, 40);
	    contentPane.add(ID_lb);
	    
	    
	    // 진료내역 확인 버튼
	    JButton my_history_btn = new JButton("진료내역 확인하기");
	    my_history_btn.setBounds(60, 90, 564, 60);
	    my_history_btn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
	    my_history_btn.setForeground(Color.white);
	    my_history_btn.setBackground(new Color(133, 182, 255));
	    my_history_btn.setBorder(null);
 		
 		contentPane.add(my_history_btn);
 		
 		// 병원찾기 버튼 생성
 		JButton my_Hospital_btn = new JButton("가까운 병원 찾기");
 		my_Hospital_btn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
 		my_Hospital_btn.setForeground(Color.white);
 		my_Hospital_btn.setBackground(new Color(133, 182, 255));
 		my_Hospital_btn.setBounds(60, 170, 564, 60);
 		my_Hospital_btn.setBorder(null);
 		
 		contentPane.add(my_Hospital_btn);
 		
 		// 회원탈퇴 버튼 생성
 		JButton user_Cancel_btn = new JButton("탈퇴하기");
 		user_Cancel_btn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
 		user_Cancel_btn.setForeground(Color.white);
 		user_Cancel_btn.setBackground(Color.gray);
 		user_Cancel_btn.setBounds(60, 270, 564, 35);
 		user_Cancel_btn.setBorder(null);
 		
 		contentPane.add(user_Cancel_btn);
		
		
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
		
		// 마이페이지 이미지 생성
		JLabel mypage_img = new JLabel(new ImageIcon("image/profile(blue).png"));
		mypage_img.setBounds(582, 317, 74, 74);

		contentPane.add(mypage_img);
		contentPane.add(Mypage_btn);
		
		
		
		/* 진료내역 확인하기 버튼 이벤트 */
		my_history_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	setVisible(false);
			}
		});
		
		
		/* 병원 찾기 버튼 이벤트 */
		my_Hospital_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HospitalSearch();
	        	setVisible(false);
			}
		});
		
		
		/* 회원 탈퇴 버튼 이벤트 */
		user_Cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "탈퇴 하시겠습니까?", null, JOptionPane.YES_NO_OPTION);
 			   	if(result == JOptionPane.YES_OPTION)
 			   	{
 					db.usercancel();
 					System.out.println("성공");
 					new UserLogin();
 					setVisible(false);
 			   	}
 			   	else
 			   		return;
			}
		});
		
		 
	    /* 하단바 홈 버튼 이벤트 */
		Home_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});
		
		
		/* 하단바 병원 찾기 이벤트 */
		hospital_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HospitalSearch();
	        	setVisible(false);
			}
		});
		
		
		/* 하단바 질문하기 버튼 이벤트 */
		qna_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QnA();
	        	setVisible(false);
			}
		});
		
		
		/* 하단바 진료내역 버튼 이벤트 */
		Mhistory_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MedicalHistory();
	        	setVisible(false);
			}
		});
		
		
		/* 하단바 마이페이지 버튼 이벤트 */
		Mypage_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPage();
            	setVisible(false);
			}
		});
	    
		
		/* 창 닫기 이벤트 */
		addWindowListener(new WindowCloseHandler());
		
	    
	    setVisible(true);
	}
	
	class WindowCloseHandler extends WindowAdapter{
	    public void windowClosing(WindowEvent e) {
	        System.out.println("로그아웃 완료");
	        db.logout();
	    }
	}

    public static void main(String[] args) {
        new MyPage();
    }
}
