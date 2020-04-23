import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class SwingCalendar extends JFrame {
	Calendar today = Calendar.getInstance();
	Calendar lastca = Calendar.getInstance();
	Calendar ca = Calendar.getInstance();
	Calendar cal = Calendar.getInstance();

	MyP1 myp1;
	MyP2 myp2;
	MyP3 myp3;

	int dayIndex; // 요일
	int lastDay; // 달마다 다른 날짜 수
	int y, m;
	int DayOfMon = today.get(Calendar.DAY_OF_MONTH);
	int j = 0;
	int n = DayOfMon;
	String s = "";

	JLabel memoLabel = new JLabel();

	SwingCalendar() {
		setTitle("Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();

		myp2 = new MyP2();
		myp3 = new MyP3();
		myp1 = new MyP1();

		myp1.settingJFrame.setPanelColor(Color.lightGray);
		myp1.settingJFrame.setArrowColor(Color.white, Color.black);
		myp1.settingJFrame.setFuncColor(Color.white, Color.black);
		myp1.settingJFrame.setTextColor(Color.black);
		myp1.settingJFrame.setDayColor(Color.orange, Color.black, 3, Color.black);
		myp1.settingJFrame.setDateColor(Color.white, Color.gray);

		c.add(myp2, BorderLayout.CENTER);
		c.add(myp1, BorderLayout.NORTH);
		c.add(myp3, BorderLayout.SOUTH);

		setSize(500, 500);
		setResizable(false);
		setVisible(true);

	}

	class MyP1 extends JPanel {
		JButton t;
		JButton left, right;// 좌우 화살표 버튼
		JButton gotoday;// Today
		JButton settingBtn;// 설정버튼 (테마)
		SettingJFrame settingJFrame;

		Color datetextColor;// 달력의 일 버튼들의 텍스트 색상
		Color dateColor;// 이번달 일 버튼의 색상
		Color nondateColor;// 이번달 일이 아닌 버튼들의 색상

		MyP1() {
			dateColor = Color.white;
			nondateColor = Color.GRAY;
			datetextColor = Color.black;

			setLayout(new FlowLayout());
			setBackground(Color.LIGHT_GRAY);
			left = new JButton("◀");
			right = new JButton("▶");
			gotoday = new JButton("Today");
			t = new JButton("");
			settingBtn = new JButton("설정");
			settingJFrame = new SettingJFrame();
			setDays(ca);

			t.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String mydate = JOptionPane.showInputDialog("년/월을 공백없이 입력하세요");
					y = Integer.parseInt(mydate.substring(0, 4));
					System.out.println(y);
					m = Integer.parseInt(mydate.substring(4)) - 1;
					System.out.println(m);
					ca.set(y, m, ca.get(Calendar.DAY_OF_WEEK));
					setDays(ca);
				}
			});

			left.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (m >= 1) {
						lastca.setTime(ca.getTime());
						System.out.println(lastca);
						m = m - 1;
						ca.set(y, m, 1);
						setDays(ca);
					} else {
						lastca.setTime(ca.getTime());
						System.out.println(lastca);
						y = y - 1;
						m = 11;
						ca.set(y, m, 1);
						setDays(ca);
					}

				}
			});
			right.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (m < 11) {
						lastca.setTime(ca.getTime());
						System.out.println(lastca);
						m = m + 1;
						ca.set(y, m, 1);
						setDays(ca);
					} else {
						lastca.setTime(ca.getTime());
						System.out.println(lastca);
						y = y + 1;
						m = 0;
						ca.set(y, m, 1);
						setDays(ca);
					}
				}
			});

			gotoday.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lastca.setTime(ca.getTime());
					System.out.println(lastca);
					y = today.get(Calendar.YEAR);
					m = today.get(Calendar.MONTH);
					ca.set(y, m, DayOfMon);
					setDays(ca);

				}
			});

			settingBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (settingJFrame == null) {
						settingJFrame = new SettingJFrame();
					}
					settingJFrame.setVisible(true);
				}
			});
			add(settingBtn);
			add(left);
			add(t);
			add(right);
			add(gotoday);
		}

		class SettingJFrame extends JFrame {
			JButton defaultTh;
			JButton blackTh;
			JButton blueTh;
			JButton pinkTh;

			SettingJFrame() {
				setTitle("테마설정");
				setLayout(new FlowLayout());

				makeGUI();

				setResizable(false);
				setSize(200, 120);
			}

			public void makeGUI() {
				defaultTh = new JButton("기본");
				defaultTh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setPanelColor(Color.lightGray);
						setArrowColor(Color.white, Color.black);
						setFuncColor(Color.white, Color.BLACK);
						setTextColor(Color.black);
						setDayColor(Color.orange, Color.black, 3, Color.black);
						setDateColor(Color.white, Color.gray);
					}
				});
				blackTh = new JButton("Black");
				blackTh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setPanelColor(Color.black);
						setArrowColor(Color.white, Color.black);
						setFuncColor(Color.white, Color.BLACK);
						setTextColor(Color.white);
						setDayColor(Color.white, Color.white, 0, Color.black);
						setDateColor(Color.black, Color.gray);
					}
				});
				blueTh = new JButton("Blue");
				blueTh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setPanelColor(new Color(102, 115, 255));
						setArrowColor(Color.white, Color.black);
						setFuncColor(Color.white, Color.BLACK);
						setTextColor(Color.white);
						setDayColor(Color.white, Color.white, 0, Color.black);
						setDateColor(new Color(75, 89, 242), Color.gray);
					}
				});

				pinkTh = new JButton("Pink");
				pinkTh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setPanelColor(new Color(251, 208, 217));
						setArrowColor(Color.white, Color.black);
						setFuncColor(Color.white, Color.black);
						setTextColor(Color.white);
						setDayColor(Color.white, Color.white, 0, Color.black);
						setDateColor(Color.pink, Color.gray);
					}
				});

				add(defaultTh);
				add(blackTh);
				add(blueTh);
				add(pinkTh);

			}

			public void setPanelColor(Color c) {
				myp1.setBackground(c);
				myp2.setBackground(c);
				myp3.setBackground(c);
			}

			public void setDayColor(Color back, Color border, int thick, Color text) {
				for (int i = 0; i < myp2.l_day.length; i++) {
					myp2.l_day[i].setBackground(back);
					myp2.l_day[i].setBorder(new LineBorder(border, thick));
				}
				for (int i = 1; i <= 5; i++) {
					myp2.l_day[i].setForeground(text);
				}
			}

			public void setDateColor(Color c1, Color c2) {
				myp1.dateColor = c1;
				myp1.nondateColor = c2;
				myp1.setDays(ca);
			}

			public void setArrowColor(Color back, Color text) {
				myp1.left.setBackground(back);
				myp1.right.setBackground(back);
				myp1.left.setForeground(text);
				myp1.right.setForeground(text);
			}

			public void setFuncColor(Color back, Color text) {
				myp1.settingBtn.setBackground(back);
				myp1.gotoday.setBackground(back);
				myp1.t.setBackground(back);
				myp3.clearBut.setBackground(back);
				myp3.delBut.setBackground(back);
				myp3.saveBut.setBackground(back);
				myp1.settingBtn.setForeground(text);
				myp1.gotoday.setForeground(text);
				myp1.t.setForeground(text);
				myp3.clearBut.setForeground(text);
				myp3.delBut.setForeground(text);
				myp3.saveBut.setForeground(text);
			}

			public void setTextColor(Color c) {
				myp1.datetextColor = c;
				memoLabel.setForeground(c);
			}
		}

		public void setDays(Calendar inputca) {
			y = inputca.get(Calendar.YEAR);
			m = inputca.get(Calendar.MONTH);
			
			t.setText("   " + y + "년 " + (m + 1) + "월" + "   ");// t버튼 재설정
			cal.set(y, m, 1); // 입력월의 초일로 날짜 셋팅
			n = cal.get(Calendar.DAY_OF_WEEK);// n=1~7(일~토)
			
			JButton[] b_date = myp2.b_date;
			JTextArea memoArea = myp3.memoArea;
			
			for (int i = n - 1; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH) + n - 1; i++) {
				int j = i;
				if (b_date[j].getActionListeners().length == 0) {// 이미 action리스너를 달아뒀다면 또 추가하지않는다
					b_date[j].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							DayOfMon = j - n + 2;
							System.out.println("ActionListener 작동 " + DayOfMon);
							// -------------------------------저장된 메모 불러오기----------------------------------
							memoLoad();
							// -------------------------------------------------------------------
						}
					});
				}
				
				memoLabel.setText(
						Integer.toString(y) + "년 " + Integer.toString(m + 1) + "월 " + Integer.toString(DayOfMon) + "일");
			}
			cal.add(Calendar.DATE, -n + 1);
			// 일을 첫번째 주의 일요일(좌상단)

			// 버튼에 String넣고 색을 넣는 반복문
			for (int i = 0; i < b_date.length; i++, cal.add(Calendar.DATE, 1)) {// i++,날짜++
				s = Integer.toString(cal.get(Calendar.DATE));
				b_date[i].setText(s);
				b_date[i].setForeground(datetextColor);
				if (cal.get(Calendar.MONTH) != m) { // 월(month)이 다른 경우
					b_date[i].setBackground(nondateColor);
					b_date[i].setEnabled(false);
				} else {
					b_date[i].setBackground(dateColor);
					b_date[i].setEnabled(true);
				}
			}
			int lastDOM = DayOfMon;
			Calendar mycal=Calendar.getInstance();
	        mycal.set(y, m,1);
	        int maxday=mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        System.out.println(maxday);
	        System.out.println(lastDOM);
	        if(lastDOM>maxday)
	           DayOfMon=maxday;
	        System.out.println(DayOfMon);
			memoLoad();
		}

		public void memoLoad() {

			String readstr;

			BufferedReader BR = null;
			try {
				File file = new File("MemoData/" + y + ((m + 1) < 10 ? "0" : "") + (m + 1) + (DayOfMon < 10 ? "0" : "")
						+ DayOfMon + ".txt");
				BR = new BufferedReader(new FileReader(file));
				while ((readstr = BR.readLine()) != null) {

					myp3.memoArea.setText(readstr);
				}
			} catch (IOException e1) {// 파일이 만들어지지않은상태에서 발생하는 에러-> 즉 아직 memoArea는 비어있어야함
				System.out.println("MemoData/" + y + ((m + 1) < 10 ? "0" : "") + (m + 1) + (DayOfMon < 10 ? "0" : "")
						+ DayOfMon + ".txt" + "은 아직 만들어지지 않았습니다.");
				myp3.memoArea.setText("");
			} finally {
				try {
					if (BR != null)
						BR.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			memoLabel.setText(
					Integer.toString(y) + "년 " + Integer.toString(m + 1) + "월 " + Integer.toString(DayOfMon) + "일");
		}
	}

	class MyP2 extends JPanel {
		JLabel l_day[] = new JLabel[7]; // 요일 라벨
		JButton b_date[] = new JButton[42]; // 날짜 버튼

		MyP2() {
			setBackground(Color.lightGray);
			setLayout(new GridLayout(7, 7, 1, 1));
			setDayLabel();
			setDateLabel();
			for (int i = 0; i < l_day.length; i++) {
				l_day[i].setOpaque(true);
				l_day[i].setBackground(Color.ORANGE);
				l_day[i].setBorder(new LineBorder(Color.BLACK, 3));
				add(l_day[i]);
			}
			for (int i = 0; i < b_date.length; i++)
				add(b_date[i]);

		}

		public void setDayLabel() {
			l_day[0] = new JLabel(" 일 ", JLabel.CENTER);
			l_day[0].setForeground(Color.RED);
			l_day[1] = new JLabel(" 월 ", JLabel.CENTER);
			l_day[2] = new JLabel(" 화 ", JLabel.CENTER);
			l_day[3] = new JLabel(" 수 ", JLabel.CENTER);
			l_day[4] = new JLabel(" 목 ", JLabel.CENTER);
			l_day[5] = new JLabel(" 금 ", JLabel.CENTER);
			l_day[6] = new JLabel(" 토 ", JLabel.CENTER);
			l_day[6].setForeground(Color.BLUE);
		}

		public void setDateLabel() {
			for (int i = 0; i < b_date.length; i++) {
				b_date[i] = new JButton();
			}
		}
	}

	class MyP3 extends JPanel {
		JTextArea memoArea;
		JScrollPane memoAreaSP;
		JPanel memoPanel;
		JPanel memoSubPanel;
		JButton saveBut;
		JButton delBut;
		JButton clearBut;

		MyP3() {
			setBackground(Color.lightGray);
			memoPanel = new JPanel();
			memoPanel.setOpaque(false);
			memoArea = new JTextArea(5, 20);
			memoArea.setLineWrap(true);
			memoArea.setWrapStyleWord(true);
			memoAreaSP = new JScrollPane(memoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			memoSubPanel = new JPanel();
			memoSubPanel.setOpaque(false);

			saveBut = new JButton("저장");
			saveBut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						File f = new File("MemoData");
						if (!f.isDirectory())
							f.mkdir();

						String memo = memoArea.getText();
						if (memo.length() > 0) {
							BufferedWriter out = new BufferedWriter(
									new FileWriter("MemoData/" + y + ((m + 1) < 10 ? "0" : "") + (m + 1)
											+ (DayOfMon < 10 ? "0" : "") + DayOfMon + ".txt"));
							String str = memoArea.getText();
							out.write(str);
							out.close();

						}

					} catch (IOException e) {

					}

				}
			});
			delBut = new JButton("삭제");
			delBut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					memoArea.setText("");
					File f = new File("MemoData/" + y + ((m + 1) < 10 ? "0" : "") + (m + 1) + (DayOfMon < 10 ? "0" : "")
							+ DayOfMon + ".txt");
					if (f.exists()) {
						f.delete();

					}
				}
			});
			clearBut = new JButton("비우기");
			clearBut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					memoArea.setText(null);
				}
			});

			memoSubPanel.add(saveBut);
			memoSubPanel.add(delBut);
			memoSubPanel.add(clearBut);
			memoPanel.setLayout(new BorderLayout());
			memoPanel.add(memoLabel, BorderLayout.NORTH);
			memoPanel.add(memoAreaSP, BorderLayout.CENTER);
			memoPanel.add(memoSubPanel, BorderLayout.SOUTH);
			add(memoPanel);

			// JButton AddScd = new JButton("일정추가");
			// add(AddScd);
			// AddScd.addActionListener(new ActionListener() {
			// public void actionPerformed(ActionEvent e) {
			// String s = JOptionPane.showInputDialog("일정");
			// System.out.println(s);
			// memoArea.append(s);
			// }
			// });
		}
	}

	public static void main(String[] args) {
		new SwingCalendar();
	}
}
