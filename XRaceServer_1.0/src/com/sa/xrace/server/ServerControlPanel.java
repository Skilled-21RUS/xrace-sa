package com.sa.xrace.server;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 * @author jlin
 * @version $Id: ServerControlPanel.java,v 1.1 2008-11-17 06:51:09 cpan Exp $
 */

public class ServerControlPanel implements TreeSelectionListener,
		ActionListener, MouseListener {

	private JFrame frame;
	private JMenuBar bar;
	private JMenu menu1;
	private JTree tree;
	private JTextField jt1, jt2; // login max, server Port
	private JButton connect, testLogin, testLogout;
	private int width = 800, height = 450;
	private JPanel rightPanel;
	private ArrayList<CardPanel> cardPanel = new ArrayList<CardPanel>();
	private DefaultMutableTreeNode root ;
	private DefaultTreeModel treeeModel;
	private int index = 0;
	private final int MAXNUM = 10;
	private JPopupMenu popmenu;
	private JMenuItem kick;
	private String selectedName;
	public static final int LOGOUT = 0, TIMEOUT = 1, ERROR = 2, KICK = 3;
	public ServerMain main;
	public static void main(String args[]) {
		ServerControlPanel test1 = new ServerControlPanel();
	}

	public ServerControlPanel() {
		frame = new JFrame("Server Control");
		
		bar = new JMenuBar();
		menu1 = new JMenu("Management");
		bar.add(menu1);

		popmenu = new JPopupMenu();
		kick = new JMenuItem("Kick Player");
		popmenu.add(kick);
		kick.addActionListener(this);

		// the North JPanel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		JLabel JLa1 = new JLabel("Players Max");
		JLabel JLa2 = new JLabel("Server Port");
		jt1 = new JTextField(8);
		jt1.setText("" + MAXNUM);
		jt1.setEditable(false);
		jt2 = new JTextField(8);
		jt2.setText("4444");
		connect = new JButton("OpenServer");
		testLogin = new JButton("Test Login");
		testLogout = new JButton("Test Logout");
		connect.addActionListener(this);
		testLogin.addActionListener(this);
		testLogout.addActionListener(this);
		northPanel.add(JLa1);
		northPanel.add(jt1);
		northPanel.add(JLa2);
		northPanel.add(jt2);
		northPanel.add(connect);
		northPanel.add(testLogin);
		northPanel.add(testLogout);

		// the Center_Left JTree
		root = new DefaultMutableTreeNode("Players Infomation");

		tree = new JTree(root);
		tree.setEditable(true);
		tree.setRootVisible(true);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		treeeModel = (DefaultTreeModel) tree.getModel();
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(tree);

		// the rightPanel which show the contentPanel
		rightPanel = new JPanel();
		rightPanel.setLayout(new CardLayout());

		CardPanel tem = new CardPanel("Players Infomation", "Server Open Time:  ", "");
		cardPanel.add(tem);
		rightPanel.add("Players Infomation", tem);
		((CardLayout) rightPanel.getLayout()).show(rightPanel, "Players Infomation");

		// the JSplitPane separate the Left and the Right View
		JSplitPane splitpane = new JSplitPane();
		splitpane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitpane.setLeftComponent(scroll);
		splitpane.setRightComponent(rightPanel);
		splitpane.setOneTouchExpandable(true); // show arrow
		splitpane.setDividerLocation(150); // set the position

		frame.setJMenuBar(bar);
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(splitpane, BorderLayout.CENTER);
		frame.setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		frame.setBounds((dimension.width - width) / 2,
				(dimension.height - height) / 2, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.validate();

		tree.addMouseListener(this);

	}
 
	class CardPanel extends JPanel {
		JScrollPane textscroll;
		JTextArea text_player;
		JLabel label1, basic_info;
		JButton clean;
		DefaultMutableTreeNode temPlayer;
		String name;

		public CardPanel(String tem, String type, String time) {
			name = tem;
			setLayout(new BorderLayout());
			if (!name.equals("Players Infomation")) {
				temPlayer = new DefaultMutableTreeNode(name);
				root.add(temPlayer);
				treeeModel.reload(); // must add it....
			}
			text_player = new JTextArea();
			text_player.setEditable(false);
			text_player.setBackground(Color.black);
			text_player.setForeground(Color.green);
			textscroll = new JScrollPane(text_player);
			clean = new JButton("Clear Panel");
			clean.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					text_player.setText("");
				}
			});
			label1 = new JLabel(type);
			basic_info = new JLabel(time);
			Box right_north_box = Box.createHorizontalBox();
			right_north_box.add(label1);
			right_north_box.add(basic_info);
			right_north_box.add(Box.createHorizontalGlue());
			right_north_box.add(clean);
			add(right_north_box, BorderLayout.NORTH);
			add(textscroll, BorderLayout.CENTER);

		}

		public void appendMessage(String str) {
			text_player.append(str + "\n");
		}

		public void setTimeLabel(String str) {
			basic_info.setText(str);
		}
	}
	
	/**
	 * @param name
	 * 			<p>
	 *			 receives name from Client and when the Client Socket log in
	 *			And it will control the Panel to show the information
	 * 			</p>
	 */
	public void playerIn(String name) {
		CardPanel tem = new CardPanel(name, name + "  Login Time:  ", getFormatTime());
		cardPanel.add(tem);
		rightPanel.add(name, tem);
		((CardLayout) rightPanel.getLayout()).show(rightPanel, name);
		cardPanel.get(0).appendMessage(name + " at " + getFormatTime() + "  Login");
	}

	/**
	 * 
	 * @param name
	 * 			<p>
	 *			 receives name from Client and when the Client Socket log out
	 *			And it will control the Panel to show the information with different 
	 *			log out reason
	 * 			</p>
	 */
	public void playerOut(String name, int type) {
		for (int i = 0; i < cardPanel.size() - 1; i++) {
			String str2 = root.getChildAt(i).toString();
			if (str2.equals(name)) {
				root.remove(i);
				treeeModel.reload();
				cardPanel.remove(i + 1);
				((CardLayout) rightPanel.getLayout()).next(rightPanel);

				switch (type) {
				case LOGOUT: {
					cardPanel.get(0).appendMessage(
							name + " At  " + getFormatTime() + "  Logout");
					break;
				}
				case TIMEOUT: {
					cardPanel.get(0).appendMessage(
							name + " At  " + getFormatTime() + "  Dropped by overtime ");
					break;
				}
				case ERROR: {
					cardPanel.get(0).appendMessage(
							name + " At  " + getFormatTime() + "  Dropped error");
					break;
				}
				case KICK: {
					cardPanel.get(0).appendMessage(
							name + " At  " + getFormatTime() + "  Kicked by server");
					break;
				}
				}

				break;
			}
		}
	}

	/**
	 * @param name
	 * 			<p>
	 *			 receives name from Client
	 * 			</p>
	 *  @param name
	 * 			<p>
	 *			 receives action from Client 
	 * 			</p>
	 */
	public void oneAction(String name, String action) {
		for (int i = 0; i < cardPanel.size() - 1; i++) {
			String str2 = root.getChildAt(i).toString();
			if (str2.equals(name)) {
				cardPanel.get(i + 1).appendMessage(action);
			}
		}
	}
	
	/**
	 * 		<p>
	 * 			set the time format and show time when the Client log in and log out.
	 * 		</p>
	 */
	public String getFormatTime() {
		Calendar rightnow = Calendar.getInstance();
		String loginTime = "" + rightnow.get(Calendar.YEAR) + " Y "
				+ (rightnow.get(Calendar.MONTH) + 1) + " M "
				+ rightnow.get(Calendar.DAY_OF_MONTH) + " D  "
				+ rightnow.get(Calendar.HOUR_OF_DAY) + " : "
				+ rightnow.get(Calendar.MINUTE) + " : "
				+ rightnow.get(Calendar.SECOND);
		return loginTime;
	}

	/**
	 * 	<p>
	 * 		show the information of different JPanel.
	 * 	</p>
	 */
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		
		
		
		
		if (selection != null) { // if not choose any tree selection be null
			selectedName = selection.toString();
			int selectedRow = tree.getMaxSelectionRow();
			((CardLayout) rightPanel.getLayout())
					.show(rightPanel, selectedName);

		}
	}
