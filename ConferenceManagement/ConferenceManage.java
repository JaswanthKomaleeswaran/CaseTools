import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;
class ConferenceManage extends JFrame
{
	JFrame jframe;
	JPanel panelcenter;
	JLabel appid,apptxt,paper,namelb,domain;
	JButton apply;
	JTextField nametxt,domaintxt;
	JFileChooser chooser;
	String username;
	ConferenceManage(String username)
	{
		this.username=username;
		appid=new JLabel("Application ID");
		apptxt=new JLabel(username);
		namelb=new JLabel("Name");
		domain=new JLabel("Domain");
		paper=new JLabel("Upload Paper");
		nametxt=new JTextField();
		domaintxt=new JTextField();
		apply=new JButton("Apply");
		chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Document Files", "docx");
        chooser.setFileFilter(filter);
        panelcenter=new JPanel();
		panelcenter.setLayout(new GridLayout(5,2));
		panelcenter.add(appid);
		panelcenter.add(apptxt);
		panelcenter.add(namelb);
		panelcenter.add(nametxt);
		panelcenter.add(domain);
		panelcenter.add(domaintxt);
		panelcenter.add(paper);
		panelcenter.add(chooser);
		panelcenter.add(apply);
		add(panelcenter,BorderLayout.CENTER);
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame fr=new JFrame();
				JLabel cert=new JLabel("Certificate of Appriciation");
				JLabel cert1=new JLabel("Congratulations "+nametxt.getText());
				JLabel cert2=new JLabel("for presenting paper in "+domaintxt.getText());
				JLabel cert3=new JLabel("\n Authorized Signatory");
				JPanel panel=new JPanel();
				panel.setLayout(new GridLayout(4,1));
				panel.add(cert);
				panel.add(cert1);
				panel.add(cert2);
				panel.add(cert3);
				fr.add(panel,BorderLayout.CENTER);
				fr.setVisible(true);
				fr.setSize(300,200);
				fr.setTitle("Conference Certificate");
			}
		});
		setVisible(true);
		setSize(400,400);
		setTitle("Conference Management");
	}
}