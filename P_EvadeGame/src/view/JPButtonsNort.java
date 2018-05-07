package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import controller.EAction;

public class JPButtonsNort extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton jButtonExitGame;
	
	public JPButtonsNort(Controller controller) {
		this.setLayout(new FlowLayout());
		this.jButtonExitGame = new JButton("Exit");
		this.jButtonExitGame.addActionListener(controller);
		this.jButtonExitGame.setActionCommand(EAction.EXITGAME.name());
		init();
		
	}

	private void init() {
		this.add(jButtonExitGame);
		
	}

}