/*
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == connect) {
			if (connect.getText().equals("OpenServer")) {
				connect.setText("CloseServer");
				jt2.setEditable(false);
				cardPanel.get(0).setTimeLabel(getFormatTime());
				// /////////////Server open///////////////////////////////////
				int port = Integer.parseInt(jt2.getText());
				main = new ServerMain(this, port);
			} else if (connect.getText().equals("CloseServer")) {
				int n = JOptionPane.showConfirmDialog(frame,
						"The server will lead to all the players dropped,sure to exit?", "Warning",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (n == 0) { 
					main.stop();
					jt2.setEditable(true);
					connect.setText("OpenServer");
				}
			}
		} else if (event.getSource() == testLogin) {
			if (cardPanel.size() < MAXNUM) {
				index++;
				String name = "player_test" + index;
				playerIn(name);
			}
		}

		else if (event.getSource() == testLogout) {
			DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree
					.getLastSelectedPathComponent();
			if (selection != null) { // if not choose any tree selection be
										// null
				selectedName = selection.toString();
				
				int indexToDropOut = tree.getMinSelectionRow();
				System.out.println("Server kick Player at "+(indexToDropOut-1));
				if(indexToDropOut != -1 && indexToDropOut != 0)
				{
				indexToDropOut -=1; 
				main.getInforPoolServer().updateUserDropedOut(
						main.getInforPoolServer().getSocketVector().get(indexToDropOut), 
						main.getInforPoolServer().getOutSVector().get(indexToDropOut)
						);
				playerOut(selectedName, 0);
				}
				
				
			}
		} else if (event.getSource() == kick) {
			
			int indexToDropOut = tree.getMinSelectionRow();
			System.out.println("Server kick Player at "+(indexToDropOut-1));
			if(indexToDropOut != -1 && indexToDropOut != 0)
			{
			indexToDropOut -=1; 
			main.getInforPoolServer().updateUserDropedOut(
					main.getInforPoolServer().getSocketVector().get(indexToDropOut), 
					main.getInforPoolServer().getOutSVector().get(indexToDropOut)
					);
			playerOut(selectedName, 2);
			}
		}
	}
/*
 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
 */
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 3) {
			DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree
					.getLastSelectedPathComponent();
			if (selection != null) { // if not choose any tree selection be
										// null
				selectedName = selection.toString();
				int selectedRow = tree.getMaxSelectionRow();
				if (selectedRow != 0) {
					popmenu.show(tree, e.getX(), e.getY());
				}
			}
		}

	}
	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
