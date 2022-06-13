package W5;

import javax.swing.*;


public class Pessoa extends Thread {
	
	//ImageIcon Imagem = new ImageIcon(getClass().getResource("passageiro.png"));
	JLabel pessoa;
	
	FLOOR floorDestiny;
	FLOOR floor;
	LR lr;
	public int id;
	private float positionX = 400;
	private float positionY = 300;
	private int contador = 0;
	public boolean finish = true;
	
	public Pessoa(JLabel pessoa,FLOOR floor,FLOOR floorDestiny, LR lr,int id)
	{
		
		this.pessoa = pessoa;
		this.floor = floor;
		this.floorDestiny = floorDestiny;
		this.id = id;
		this.lr = lr;
	}


	
	public void run() {
		
		System.out.println("Thread pessoa antes: " + id);
		Start();
		
		System.out.println("Thread pessoa depois: " + id);
	}
	
	public void Start()
	{
		
		float deltaTime = 0;
		
		
		while(finish)
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
			switch(this.lr)
			{
			case LEFT:
				this.positionX = 250;
				break;
			case RIGHT:
				this.positionX = 500;
				break;
			}
			this.positionY = 460;
			break;
		case SEGUNDO:
			switch(this.lr)
			{
			case LEFT:
				this.positionX = 250;
				break;
			case RIGHT:
				this.positionX = 500;
				break;
			}
			
			this.positionY = 270;
			break;
		case TERCEIRO:
			switch(this.lr)
			{
			case LEFT:
				this.positionX = 250;
				break;
			case RIGHT:
				this.positionX = 500;
				break;
			}
			
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
	
	public void SetFinish()
	{
		
		finish = false;
		
	}
	
}
