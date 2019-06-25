import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;
class Recruit extends JFrame
{
	Connection connection;
	JFrame jframe;
	JPanel panelcenter;
	JLabel appid,apptxt,resume,namelb;
	JButton apply;
	JTextField nametxt;
	JFileChooser chooser;
	String username;
	//File file;
	Recruit(String username)
	{
		this.username=username;
		appid=new JLabel("Application ID");
		apptxt=new JLabel(username);
		namelb=new JLabel("Name");
		resume=new JLabel("Insert Resume");
		nametxt=new JTextField();
		apply=new JButton("Apply");
		chooser = new JFileChooser();
		//int returnVal = fileChooserAddDoc.showOpenDialog(chooser);
		//if (returnVal == JFileChooser.APPROVE_OPTION) {
		//file = chooser.getSelectedFile();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Document Files", "docx");
        chooser.setFileFilter(filter);
        panelcenter=new JPanel();
		panelcenter.setLayout(new GridLayout(4,2));
		panelcenter.add(appid);
		panelcenter.add(apptxt);
		panelcenter.add(namelb);
		panelcenter.add(nametxt);
		panelcenter.add(resume);
		panelcenter.add(chooser);
		panelcenter.add(apply);
		add(panelcenter,BorderLayout.CENTER);
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String nm=nametxt.getText();
				JOptionPane.showMessageDialog(jframe,"Dear "+nm+",\n\t Your Resume has been Successfully received.\n\t\tThank You!!!");
				dispose();
			}
		});
		setVisible(true);
		setSize(400,400);
		setTitle("Recruit Online");
	}
}