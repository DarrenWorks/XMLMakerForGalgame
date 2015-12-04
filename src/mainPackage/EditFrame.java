package mainPackage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;

public class EditFrame extends MyFrame{
	JTextArea taStory = null;
	JTextArea taTitle = null;
	JButton btnCommit = null;
	JButton btnHelp = null;
	
	//初始化数据
	public void init(){
		launchFrame();
	}
	
	//装载界面
	public void launchFrame(){
		setFocusable(true);
		setLocation(200, 100);
		setTitle("XMLEditor For Galgame");
		setLayout(new BorderLayout(5,5));
		taStory = new JTextArea("enter your story");
		add(taStory,BorderLayout.CENTER);
		taTitle = new JTextArea("enter your XML title");
		add(taTitle,BorderLayout.NORTH);
		btnCommit = new JButton("commit");
		add(btnCommit,BorderLayout.SOUTH);
//		btnHelp = new JButton("Help");
//		add(btnHelp,BorderLayout.SOUTH);
		pack();
		setSize(800, 600);
		
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
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocus();
			}
		});
		
		
		setVisible(true);
		addButtonListener();
	}

	private void addButtonListener() {
		btnCommit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String XMLName = taTitle.getText();
				String XMLDetails = taStory.getText();
				XMLEditor editor = new XMLEditor(XMLName,XMLDetails);
				editor.madeXML();
			}
		});
	}
	
	private void CloseWindow() {
		confirm();
	}
	
	//确认是否退出编辑界面
	private void confirm(){
		dispose();
	}
	
}
