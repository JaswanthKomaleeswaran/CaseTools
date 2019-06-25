import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;
class Registration extends JFrame
{
	//Frame frame;
	Connection connection;
	JFrame jframe;
	JPanel panelcenter;
	JLabel appid,uname,pass,fname,mname,dob,gender,birthplace,address;
	JLabel state,country,mobile,mailid,qualification;
	JTextField apptext,usertext,passtext,ftext,mtext,dobtext,gendertext,birthtext,addtext;
	JTextField statetext,countrytext,mobiletext,mailtext,qualtext;
	JButton register;
	Registration()
	{
		//frame=new Frame();
		jframe=new JFrame();
		panelcenter=new JPanel();
		appid=new JLabel("Application Id");
		uname=new JLabel("UserName");
		pass=new JLabel("Password");
		fname=new JLabel("Father Name");
		mname=new JLabel("Mother Name");
		dob=new JLabel("Date Of Birth");
		gender=new JLabel("Gender");
		birthplace=new JLabel("Birth Place");
		address=new JLabel("Address");
		state=new JLabel("State");
		country=new JLabel("Country");
		mobile=new JLabel("Mobile");
		mailid=new JLabel("Mail Id");
		qualification=new JLabel("Qualification");
		register=new JButton("Register");
		apptext=new JTextField();
		usertext=new JTextField();
		passtext=new JTextField();
		ftext=new JTextField();
		mtext=new JTextField();
		dobtext=new JTextField();
		gendertext=new JTextField();
		birthtext=new JTextField();
		addtext=new JTextField();
		statetext=new JTextField();
		countrytext=new JTextField();
		mobiletext=new JTextField();
		mailtext=new JTextField();
		qualtext=new JTextField();
		setSize(600,500);
		setTitle("Registration");
		setVisible(true);
		panelcenter=new JPanel();
		panelcenter.setLayout(new GridLayout(15,2));
		panelcenter.add(appid);
		panelcenter.add(apptext);
		panelcenter.add(uname);
		panelcenter.add(usertext);
		panelcenter.add(pass);
		panelcenter.add(passtext);
		panelcenter.add(fname);
		panelcenter.add(ftext);
		panelcenter.add(mname);
		panelcenter.add(mtext);
		panelcenter.add(dob);
		panelcenter.add(dobtext);
		panelcenter.add(gender);
		panelcenter.add(gendertext);
		panelcenter.add(address);
		panelcenter.add(addtext);
		panelcenter.add(state);
		panelcenter.add(statetext);
		panelcenter.add(country);
		panelcenter.add(countrytext);
		panelcenter.add(birthplace);
		panelcenter.add(birthtext);
		panelcenter.add(mobile);
		panelcenter.add(mobiletext);
		panelcenter.add(mailid);
		panelcenter.add(mailtext);
		panelcenter.add(qualification);
		panelcenter.add(qualtext);
		panelcenter.add(register);
		add(panelcenter,BorderLayout.CENTER);
		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					String appid=apptext.getText();
					String name=usertext.getText();
					String pass=passtext.getText();
					String ftext1=ftext.getText();
					String mtext1=mtext.getText();
					String dob=dobtext.getText();
					String gender=gendertext.getText();
					String address=addtext.getText();
					String state=statetext.getText();
					String country=countrytext.getText();
					String birthplace=birthtext.getText();
					//long mobile=Long.parseLong(mobiletext.getText());
					String mobile=mobiletext.getText();
					String mail=mailtext.getText();
					String qualification=qualtext.getText();
					Class.forName("com.mysql.jdbc.Driver");
					connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/passport","root","jaswanth");
					PreparedStatement ps=connection.prepareStatement("insert into userdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1, appid);
					ps.setString(2, name);
					ps.setString(3, pass);
					ps.setString(4, ftext1);
					ps.setString(5, mtext1);
					ps.setString(6, dob);
					ps.setString(7, gender);
					ps.setString(8, address);
					ps.setString(9, state);
					ps.setString(10, country);
					ps.setString(11, birthplace);
					ps.setString(12, mobile);
					ps.setString(13, mail);
					ps.setString(14, qualification);
					ps.setInt(15,0);
					int rs=ps.executeUpdate();
					if(rs!=0)
					{
						JOptionPane.showMessageDialog(jframe,"Successfully Registered");
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(jframe,"Registration Failed");
					}
					connection.close();
				}
				catch(SQLException sq)
				{
					JOptionPane.showMessageDialog(jframe,"SQl Exception");
				}
				catch (Exception ex) {
					//JOptionPane.showMessageDialog(jframe,"You have already registered");
					JOptionPane.showMessageDialog(jframe,ex);
					
				}
				
						
				
			}
		});
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);	
			}		
		});
	}
	public static void main(String args[])
	{
		new Registration();
	}
}
