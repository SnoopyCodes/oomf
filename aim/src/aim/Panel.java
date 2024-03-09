package aim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	Random random = new Random();
	Timer timer;
	TimerTask end;
	JLabel target;
	JLabel fake;
	int score = -1;
	int x;
	int y;
	boolean finished = false;
	
	Panel() {
		target = new JLabel();
		Generate();
		Draw();
		target.addMouseListener(this);
		target.setOpaque(true);
		target.setBackground(new Color(200, 200, 200));
		target.setHorizontalAlignment(JLabel.CENTER);
		target.setText("0");

		fake = new JLabel();
		DrawFake();
		fake.setOpaque(true);
		fake.setBackground(new Color(100, 100, 100));
		fake.setHorizontalAlignment(JLabel.CENTER);
		
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setFocusable(true);
		this.setBackground(new Color(51, 53, 55));
		this.setLayout(null);
		this.add(target);
		this.add(fake);
	}
	void Start() {
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
		fake.setBounds(x, y, 25, 25);
	}
	void Draw() {
		target.setText(Integer.toString(score+1));
		target.setBounds(x, y, 25, 25);
	}
	void Generate() {
		x = random.nextInt(1895);
		y = random.nextInt(1035);
	}
	void GameOver() {
		target.setBounds(0, 0, 1920, 1080);
		target.setText("Score: " + score);
		finished = true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (score == -1 || !finished) {
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