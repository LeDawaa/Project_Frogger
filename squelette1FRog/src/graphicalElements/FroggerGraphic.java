package graphicalElements;

import javax.swing.*;

import gameCommons.IFrog;
import util.Direction;
import java.util.Iterator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;
	private int pixelByCase = 30;
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;
	private JLabel score;

	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();

		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Iterator<Element> i = this.elementsToDisplay.iterator();
		while (i.hasNext()) {
			Element e = i.next();
			g.drawImage(e.sprite, pixelByCase * e.absc, pixelByCase * (height - e.ord - 1), this);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right);
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public void setScore(int score) {
		this.score.setText("Score : " + score);
	}

	public void setTime(int Time) {
		JLabel time = new JLabel("Time : " + Time);
		time.setHorizontalAlignment(JLabel.LEFT);
		time.setVerticalAlignment(JLabel.TOP);
		this.frame.add(time);
	}

	public ArrayList<Element> getElementToDisplay() {
		return this.elementsToDisplay;
	}

	public void endGameScreen(String s, String t, String u) {
		frame.remove(this);
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		Dimension size = label.getPreferredSize();
		label.setBounds(320, 240, size.width, size.height);
		frame.getContentPane().add(label);

		JLabel score = new JLabel(t);
		score.setFont(new Font("Verdana", 1, 20));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setSize(this.getSize());
		size = score.getPreferredSize();
		score.setBounds(300, 280, size.width, size.height);
		frame.getContentPane().add(score);

		JLabel time = new JLabel(u);
		time.setFont(new Font("Verdana", 1, 20));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setSize(this.getSize());
		size = time.getPreferredSize();
		time.setBounds(320, 320, size.width, size.height);
		frame.getContentPane().add(time);

		frame.repaint();
	}
}