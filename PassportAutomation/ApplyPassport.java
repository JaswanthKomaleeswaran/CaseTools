import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
class ApplyPassport extends JFrame
{
	Connection connection;
	//Frame frame;
	JFrame jframe;
	JLabel appid,name,app,nm,nulllabel,passapp,passsend,passexp,passappdt,passsenddt,passexpdt;
	JButton apply;
	JPanel paneldown,panelcenter;
	String user="",mail="",mobile="",dateappstr="",sddatestr="",expdatestr="";
	String apid;
	int status=0;
	ApplyPassport(String recusername)
	{
		apid=recusername;
		jframe=new JFrame();
		panelcenter=new JPanel();
		paneldown=new JPanel();
		appid=new JLabel("Application Id");
		name=new JLabel("Name");
		passapp=new JLabel("Passport Apply Date");
		passsend=new JLabel("Passport Send Date");
		passexp=new JLabel("Passport Expiry Date");
		app=new JLabel();
		nm=new JLabel();
		passappdt=new JLabel();
		passsenddt=new JLabel();
		passexpdt=new JLabel();
		nulllabel=new JLabel(" ");
		apply=new JButton("Apply");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/passport","root","jaswanth");
		}
		catch(Exception E){}
		try
		{
			PreparedStatement statement=connection.prepareStatement("select name,applystatus,mail,mobile from userdetails where appid=?");
			statement.setString(1,apid);
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				user=rs.getString(1);
				status=rs.getInt(2);	
				mail=rs.getString(3);
				mobile=rs.getString(4);
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		final String username=user;
		final String mob=mobile;
		final String mailid=mail;
		app.setText(apid);
		nm.setText(user);
		if(status==0)
		{
			panelcenter.setLayout(new GridLayout(3,2));
			panelcenter.add(appid);
			panelcenter.add(app);
			panelcenter.add(name);
			panelcenter.add(nm);
			panelcenter.add(apply);
			panelcenter.add(nulllabel);
			apply.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						DateFormat dtformat=new SimpleDateFormat("yyyy/MM/dd");
						Date applydate=new Date();
						Calendar c=Calendar.getInstance();
						Calendar c1=Calendar.getInstance();
						c.setTime(applydate); 
						c.add(Calendar.DATE, 7);
						Date senddate = c.getTime();
						c1.setTime(applydate); 
						c1.add(Calendar.DATE, 7305);
						Date expirydt= c1.getTime();
						String dtapply=dtformat.format(applydate).toString();
						String sdapply=dtformat.format(senddate).toString();
						String expiry=dtformat.format(expirydt).toString();
						PreparedStatement ps=connection.prepareStatement("update userdetails set applystatus=1 where appid=?");
						ps.setString(1, apid);
						int rs=ps.executeUpdate();
						PreparedStatement ps1=connection.prepareStatement("insert into passportdetails values(?,?,?,?,?,?,?)");
						ps1.setString(1, apid);
						ps1.setString(2, username);
						ps1.setString(3, mob);
						ps1.setString(4, mailid);
						ps1.setString(5, dtapply);
						ps1.setString(6, sdapply);
						ps1.setString(7, expiry);
						int rs1=ps1.executeUpdate();
						if(rs1!=0 && rs!=0)
						{
							JOptionPane.showMessageDialog(jframe,"Successfully Applied!!!Please Login Again!!!");
							dispose();
						}
						//System.out.println(dtapply+sdapply+expiry);
						
					}
					catch(Exception ex)
					{
						
					}
					
					
				}
			});
		}
		else
		{
			try
			{
				PreparedStatement statement1=connection.prepareStatement("select applydate,senddate,expdate from passportdetails where appid=?");
				statement1.setString(1,apid);
				//System.out.println("statement");
				ResultSet rs2=statement1.executeQuery();
				//String user1,mail1;
				//long mobile1;
				if(rs2.next())
				{
					//user=rs2.getString(2);
					//mobile=rs2.getString(3);	
					//mail=rs2.getString(4);
					dateappstr=rs2.getString(1);
					sddatestr=rs2.getString(2);
					expdatestr=rs2.getString(3);
					
				}
				//System.out.println(apid+user);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//System.out.println(dateappstr+sddatestr+expdatestr);
			passappdt=new JLabel(dateappstr);
			passsenddt=new JLabel(sddatestr);
			passexpdt=new JLabel(expdatestr);
			
			
			panelcenter.setLayout(new GridLayout(6,6));
			panelcenter.add(appid);
			panelcenter.add(app);
			panelcenter.add(name);
			panelcenter.add(nm);
			panelcenter.add(passapp);
			panelcenter.add(passappdt);
			panelcenter.add(passsend);
			panelcenter.add(passsenddt);
			panelcenter.add(passexp);
			panelcenter.add(passexpdt);
			
			
		}
		add(panelcenter,BorderLayout.CENTER);
		setTitle("Apply Passport");
		setSize(600,250);
		setVisible(true);
	}
	/*	
	public static void main(String args[])
	{
		new ApplyPassport();
	}
	*/
}