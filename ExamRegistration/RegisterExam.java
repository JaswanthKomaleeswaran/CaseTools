import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class RegisterExam extends JFrame
{
	Connection connection;
	JFrame jframe;
	JPanel panelcenter;
	JLabel appid,apptxt,subject,date;
	JComboBox subjectcb;
	JTextField datetxt;
	JButton register,regcourses;
	String username;
	RegisterExam(String username)
	{
		this.username=username;
		appid=new JLabel("Application Id");
		apptxt=new JLabel(username);
		date=new JLabel("Date of Exam");
		datetxt=new JTextField();
		subject=new JLabel("subjects");
		String subjects[]={"OOPS","POP","Web Programming","Compiler Design","Data Analytics"};
		subjectcb=new JComboBox(subjects);
		register=new JButton("Regiter Exam");
		panelcenter=new JPanel();
		panelcenter.setLayout(new GridLayout(4,2));
		panelcenter.add(appid);
		panelcenter.add(apptxt);
		panelcenter.add(subject);
		panelcenter.add(subjectcb);
		panelcenter.add(date);
		panelcenter.add(datetxt);
		panelcenter.add(register);
		add(panelcenter,BorderLayout.CENTER);
		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String selsub=(String)subjectcb.getItemAt(subjectcb.getSelectedIndex());
				int result = JOptionPane.showConfirmDialog(null, "Confirm your Selected Subject "+selsub+"\n on "+datetxt.getText(),null, JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(jframe,"Exam on "+datetxt.getText()+" and the subject "+selsub+" has been Registered Successfully!!!");
					dispose();
				}
				
			}
		});
		setTitle("Register Exam");
		setSize(400,400);
		setVisible(true);
	}
}