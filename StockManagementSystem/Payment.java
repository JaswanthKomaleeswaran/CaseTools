import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
class Payment extends JFrame
{	
	JFrame jframe;
	Connection connection;
	String username;
	JPanel panelcenter;
	JLabel uname,userlabel,costlabel,purchaseprice,cardno,expirydate,cvv,mob;
	JTextField card,expiry,cvvt,mobno;
	JButton paybill,confirm;
	String mobile,totalcost,totalpr;
	Payment(String username)
	{
		this.username=username;
		panelcenter=new JPanel();
		uname=new JLabel("UserName");
		userlabel=new JLabel(username);
		costlabel=new JLabel("Total Purchase Price");
		purchaseprice=new JLabel();
		mob=new JLabel("Mobile");
		mobno=new JTextField();
		paybill=new JButton("Pay Bill");
		Random r=new Random();
		int price=r.nextInt(10000);
		int price1=r.nextInt(10000);
		totalpr=String.valueOf(price+price1);
		purchaseprice.setText(totalpr);
		panelcenter.setLayout(new GridLayout(4,2));
		panelcenter.add(uname);
		panelcenter.add(userlabel);
		panelcenter.add(costlabel);
		panelcenter.add(purchaseprice);
		panelcenter.add(mob);
		panelcenter.add(mobno);
		panelcenter.add(paybill);
		add(panelcenter,BorderLayout.CENTER);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/inventoryms","root","jaswanth");		
			PreparedStatement statement=connection.prepareStatement("select mobile,totalcost from customerdetails where cusid=?");
			statement.setString(1,username);
			ResultSet rs=statement.executeQuery();
			while(rs.next())
			{
				mobile=rs.getString(1);
				totalcost=rs.getString(2);
			}
		}
		catch(Exception excep)
		{}
		paybill.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(mobile.equals(mobno.getText().toString()))
				{
					JFrame fr=new JFrame();
					cardno=new JLabel("Card Number");
					expirydate=new JLabel("Expiry Date");
					cvv=new JLabel("CVV");
					card=new JTextField();
					expiry=new JTextField();
					cvvt=new JTextField();
					confirm=new JButton("Confirm");
					fr.setLayout(new GridLayout(4,2));
					fr.add(cardno);
					fr.add(card);
					fr.add(expirydate);
					fr.add(expiry);
					fr.add(cvv);
					fr.add(cvvt);
					fr.add(confirm);
					confirm.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent i)
						{
							
							String cards=card.getText();
							String expirys=expiry.getText();
							String cvvs=cvvt.getText();
							if(cards.length()!=0 && expirys.length()!=0 && cvvs.length()!=0)
							{
								
								try
								{									
									PreparedStatement statement1=connection.prepareStatement("insert into payment values(?,?,?)");
									statement1.setString(1,username);
									statement1.setString(2,mobile);
									statement1.setString(3,totalpr);
									totalpr=Integer.parseInt(totalpr)+Integer.parseInt(totalcost)+"";
									PreparedStatement statement2=connection.prepareStatement("update customerdetails set totalcost=? where cusid=?");
									statement2.setString(1,totalpr);
									statement2.setString(2,username);
									int rs1=statement1.executeUpdate();
									int rs2=statement2.executeUpdate();
									if(rs1!=0 && rs2!=0)
									{
										JOptionPane.showMessageDialog(jframe,"Payment Success");
										fr.dispose();
										dispose();
									}
									else
										JOptionPane.showMessageDialog(jframe,"Payment Failed");
								}
								catch(Exception ex)
								{}
							}
							else
							{
								JOptionPane.showMessageDialog(jframe,"Enter all the fields");
							}	
						}
					});
					fr.setVisible(true);
					fr.setSize(500,250);
				}
				else
					JOptionPane.showMessageDialog(jframe,"Enter Correct Mobile Number");
			}
		});
		setTitle("Payment");
		setSize(500,250);
		setVisible(true);		
	}
}