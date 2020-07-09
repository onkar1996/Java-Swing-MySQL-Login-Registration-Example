package co.demo.swing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class UserRegistration extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField StudentName;
	private JTextField UserName;
	private JPasswordField Password;

	private JTextField MobileNo;
	private JTextField City;

	private JButton btnNewButton;
	private JButton resetButton;
	private JButton loginButton;

	ButtonGroup bg = new ButtonGroup();
	private JRadioButton rMale;
	private JRadioButton rFemale;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistration frame = new UserRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewUserRegister = new JLabel("New User Register");
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblNewUserRegister.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewUserRegister);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(58, 152, 99, 43);
		contentPane.add(lblName);

		StudentName = new JTextField();
		StudentName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		StudentName.setBounds(214, 151, 228, 50);
		contentPane.add(StudentName);
		StudentName.setColumns(10);

		JLabel GenderLbl = new JLabel("Gender");
		GenderLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GenderLbl.setBounds(58, 243, 110, 29);
		contentPane.add(GenderLbl);

		rMale = new JRadioButton("Male", true);
		rFemale = new JRadioButton("Female");

		rMale.setBounds(214, 240, 100, 30);
		rFemale.setBounds(320, 240, 150, 30);
		bg = new ButtonGroup();
		bg.add(rMale);
		bg.add(rFemale);

		contentPane.add(rMale);
		contentPane.add(rFemale);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(58, 324, 124, 36);
		contentPane.add(lblCity);

		City = new JTextField();
		City.setFont(new Font("Tahoma", Font.PLAIN, 32));
		City.setBounds(214, 320, 228, 50);
		contentPane.add(City);
		City.setColumns(10);

		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(542, 159, 99, 29);
		contentPane.add(lblUsername);

		UserName = new JTextField();
		UserName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		UserName.setBounds(707, 151, 228, 50);
		contentPane.add(UserName);
		UserName.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(542, 245, 99, 24);
		contentPane.add(lblPassword);

		Password = new JPasswordField();
		Password.setFont(new Font("Tahoma", Font.PLAIN, 32));
		Password.setBounds(707, 235, 228, 50);
		contentPane.add(Password);

		JLabel lblMobileNumber = new JLabel("Mobile");
		lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMobileNumber.setBounds(542, 329, 139, 26);
		contentPane.add(lblMobileNumber);

		MobileNo = new JTextField();
		MobileNo.setFont(new Font("Tahoma", Font.PLAIN, 32));
		MobileNo.setBounds(707, 320, 228, 50);
		contentPane.add(MobileNo);
		MobileNo.setColumns(10);

		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentName.setText("");
				MobileNo.setText("");
				City.setText("");
				UserName.setText("");
				Password.setText("");
			}
		});

		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				UserLogin.loginUser(null);
			}
		});

		btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentName = StudentName.getText();
				String mobile = MobileNo.getText();
				String city = City.getText();
				String userName = UserName.getText();
				String gender = (rMale.isSelected()) ? "Male" : "Female";

				int len = mobile.length();
				String password = String.valueOf(Password.getPassword());

				String msg = "" + studentName;
				msg += " \n";
				if (len != 10) {
					JOptionPane.showMessageDialog(btnNewButton, "Enter a valid 10 Digit mobile number");
				}
				try {
					Connection connection = (Connection) DriverManager.getConnection(Configuration.DBUrl,
							Configuration.DBUserName, Configuration.DBPassword);
					String query = "INSERT INTO StudentMaster set StudentName=?,UserName=?,Password=?,Gender=?,MobileNo=?,City=?";
					PreparedStatement stmt = connection.prepareStatement(query);
					stmt.setString(1, studentName);
					stmt.setString(2, userName);
					stmt.setString(3, password);
					stmt.setString(4, gender);
					stmt.setString(5, mobile);
					stmt.setString(6, city);
					if (stmt.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(btnNewButton,
								"Welcome, " + msg + "Your account is sucessfully created");
					} else {
						JOptionPane.showMessageDialog(btnNewButton, "Alredy exist..Please login");
					}
					connection.close();
				} catch (Exception exception) {
					// exception.printStackTrace();
					JOptionPane.showMessageDialog(btnNewButton, "Alredy exist..Please login");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(399, 447, 259, 74);
		contentPane.add(btnNewButton);

		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		resetButton.setBounds(130, 447, 200, 44);
		contentPane.add(resetButton);

		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		loginButton.setBounds(708, 447, 200, 44);
		contentPane.add(loginButton);
	}
}
