package aim;
import javax.swing.JFrame;

import java.util.Timer;
import java.util.TimerTask;
public class Frame extends JFrame{
	Timer timer;
	TimerTask delay;
	TimerTask delete;
	Frame() {
		Panel panel = new Panel();
		this.add(panel);
		this.setTitle("Aim");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		timer = new Timer();
		delay = new TimerTask() {
			@Override
			public void run() {
				if (panel.finished) {
					timer.schedule(delete, 500);
				}
			}
		};
		delete = new TimerTask() {
			@Override
			public void run() {
				dispose();
				new Frame();
				timer.cancel();
			}
		};
		timer.scheduleAtFixedRate(delay, 14000, 1000);
		
	}
}