package W5;
import javax.swing.*;

public class Predio extends JFrame {

	
	private static final long serialVersionUID = 1L;
	
	
	
	ImageIcon imagem = new ImageIcon(getClass().getResource("andar.png"));
	ImageIcon elev = new ImageIcon(getClass().getResource("elevadorVermelho.png"));
	JLabel labelElevador = new JLabel(elev);
	
	JLabel label = new JLabel(imagem);
	JLabel label1 = new JLabel(imagem);
	JLabel label2 = new JLabel(imagem);
	JLabel label3 = new JLabel(imagem);
	
	JLabel label4 = new JLabel(imagem);
	JLabel label5 = new JLabel(imagem);
	JLabel label6 = new JLabel(imagem);
	JLabel label7 = new JLabel(imagem);
	
	static Elevador e;
	
	
	public Predio()
	{
		
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
		setVisible(true);
		
		
	}
	
	public void Desenha() {
		add(label);
		add(label1);
		add(label2);
		add(label3);
		
		add(label4);
		add(label5);
		add(label6);
		add(label7);
		
		add(labelElevador);
				
				
		label.setBounds(0, 0, 300, 20);
		label1.setBounds(0, 170, 300, 20);
		label2.setBounds(0, 350, 300, 20);
		label3.setBounds(0, 540, 300, 20);
		
		label4.setBounds(500, 0, 300, 20);
		label5.setBounds(500, 170, 300, 20);
		label6.setBounds(500, 350, 300, 20);
		label7.setBounds(500, 540, 300, 20);
		
			
		
	}
	
	
	public static void main(String[] args) {
		
		Predio p = new Predio();
		p.Desenha();
		
		e = new Elevador(p.labelElevador);
		e.start();
	}

}
