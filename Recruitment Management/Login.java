import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class Login extends JFrame
{
	Connection connection;
	JFrame jframe;
	BorderLayout layout;
	JLabel uname,pass,signup;
	JTextField usertext,passtext;
	JButton login,register;
	JPanel paneldown,panelcenter;
	Login()
	{
		jframe=new JFrame();
		layout=new BorderLayout();
		panelcenter=new JPanel();
		paneldown=new JPanel();
		uname=new JLabel("Application ID");
		pass=new JLabel("Password");
		signup=new JLabel("Are You a new User");
		usertext=new JTextField();
		passtext=new JTextField();
		login=new JButton("Login");
		register=new JButton("SignUP");
		paneldown.add(signup);
		paneldown.add(register);
		add(paneldown,BorderLayout.SOUTH);
		panelcenter.setLayout(new GridLayout(3,2));
		panelcenter.add(uname);
		panelcenter.add(usertext);
		panelcenter.add(pass);
		panelcenter.add(passtext);
		panelcenter.add(login);
		add(panelcenter,BorderLayout.CENTER);
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
				String username=usertext.getText();
				String password=passtext.getText();
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/examregister","root","jaswanth");		
				PreparedStatement statement=connection.prepareStatement("select pass from studentdetails where appid=?");
				statement.setString(1,username);
				ResultSet rs=statement.executeQuery();
				if(rs.next())
				{
					String passq=rs.getString(1);
					if(password.equals(passq))
					{
						JOptionPane.showMessageDialog(jframe,"Login Successs");
						Recruit rec=new Recruit(username);
					}
					else
					{
						JOptionPane.showMessageDialog(jframe,"Password is wrong");
					}					
				}
				else
				{
					JOptionPane.showMessageDialog(jframe,"Invalid User");
				}
				connection.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Registration registration=new Registration();
			}
		});
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
		setTitle("Login");
		setSize(600,250);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new Login();
	}
}

