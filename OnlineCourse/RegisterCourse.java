import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class RegisterCourse extends JFrame
{
	Connection connection;
	JFrame jframe;
	JPanel panelcenter;
	JLabel appid,apptxt,course;
	JComboBox coursecb;
	JButton register,regcourses;
	String username;
	RegisterCourse(String username)
	{
		this.username=username;
		appid=new JLabel("Application Id");
		apptxt=new JLabel(username);		
		course=new JLabel("Courses");
		String courses[]={"Java","C","C++","HTML","JSP",".NET","Tally"};
		coursecb=new JComboBox(courses);
		register=new JButton("Regiter Course");
		regcourses=new JButton("Registered Courses");
		panelcenter=new JPanel();
		panelcenter.setLayout(new GridLayout(3,2));
		panelcenter.add(appid);
		panelcenter.add(apptxt);
		panelcenter.add(course);
		panelcenter.add(coursecb);
		panelcenter.add(register);
		panelcenter.add(regcourses);
		add(panelcenter,BorderLayout.CENTER);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecourse","root","jaswanth");
		}
		catch(Exception e)
		{}
		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String selcrse=(String)coursecb.getItemAt(coursecb.getSelectedIndex());
				int result = JOptionPane.showConfirmDialog(null, "Confirm your Selected Course "+selcrse,null, JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					try
					{
						PreparedStatement statement=connection.prepareStatement("insert into courselist values(?,?)");
						statement.setString(1,selcrse);
						statement.setString(2,username);
						int rs=statement.executeUpdate();
						if(rs!=0)
						{
							JOptionPane.showMessageDialog(jframe,"Course Registered Successfully");
						}
					}
					catch(Exception ex)
					{}
				} 
			}
		});
		regcourses.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String rgs="The Registered Courses \n";
				try
				{
					PreparedStatement statement1=connection.prepareStatement("select coursename from courselist where appid=?");
					statement1.setString(1,username);
					ResultSet rs1=statement1.executeQuery();
					while(rs1.next())
					{
						rgs=rgs+rs1.getString(1)+"\n";
					}
					JOptionPane.showMessageDialog(jframe,rgs);
				}
				catch(Exception exe)
				{}
			}
		});
		setTitle("Register Course");
		setSize(400,300);
		setVisible(true);
	}
}