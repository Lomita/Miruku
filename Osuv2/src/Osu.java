import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/*! the start class */

@SuppressWarnings("serial")
public class Osu extends JFrame implements ActionListener
{
	private JButton bClick,
					bEnd,
					bTime,
					bEndStat;

	private JLabel lCount,
				   lTime,
				   lTimer,
				   lGreat,
				   lCountTimerA;

	private JTextField tTime;

	private int count = 0,
				time = 0,
				Rtime;

	/*!
	* timer
	*/
	Timer myTimer = new Timer();
	TimerTask task  = new TimerTask()
	{
		public void run()
		{
			if(time > 0)
			{
				time--;
				lTimer();
			}
			else
			{
				gameEnd();
			}
		}
	};

	/*!
	* create main frame
	*/
	public void OsuStart()
	{
		this.setSize(1600,900);
		this.setLocation(150,65);
		this.setLayout(null);
	    this.setVisible(true);
	    this.getContentPane().setBackground(Color.cyan);
	}

	/*!
	* move the button on a random location
	*/
	public void OsuMoveButton()
	{
		int width, height;
		width = this.getWidth()-100;
		height = this.getHeight()-150;

		@SuppressWarnings("unused")
		int z = (int)(Math.random()*1000+1);

		bClick = new JButton ();
		bClick.setBounds(((int)(Math.random()*width)+1),((int)(Math.random()*height)+1),100,100);
		try
		{
			bClick.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/auto.png"))));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		bClick.setContentAreaFilled(false);
		bClick.setBorder(null);

		this.add(bClick);
		bClick.addActionListener(this);
	}

	/*!
	* create the exit button
	*/
	public void pressEnd()
	{
		bEnd = new JButton ("Quit");
		bEnd.setBounds(0,0,100,100);
		try
		{
			bEnd.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/Button_exit.png"))));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		bEnd.setContentAreaFilled(false);
		bEnd.setBorder(null);

		this.add(bEnd);
		bEnd.addActionListener(this);
	}

	/*!
	* create the label for the counter
	*/
	public void lcounter(int count)
	{
		lCount = new JLabel ("Counter :  " + count);
		lCount.setBounds(1400,0,1000,50);

		lCount.setFont(new Font("Arial", Font.CENTER_BASELINE, 17));
		lCount.setForeground(Color.black);

		this.add(lCount);
	}

	/*!
	* create dialog to insert time for the timer
	*/
	public void Timer()
	{
		lTimer = new JLabel ("Zeit eingeben");
		lTimer.setBounds(745,350,1000,50);

		tTime = new JTextField();
		tTime.setBounds(805,400,60,50);


		bTime = new JButton("GO");
		bTime.setBounds(745, 400, 60, 50);

		tTime.setFont(new Font("Arial", Font.CENTER_BASELINE, 17));
		lTimer.setFont(new Font("Arial", Font.CENTER_BASELINE, 17));

		this.add(tTime);
		this.add(lTimer);
		this.add(bTime);
		bTime.addActionListener(this);
	}

	/*!
	*
	*/
	public void lTimer()
	{
		this.remove(lTime);
		this.repaint();

		lTime = new JLabel ("Time left " + time + "s");
		lTime.setBounds(1150,0,1000,50);
		lTime.setFont(new Font("Arial", Font.BOLD, 17));

		this.add(lTime);
		this.repaint();
	}

	/*!
	* stop game and remove the buttons
	* create the good job dialog
	*/
	public void gameEnd()
	{
		myTimer.cancel();
		this.remove(bEnd);
		this.remove(bClick);
		this.remove(lTime);
		this.remove(lCount);

		lGreat = new JLabel("GREAT JOB!");
		lGreat.setBounds(680,300,400,100);
		lGreat.setFont(new Font("Arial", Font.BOLD, 40));

		lCountTimerA = new JLabel(count + " Bananya's gefangen in " + Rtime + "s ");
		lCountTimerA.setBounds(650,400,1000,50);
		lCountTimerA.setFont(new Font("Arial", Font.BOLD, 24));

		bEndStat = new JButton ("Back to menu");
		bEndStat.setBounds(720,500,150,30);

		this.add(lGreat);
		this.add(lCountTimerA);
		this.add(bEndStat);
		this.repaint();
		bEndStat.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event)
	{
		/*!
		* move button event
		*/
		if (event.getSource() == bClick)
		{
			this.remove(bClick);
			this.remove(lCount);
			this.repaint();
			OsuMoveButton();
			count++;
			lcounter(count);
		}

		/*!
		* get time from the text field and parse it to an integer
		* create and start the timer
		*/
		else if (event.getSource() == bTime)
		{
			time = Integer.parseInt(tTime.getText());
			Rtime = time;
			System.out.println(Rtime);
			this.remove(lTimer);
			this.remove(tTime);
			this.remove(bTime);
			this.repaint();

			OsuMoveButton();
			pressEnd();
			lcounter(0);

			lTime = new JLabel ("Time left " + time + "s");
			lTime.setBounds(1150,0,1000,50);
			lTime.setFont(new Font("Arial", Font.BOLD, 17));
			this.add(lTime);
			myTimer.scheduleAtFixedRate(task,1000, 1000);
		}

		/*!
		* time runs out event
		*/
		else if (event.getSource() == bEndStat)
		{
			this.dispose();
			@SuppressWarnings("unused")
			OsuMenu menu = new OsuMenu();
		}

		/*!
		*  exit button event
		*/
		else if (event.getSource() == bEnd)
		{
			this.dispose();

			myTimer.cancel();

			@SuppressWarnings("unused")
			OsuMenu menu = new OsuMenu();
		}
	}
}