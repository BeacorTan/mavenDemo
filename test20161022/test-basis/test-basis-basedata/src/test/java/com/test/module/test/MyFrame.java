package com.test.module.test;

public class MyFrame {
	public static void main(String[] args) {
		myFrame();
	}

	private static void myFrame() {/*
		//Frame 缁愭褰�
		JFrame fram=new JFrame("MyProgram");
		fram.setLayout(null);//濞屸剝婀佺敮鍐ㄧ湰
		//鏉堟挸鍙嗗锟�		final JTextField inputPwd=new JTextField();
		//鐟欙絽鐦戦崥搴ｆ畱鏉堟挸鍤锟�		final JTextField outPwd=new JTextField();
		JButton jiami=new JButton("");
		JButton jiemi=new JButton("");
		JLabel jLabel=new JLabel("");
		fram.setSize(285, 154);
		inputPwd.setBounds(60, 15, 200, 25);
		jLabel.setBounds(20, 15, 100, 25);
		jiami.setBounds(23, 50, 100, 25);
		jiami.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text=inputPwd.getText();
				if(null==text||"".equals(text)){
//					JOptionPane.sho
//					JOptionPane.showMessageDialog(null, "闁告梻濮撮惁鎴﹀礃閸涱収鍟囧☉鎾崇Х閸忔ɑ绋夐搹鍏夋晞!", 
//							"婵炴挴鏅犺灈闁圭粯鍔楅妵锟� JOptionPane.INFORMATION_MESSAGE");
					return;
				}
				outPwd.setText(text);
			}
		});
		jiemi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text=inputPwd.getText();
				if(null==text||"".equals(text)){
//					JOptionPane.showMessageDialog(null, "閻熸瑱绲介惁鎴﹀礃閸涱収鍟囧☉鎾崇Х閸忔ɑ绋夐搹鍏夋晞!", 
//							"婵炴挴鏅犺灈闁圭粯鍔楅妵锟� JOptionPane.INFORMATION_MESSAGE");
					return;
				}
				outPwd.setText(text);
				
			}
		});
		//jiemihou.setText("闁瑰瓨鍨堕惀鍛村嫉婢跺﹦鏉烘慨妯绘煥閹革拷);
		jiemi.setBounds(155, 50, 100, 25);
		outPwd.setBounds(20, 90, 240, 25);
		fram.add(inputPwd);
		fram.add(jiami);
		fram.add(jiemi);
		fram.add(jLabel);
		fram.add(outPwd);
		//鐠佸墽鐤嗛懗灞炬珯妫版粏澹�
		fram.getContentPane().setBackground(null);
		//
		fram.setResizable(false);
		//閻忕偛绻愮粻椋庯拷绾懓鏉�
		Dimension  dimension=Toolkit.getDefaultToolkit().getScreenSize();
		// 濮掓稒顭堥濠氬及閸撗佷粵濞达絽绉堕悿锟�		fram.setLocation((int)dimension.getWidth()/2-fram.getWidth()/2,(int) dimension.getHeight()/2-fram.getHeight());
		//闁稿繑濞婂Λ鎾箰婢舵劖灏﹂柨娑樼焸閿熸枻鎷烽柛鎴ｆ閳诲吋鎯旈敓锟�		fram.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//闁哄嫬澧介妵姘辩玻濡わ拷缍�
		fram.setVisible(true);
	*/}

	/*private static void test1() {
		JFrame f=new JFrame("beacor 8023 TH");
		f.setLayout(new GridLayout(1,1));
		//闁哄秴娲ㄩ锟�		JLabel label=new JLabel("闁告繂鐗嗛幖锟�;
		JButton start=new JButton("鐎殿噯鎷峰┑顕嗘嫹");
		JButton end=new JButton("缂備焦鎸诲锟�;
		label.setBounds(45, 5, 150, 20);
		start.setBounds(10, 30, 80, 20);
		end.setBounds(100, 30, 80, 20);
		JButton jToggleButton=new JButton("鐎瑰憡鐓￠敓钘夘樅閼碉拷);
		jToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JFrame("xxx").setVisible(true);
			}
		});
		f.setSize(200, 100);
		f.add(jToggleButton);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}*/
}

