package mainPackage;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;

public class MainFrame extends MyFrame {
	JButton btnEdit = null;
	JButton btnShow = null;

	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		f.init();
	}

	// 初始化数据
	public void init() {
		test();
		launchFrame();
	}

	// 装载界面
	public void launchFrame() {
		setFocusable(true);
		setLocation(200, 100);
		setTitle("XMLEditor For Galgame");
		setLayout(new GridLayout(2, 1));
		btnEdit = new JButton("edit XML");
		btnShow = new JButton("show me");
		add(btnEdit);
		add(btnShow);
		pack();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				CloseWindow();
			}
		});
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				System.out.println(key == KeyEvent.VK_ESCAPE);
				switch (key) {
				case KeyEvent.VK_ESCAPE:
					CloseWindow();
					break;
				}
			}
		});

		setVisible(true);
		addButtonListener();
	}

	private void addButtonListener() {
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditFrame f = new EditFrame();
				f.init();
				requestFocus();
			}
		});
	}

	private void CloseWindow() {
		System.exit(0);
	}

	private void test() {

	}

}
