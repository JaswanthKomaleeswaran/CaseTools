import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class BookTicket extends JFrame
{
	Connection connection;
	JFrame jframe,fr;
	JLabel source,dest,trainnm,price;
	JTextField sourcenm,destnm;
	JButton seltrain,bkticket;
	JPanel panelcenter;
	String username,trainsr,traindest,trainsnm,totalcost;
	BookTicket(String username)
	{
		this.username=username;
		jframe=new JFrame();
		fr=new JFrame();
		panelcenter=new JPanel();
		source=new JLabel("Source");
		dest=new JLabel("Destination");
		trainnm=new JLabel("Train Name");
		price=new JLabel("Total Cost");
		sourcenm=new JTextField();
		destnm=new JTextField();
		seltrain=new JButton("Select Train");
		bkticket=new JButton("Book Ticket");
		panelcenter.setLayout(new GridLayout(3,2));
		panelcenter.add(source);
		panelcenter.add(sourcenm);
		panelcenter.add(dest);
		panelcenter.add(destnm);
		panelcenter.add(seltrain);
		add(panelcenter,BorderLayout.CENTER);
		seltrain.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				trainsr=sourcenm.getText();
				traindest=destnm.getText();
				if(trainsr.length()!=0 && traindest.length()!=0)
				{
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/eticket","root","jaswanth");
						PreparedStatement statement=connection.prepareStatement("select trainnm,price from pricelist where source=? and dest=?");
						statement.setString(1,trainsr);
						statement.setString(2,traindest);
						ResultSet rs=statement.executeQuery();
						if(rs.next())
						{
							trainsnm=rs.getString(1);
							totalcost=rs.getString(2);
							fr.setLayout(new GridLayout(3,2));
							fr.add(trainnm);
							trainnm.setText(trainsnm);
							fr.add(trainnm);
							fr.add(price);
							price.setText(totalcost);
							fr.add(price);
							fr.add(bkticket);
							bkticket.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent ev)
								{
									JOptionPane.showMessageDialog(jframe,"Train Booked Successfully");
									fr.dispose();
									dispose();
								}
							});
							fr.setTitle("Confirmation");
							fr.setSize(500,250);
							fr.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(jframe,"Train Unavailable");
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(jframe,""+ex);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(jframe,"Enter all the fields");
				}
			}
		});
		setTitle("Book Ticket");
		setSize(500,250);
		setVisible(true);
	}
}