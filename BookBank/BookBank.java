import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class BookBank extends JFrame
{
	Connection connection;
	JFrame jframe,fr;
	JPanel panelcenter;
	JLabel book,author;
	JTextField bookt,authort;
	JButton searchbk,confirmbk;
	String username,books,authors;
	BookBank(String username)
	{
		this.username=username;
		jframe=new JFrame();
		fr=new JFrame();
		book=new JLabel("Book Name");
		author=new JLabel("Author Name");
		bookt=new JTextField();
		authort=new JTextField();
		searchbk=new JButton("Search Book");
		confirmbk=new JButton("Confirm");
		panelcenter=new JPanel();
		panelcenter.setLayout(new GridLayout(3,2));
		panelcenter.add(book);
		panelcenter.add(bookt);
		panelcenter.add(author);
		panelcenter.add(authort);
		panelcenter.add(searchbk);
		add(panelcenter,BorderLayout.CENTER);
		searchbk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				books=bookt.getText();
				authors=authort.getText();
				if(books.length()!=0 && authors.length()!=0)
				{
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbank","root","jaswanth");		
						PreparedStatement statement=connection.prepareStatement("select status,appid from booklist where bookname=? and authorname=?");
						statement.setString(1,books);
						statement.setString(2,authors);
						ResultSet rs=statement.executeQuery();
						while(rs.next())
						{
							int status=rs.getInt(1);
							String appid=rs.getString(2);
							if(status==0)
							{
								book.setText(books);
								author.setText(authors);
								fr.setLayout(new GridLayout(3,1));
								fr.add(book);
								fr.add(author);
								fr.add(confirmbk);
								confirmbk.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent ae)
									{
										try
										{
											PreparedStatement statement1=connection.prepareStatement("update booklist set status=?,appid=? where bookname=? and authorname=?");
											statement1.setInt(1,1);
											statement1.setString(2,username);
											statement1.setString(3,books);
											statement1.setString(4,authors);
											int rs1=statement1.executeUpdate();
											if(rs1!=0)
											{
												JOptionPane.showMessageDialog(jframe,"Book purchased Successfully");
												fr.dispose();
												dispose();
											}
										}
										catch(Exception exe)
										{}
									}
								});
								fr.setSize(400,250);
								fr.setTitle("Confirmation");
								fr.setVisible(true);
							}
							else
							{
								JOptionPane.showMessageDialog(jframe,"Book in use by "+appid);
							}
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(jframe,""+ex);
					}					
				}
				else
				{
					JOptionPane.showMessageDialog(jframe,"Enter all the details");
				}
				
			}
		});
		setSize(400,250);
		setTitle("Search Book");
		setVisible(true);
	}
}
