import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//!<class OsuMenu
@SuppressWarnings("serial")
public class OsuMenu extends JFrame implements ActionListener
{	
	Osu OU = new Osu();
	private JButton bStart, 
					bClose, 
					bStandard, 
					bTime;
	
	//menu
	public OsuMenu()
	{
		this.setSize(1600,900);
		this.setLocation(150,65);
		this.setLayout(null);	
		this.getContentPane().setBackground(Color.cyan);
		
		bStart = new JButton ("Start");
		bStart.setBounds(400 ,400 ,200 ,100);
		
		bClose = new JButton ("Quit");
		bClose.setBounds(1000 ,400 ,200 ,100);
		
		bStart.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
		bClose.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
		
		this.add(bStart);
		this.add(bClose);
		this.setVisible(true);
		bStart.addActionListener(this);
		bClose.addActionListener(this);
	}
	
	//choose mode
	public void GameMode()
	{
		this.setSize(1600,900);
		this.setLocation(150,65);
		this.setLayout(null);	
		this.getContentPane().setBackground(Color.cyan);
		
		bStandard = new JButton ("Standard");
		bStandard.setBounds(400 ,400 ,200 ,100);
		
		bTime = new JButton ("Time");
		bTime.setBounds(1000 ,400 ,200 ,100);
		
		bStandard.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
		bTime.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
		
		this.add(bStandard);
		this.add(bTime);
		this.setVisible(true);
		bStandard.addActionListener(this);
		bTime.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) 
	{
		//start button event
		if(event.getSource() == bStart)
		{
			bStart.setVisible(false);
			bClose.setVisible(false);
			GameMode();
		}
		
		//standard button event
		else if(event.getSource() == bStandard)
		{
			bStart.setVisible(false);
			bClose.setVisible(false);
			this.dispose();
			OU.OsuStart();
			OU.OsuMoveButton();
			OU.pressEnd();
			OU.lcounter(0);
		}
		//time button event
		else if(event.getSource() == bTime)
		{
			bStart.setVisible(false);
			bClose.setVisible(false);
			this.dispose();
			OU.OsuStart();
			OU.Timer();
		}
		//close button event
		else if(event.getSource() == bClose) 
		{
			this.dispose();
		}
	}
}