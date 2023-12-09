package User;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import DB.*;

public class UserSignIn {
	   Database db = new Database();
	   String user_id;    // 유저가 입력한 아이디를 담을 변수
	   String user_pw;  // 유저가 입력한 비밀번호를 담을 변수
	   String user_name;	// 유저가 입력한 이름을 담을 변수
	   String user_birth;	// 유저가 입력한 생년월일을 담을 변수
	   String user_phone;	// 유저가 입력한 전화번호를 담을 변수
	   

   public UserSignIn() {
	   
	      JFrame frame = new JFrame();
	      frame.setSize(700, 430); 
	      frame.setResizable(false); //창 크기 조절 X
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      Container contentPane = frame.getContentPane(); // 컨텐트팬 알아내기
	      contentPane.setLayout(null);
	      contentPane.setBackground(Color.white);
	      
	      JButton Join_fine_btn = new JButton("회원가입 완료");
	      Join_fine_btn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	      Join_fine_btn.setBounds(208, 307, 271, 40);
	      Join_fine_btn.setContentAreaFilled(false);
	      contentPane.add(Join_fine_btn);
	      
	      JTextField ID_text = new JTextField(); // 아이디 입력란
	      ID_text.setBounds(303, 90, 169, 29);
	      contentPane.add(ID_text);
	      ID_text.setColumns(10);
	      
	      JPasswordField PW_Text = new JPasswordField(); // 비밀번호 입력란
	      PW_Text.setBounds(303, 129, 169, 29);
	      contentPane.add(PW_Text);
	      PW_Text.setColumns(10);
	      
	      JTextField Name_text = new JTextField(); //이름 입력란
	      Name_text.setColumns(10);
	      Name_text.setBounds(303, 168, 169, 29);
	      contentPane.add(Name_text);
	      
	      JTextField Tel_text = new JTextField(); // 전화번호 입력란
	      Tel_text.setColumns(10);
	      Tel_text.setBounds(303, 207, 169, 28);
	      contentPane.add(Tel_text);
	      
	      JTextField Birth_text = new JTextField(); //생년월일 입력란
	      Birth_text.setColumns(10);
	      Birth_text.setBounds(303, 246, 169, 26);
	      contentPane.add(Birth_text);

	      JButton IDcheck_btn = new JButton("중복확인"); //중복확인 버튼
	      IDcheck_btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
	      IDcheck_btn.setBounds(484, 90, 97, 29);
	      IDcheck_btn.setContentAreaFilled(false);
	      contentPane.add(IDcheck_btn);
	      
	      JLabel ID_lb = new JLabel("아이디"); // 아이디 라벨
	      ID_lb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	      ID_lb.setHorizontalAlignment(SwingConstants.RIGHT);
	      ID_lb.setBounds(177, 95, 114, 15);
	      contentPane.add(ID_lb);
	      
	      JLabel PW_lb = new JLabel("비밀번호"); //비밀번호 라벨
	      PW_lb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	      PW_lb.setHorizontalAlignment(SwingConstants.RIGHT);
	      PW_lb.setBounds(177, 134, 114, 15);
	      contentPane.add(PW_lb);
	      
	      JLabel Name_lb = new JLabel("이름"); //이름 라벨
	      Name_lb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	      Name_lb.setHorizontalAlignment(SwingConstants.RIGHT);
	      Name_lb.setBounds(177, 173, 114, 15);
	      contentPane.add(Name_lb);
	      
	      JLabel Tel_lb = new JLabel("전화번호"); //전화번호 라벨
	      Tel_lb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	      Tel_lb.setHorizontalAlignment(SwingConstants.RIGHT);
	      Tel_lb.setBounds(177, 212, 114, 15);
	      contentPane.add(Tel_lb);
	      
	      JLabel Birth_lb = new JLabel("생년월일"); //생년월일 라벨
	      Birth_lb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	      Birth_lb.setHorizontalAlignment(SwingConstants.RIGHT);
	      Birth_lb.setBounds(177, 250, 114, 15);
	      contentPane.add(Birth_lb);
	      
	      JLabel Join_title = new JLabel("회원가입"); //회원가입 라벨
	      Join_title.setHorizontalAlignment(SwingConstants.CENTER);
	      Join_title.setFont(new Font("맑은 고딕", Font.BOLD, 18));
	      Join_title.setBounds(0, 20, 684, 52);
	      contentPane.add(Join_title);
	      
	      //중복확인 버튼 클릭 시 이벤트
	      IDcheck_btn.addActionListener(new ActionListener() { 
	         public void actionPerformed(ActionEvent e) {
	        	 JButton b = (JButton)e.getSource();
	        	 
	        	 if(b.getText().equals("중복확인")) {
	        		 if(db.findExistID(ID_text.getText())) {
	        			 JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.", "아이디 중복체크", JOptionPane.ERROR_MESSAGE);
							ID_text.grabFocus();
							return;
	        		 }
		        	 else {
		        		 JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.", "아이디 중복 체크", JOptionPane.INFORMATION_MESSAGE);
		        	 }
	        	 }
	         }
	      });
	      
	    //회원가입 완료 버튼 클릭 시 이벤트
	      Join_fine_btn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
		    	  JButton b = (JButton)e.getSource();
		    	  
	        	  user_id = ID_text.getText();  // 사용자가 입력한 ID
	        	  user_pw = "";
	        	  for(int i=0; i<PW_Text.getPassword().length; i++) {
	        	    	user_pw = user_pw + PW_Text.getPassword()[i];
				  }// 사용자가 입력한 PW
				  user_birth = Birth_text.getText();	// 사용자가 입력한 Birth
				  user_name = Name_text.getText();	// 사용자가 입력한 name
				  user_phone = Tel_text.getText();	// 사용자가 입력한 Tel
	        	  
	        	  if(b.getText().equals("회원가입 완료")) {
						if(user_id.equals("") || user_pw.equals("") || user_birth.equals("") || user_name.equals("") || user_phone.equals(""))
						{
							JOptionPane.showMessageDialog(null, "모든 정보를 기입해주세요", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
							System.out.println("회원가입 실패 > 회원정보 미입력");
						}
						
						else
						{
							 
								if((!(user_phone.length() == 11)))
								{
									JOptionPane.showMessageDialog(null, "전화번호는 11자리로 입력하세요.");
								}
								
								else
								{
									if(db.joinCheck(user_id, user_pw, user_birth, user_name, user_phone)) {

											System.out.println("회원가입 성공");
											JOptionPane.showMessageDialog(null, "회원가입 성공!");
											//db.dbclose();
											new UserLogin(); //회원가입 성공시 ID로그인 페이지로 이동
										} 
									
									else {
										System.out.println("회원가입 실패");
										JOptionPane.showMessageDialog(null, "회원가입 실패");
										ID_text.setText("");
										}
								
								}
								
						}
	        	  }
	         }
	      });
	      
	      frame.setVisible(true); // 화면 출력
	   }
   	public static void main(String[] args) {
   		new UserSignIn();
   	}
}
