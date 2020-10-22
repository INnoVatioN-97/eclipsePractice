package practice_SwingGUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Lotto_KYI extends JFrame {
	JPanel newPanel = new JPanel();
	JLabel fortuneNum = new JLabel("���ϴ� �ż��� �����ڸ� �Է��ϼ���.");
	JTextArea text;
	JTextField enterSet = new JTextField("�ִ� 6��Ʈ���� �Է�", 27);
	JTextField[] enterHappy = new JTextField[3];

	JButton go = new JButton("�ζ� ��÷");
	JButton clr = new JButton("�ʱ�ȭ");

	public Lotto_KYI() {
		setTitle("Lotto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fortuneNum.setSize(300, 50);
		newPanel.setLayout(new FlowLayout());
		setContentPane(newPanel);

		enterHappy[0] = new JTextField("����� ���ڸ� �Է�", 27);
		enterHappy[1] = new JTextField(27);
		enterHappy[2] = new JTextField(27);

		text = new JTextArea(10, 15);
		text.setFont(new Font("Arial", Font.ITALIC, 20));
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		text.setText("");
		
		newPanel.add(fortuneNum);
		newPanel.add(text);
		newPanel.add(enterSet);

		for (int i = 0; i < enterHappy.length; i++) {
			newPanel.add(enterHappy[i]);
		}
		go.addActionListener(new Listener());
		clr.addActionListener(new Listener());
		newPanel.add(go);
		newPanel.add(clr);

		setSize(300, 700);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Lotto_KYI();

	}

	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText() == "�ζ� ��÷") {
				int set = Integer.parseInt(enterSet.getText());
				if (set > 6 || set < 1) {
					JOptionPane.showMessageDialog(null, "1~6������ ���� �Է��Ͽ�.",
							"Message", JOptionPane.ERROR_MESSAGE);
					enterSet.setText("");
					enterSet.setFocusable(true);
					enterSet.requestFocus();
					return;
				}

				int[] vals = new int[3];
				for (int i = 0; i < enterHappy.length; i++) {
					vals[i] = Integer.parseInt(enterHappy[i].getText());
				}

				CalculationThread th = new CalculationThread();
				th.run();
			} else {
				text.setText("");
				for (int i = 0; i < enterHappy.length; i++)
					enterHappy[i].setText("");
				enterSet.setText("");
			}
		}
	}

	class CalculationThread extends Thread {
		private int[] luckyN = new int[3]; // �޾ƿ��� ���� ���� �迭
		private int set; // ��� ��Ʈ
		private int[] finalN = new int[6]; // ���� ������ ����ڵ�
		private long lucks = 0;
		Random rand;

		public CalculationThread() {
			set = Integer.parseInt(enterSet.getText());
			for (int i = 0; i < enterHappy.length; i++) {
				luckyN[i] = Integer.parseInt(enterHappy[i].getText());
				lucks += (long) luckyN[i];
			}
			rand = new Random(lucks); // ����ȣ���� �� ���ؼ� �� �ٸ� �������� ������.
		}

		public void run() {
			text.setText("");
			for (int j = 0; j < set; j++) { // ������ ��Ʈ�� ��ŭ �ζ� ���
				
				text.append("Lotto No." + (j + 1) + "\n");

				for (int i = 0; i < 6; i++) {
					finalN[i] = (int) (Math.random() * 45 + 1);

					int randNum = rand.nextInt(45);
					//�� �ٸ� ������ ���� ���� ū ���� ���� �ζǹ�ȣ�� ����.
					if (finalN[i] < randNum) { 
						finalN[i] = randNum;
					}
					//�ߺ��˻�
					if (i > 0 && (finalN[i] == finalN[i - 1])) {
						i--;
						continue;
					}
				}
				for (int i = 0; i < 6; i++) {
					text.append(finalN[i] + " ");
					if ((i + 1) % 6 == 0)
						text.append("\n");
				}
				text.append("\n");
			}

		}

	}
}
