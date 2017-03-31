import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*! the start class */

@SuppressWarnings("serial")
public class OsuMenu extends JFrame implements ActionListener
{	
	Osu OU = new Osu();
	
	private JButton bStart,  							/*!< JButton for start */
					bClose,   							/*!< JButton for close */
					bStandard, 							/*!< JButton for standard game mode */
					bTime;     							/*!< JButton for time game mode */
	
	
	/*!
	* Create the OsuMenu
	*/
	public OsuMenu()
	{
		this.setSize(1600,900);
		this.setLocation(150,65);
		this.setLayout(null);	
		this.getContentPane().setBackground(Color.cyan);
		
		bStart = new JButton ("Start");
		bStart.setBounds(400 ,400 ,200 ,100);
		
		bClose = new JButton ("Close");
		bClose.setBounds(1000 ,400 ,200 ,100);
		
		bStart.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
		bClose.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
		
		this.add(bStart);
		this.add(bClose);
		this.setVisible(true);
		bStart.addActionListener(this);
		bClose.addActionListener(this);
	}
	
	/*!
	* Create the GameMode menu
	*/
	public void GameMode()
	{
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
		/*!
		* bStart button event
		*/
		if(event.getSource() == bStart)
		{
			bStart.setVisible(false);
			bClose.setVisible(false);
			
			GameMode();
		}
		
		/*!
		* standard mode button event
		*/
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
		
		/*!
		* Time mode button event
		*/
		else if(event.getSource() == bTime)
		{
			bStart.setVisible(false);
			bClose.setVisible(false);
			
			this.dispose();
			
			OU.OsuStart();
			OU.Timer();
		}
		
		/*!
		* Close Button event
		*/
		else if(event.getSource() == bClose) 
		{
			System.exit(0);
		}
	}
}