//<applet code="Login" width="600" height="250" archieve="mysqlconnector.jar"></applet>
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Login extends Applet
{
	Connection connection;
	JFrame jframe;
	Label uname,pass,signup;
	TextField usertext,passtext;
	Button login,register;
	Panel paneldown,panelcenter;
	public void init()
	{
		jframe=new JFrame();
		panelcenter=new Panel();
		paneldown=new Panel();
		uname=new Label("UserName");
		pass=new Label("Password");
		signup=new Label("Are You a new User");
		usertext=new TextField();
		passtext=new TextField();
		login=new Button("Login");
		register=new Button("SignUP");
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
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/inventoryms","root","jaswanth");		
				PreparedStatement statement=connection.prepareStatement("select pass from customerdetails where cusid=?");
				statement.setString(1,username);
				ResultSet rs=statement.executeQuery();
				if(rs.next())
				{
					String passq=rs.getString(1);
					if(password.equals(passq))
					{
						JOptionPane.showMessageDialog(jframe,"Login Successs");
						Payment payment=new Payment(username);
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
				//Registration registration=new Registration();
			}
		});
		

	}
}