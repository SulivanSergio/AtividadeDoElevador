package W5;
import java.util.Random;

import javax.swing.*;

public class Predio extends JFrame {

	
	private static final long serialVersionUID = 1L;
	
	
	
	ImageIcon imagem = new ImageIcon(getClass().getResource("andar.png"));
	ImageIcon imagemElevador = new ImageIcon(getClass().getResource("elevadorVermelho.png"));
	ImageIcon imagemPessoa = new ImageIcon(getClass().getResource("passageiro.png"));
	
	//ELEVADOR E PESSOAS
	JLabel labelElevador[] = new JLabel[2];
	//JLabel labelPessoa = new JLabel(imagemPessoa);
	
	//ANDARES
	JLabel label = new JLabel(imagem);
	JLabel label1 = new JLabel(imagem);
	JLabel label2 = new JLabel(imagem);
	JLabel label3 = new JLabel(imagem);
	JLabel label4 = new JLabel(imagem);
	JLabel label5 = new JLabel(imagem);
	JLabel label6 = new JLabel(imagem);
	JLabel label7 = new JLabel(imagem);
	
	
	Elevador elevador[];
	Pessoa pessoa[];
	
	
	static int MAX = 5;
	Random random = new Random();
	
	JLabel labelPessoa[] = new JLabel[MAX];
	
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
		
		
		
		
		for(int i = 0; i< 2; i++)
		{
			labelElevador[i] = new JLabel(imagemElevador);
			add(labelElevador[i]);
		}
		
		
		for(int i = 0; i< MAX; i++)
		{
			labelPessoa[i] = new JLabel(imagemPessoa);
			add(labelPessoa[i]);
		}
		
		
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
		
		
		p.pessoa = new Pessoa[MAX];
		p.elevador = new Elevador[2];
		
		for(int i = 0; i < p.pessoa.length ; i++)
		{
			FLOOR floor = FLOOR.PRIMEIRO;
			FLOOR floor2 = FLOOR.PRIMEIRO;
			floor = p.ChoiceFloor();
			floor2 = p.ChoiceFloorDestiny(floor);
			p.pessoa[i] = new Pessoa(p.labelPessoa[i],floor,floor2,p.ChoiceLR() ,i);
			
			p.pessoa[i].start();
		}
		
		for(int i = 0; i < p.elevador.length ; i++)
		{
			p.elevador[i] = new Elevador(p.labelElevador[i],p.pessoa, i);
			p.elevador[i].start();
		}
		
		
		
	}
	
	private FLOOR ChoiceFloor()
	{
		FLOOR floor = FLOOR.PRIMEIRO;
		int r = random.nextInt(3);
		
		
		switch(r)
		{
		case 0:
			floor = FLOOR.PRIMEIRO;
			break;
		case 1:
			floor = FLOOR.SEGUNDO;
			break;
		case 2:
			floor = FLOOR.TERCEIRO;
			break;
		}
		
		return floor;
		
		
	}
	private FLOOR ChoiceFloorDestiny(FLOOR floor)
	{
		
		int r = random.nextInt(2);
		
		
		switch(floor)
		{
		case PRIMEIRO:
			switch(r)
			{
			case 0:
				floor = FLOOR.TERCEIRO;
				break;
			case 1:
				floor = FLOOR.SEGUNDO;
				break;
			
			}
			break;
		case SEGUNDO:
			switch(r)
			{
			case 0:
				floor = FLOOR.PRIMEIRO;
				break;
			case 1:
				floor = FLOOR.TERCEIRO;
				break;
			
			}
			break;
		case TERCEIRO:
			switch(r)
			{
			case 0:
				floor = FLOOR.PRIMEIRO;
				break;
			case 1:
				floor = FLOOR.SEGUNDO;
				break;
			
			}
			break;
		}
		
		return floor;
		
		
	}

	private LR ChoiceLR()
	{
		LR lr = LR.LEFT;
		int r = random.nextInt(2);
		
		switch(r)
		{
		case 0:
			lr = LR.LEFT;
			break;
		case 1:
			lr = LR.RIGHT;
			break;
		
		}
		
		return lr;
		
	}
	
}
