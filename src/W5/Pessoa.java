package W5;

import javax.swing.*;


public class Pessoa extends Thread {
	
	//ImageIcon Imagem = new ImageIcon(getClass().getResource("passageiro.png"));
	JLabel pessoa;
	
	FLOOR floorDestiny;
	FLOOR floor;
	public int id;
	private float positionX = 400;
	private float positionY = 300;
	private int contador = 0;
	
	public Pessoa(JLabel pessoa,FLOOR floor,FLOOR floorDestiny, int id)
	{
		
		this.pessoa = pessoa;
		this.floor = floor;
		this.floorDestiny = floorDestiny;
		this.id = id;
		
	}


	@Override
	public void run() {
		
		
		Start();
		
		
	}
	
	public void Start()
	{
		
		float deltaTime = 0;
		
		
		while(true)
		{
			long start = System.currentTimeMillis();
			
			
			
			Input();
			Update(deltaTime);
			Render();
			
			long end = System.currentTimeMillis();
			deltaTime = (float) (end - start)/ 1000.0f;
		
			
			
		}
		
	}
	
	private void Render() {
		pessoa.setBounds((int)positionX,(int) positionY, 40, 85);
		
	}


	private void Input() {
		
		
	}


	private void Update(float deltaTime) {
		
		if(this.contador < 10)
		{
			
			SetFloor();
			this.contador += 1;
		}
		
	}


	public void SetFloor()
	{
		
		switch(this.floor)
		{
		case PRIMEIRO:
			this.positionX = 250;
			this.positionY = 460;
			break;
		case SEGUNDO:
			this.positionX = 250;
			this.positionY = 270;
			break;
		case TERCEIRO:
			this.positionX = 250;
			this.positionY = 90;
			break;
			
			
		default:
			break;
			
		}
		
		
	}
	
	public void SetPosition(float positionX, float positionY)
	{
		
		this.positionX = positionX;
		this.positionY = positionY;
		
		
	}
	
}
