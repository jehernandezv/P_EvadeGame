package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPCronometer extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel jLabelTimeSimulation;
	
	public JPCronometer() {
		this.setLayout(new FlowLayout());
		this.setBackground(Color.decode("#91DC5A"));
		jLabelTimeSimulation = new JLabel();
		init();
		
	}

	private void init() {
		jLabelTimeSimulation.setForeground(Color.WHITE);
		jLabelTimeSimulation.setFont(new Font("Arial", Font.BOLD, 50));

		this.add(jLabelTimeSimulation);
		
	}
	
	
	public void setTimeJLabel(String string){
		this.jLabelTimeSimulation.setText(string);
	}
	
	public String getTimeJLabel(){
		return jLabelTimeSimulation.getText();
	}

}
