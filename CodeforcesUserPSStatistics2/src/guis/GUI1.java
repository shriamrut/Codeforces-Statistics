package guis;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import listeners.Actionlistener;
public class GUI1 {

	public static JFrame frame;
	private JPanel panel;
	private JButton btnNewButton;
	public static JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI1();
					GUI1.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public GUI1() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Main Page");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setBackground(Color.ORANGE);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setToolTipText("Enter your codeforces user id ");
		
		Actionlistener ac=new Actionlistener();
		btnNewButton = new JButton("Get Statistics");
		btnNewButton.addActionListener(ac);
		btnNewButton.setBackground(Color.cyan);
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.SOUTH, btnNewButton, -122, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnNewButton, -160, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, textField, 72, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField, -173, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textField, 113, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textField, -116, SpringLayout.EAST, panel);
		panel.setLayout(sl_panel);
		panel.add(textField);
		panel.add(btnNewButton);
	}
}
