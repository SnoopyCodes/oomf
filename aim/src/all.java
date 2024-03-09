
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
public class all {
	Frame fre = new Frame();
}
class Frame extends JFrame{
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

class Panel extends JPanel implements MouseListener{
	Random random = new Random();
	Timer timer;
	TimerTask end;
	JLabel target;
	JLabel fake;
	int score = -1;
	int x;
	int y;
	boolean running = false;
	boolean finished = false;
	
	Panel() {
		target = new JLabel();
		Generate();
		Draw();
		target.addMouseListener(this);
		target.setOpaque(true);
		target.setBackground(new Color(200, 200, 200));
		target.setHorizontalAlignment(JLabel.CENTER);
		target.setText("Click to start");

		fake = new JLabel();
		DrawFake();
		fake.setOpaque(true);
		fake.setBackground(new Color(100, 100, 100));
		fake.setHorizontalAlignment(JLabel.CENTER);
		
		this.setPreferredSize(new Dimension(1280, 720));
		this.setFocusable(true);
		this.setBackground(new Color(51, 53, 55));
		this.setLayout(null);
		this.add(target);
		this.add(fake);
	}
	void Start() {
		running = true;
		timer = new Timer();
		end = new TimerTask() {
			@Override
			public void run() {
				GameOver();
			}
		};
		timer.schedule(end, 10000);
	}
	void DrawFake() {
		Generate();
		fake.setText(Integer.toString(score+2));
		fake.setBounds(x, y, 15, 15);
	}
	void Draw() {
		target.setText(Integer.toString(score+1));
		target.setBounds(x, y, 15, 15);
	}
	void Generate() {
		x = random.nextInt(1265);
		y = random.nextInt(705);
	}
	void GameOver() {
		running = false;
		int avg = 10000;
		if (score != 0) { avg = 10000 / score; }
		target.setBounds(0, 0, 1280, 720);
		target.setText("Score: " + score + "  MS per target: " + avg);
		finished = true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (score == -1 || running) {
			Start();
			score++;
			Draw();
			DrawFake();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
