package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Controller;
import models.Boss;
import models.Bullet;
import models.Hero;

public class JFMainWindow extends JFrame{
	private static final String NAME_APP = "RASTREADOR";
	private static final long serialVersionUID = 1L;
	private JPCronometer jpCronometer;
	private JPGameZone jpGameZone;
	private ImageIcon icon = new ImageIcon(getClass().getResource("/icon.jpg"));
	
	public JFMainWindow(ArrayList<Boss> boss,Hero player,Controller controller,ArrayList<Bullet> listBullets) {
		this.setTitle(NAME_APP);
		this.setSize(800, 600);
		this.setIconImage(icon.getImage());
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jpCronometer = new JPCronometer();
		jpGameZone = new JPGameZone(boss,player,listBullets);
		this.addKeyListener(controller);
		this.addMouseListener(controller);
		this.setVisible(true);
		init();
	}

	private void init() {
		this.add(jpCronometer,BorderLayout.SOUTH);
		this.add(jpGameZone, BorderLayout.CENTER);
	}
	
	public void setTimeJLabel(String string){
		jpCronometer.setTimeJLabel(string);
	}
	
	public String getTimeJLabel(){
		return jpCronometer.getTimeJLabel();
	}
	
}