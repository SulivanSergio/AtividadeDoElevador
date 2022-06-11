package W5;

import javax.swing.*;


public class Elevador extends Thread{
	
	
	ImageIcon elevVermelho = new ImageIcon(getClass().getResource("elevadorVermelho.png"));
	ImageIcon elevVerde = new ImageIcon(getClass().getResource("elevadorVerde.png"));
	JLabel elevador = new JLabel();
	
	private float positionX = 400;
	private float positionY = 300;
	private float speed = 60;
	
	public STATE state = STATE.UP;
	public FLOOR floor = FLOOR.PRIMEIRO;
	public FLOOR floorDestiny = FLOOR.QUARTO;
	public Elevador(JLabel elevador) {
		
		
		this.elevador = elevador;
		this.elevador.setIcon(elevVerde);
		
		this.elevador.setBounds((int)this.positionX - 50, (int)this.positionY - 75, 100, 150);
		
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
		
		
	}

	private void Update(float deltaTime) {
		
		UpdateState(deltaTime);
		
		ChangeFloor();
		UpdateFloor();
		
		if(this.floor == FLOOR.TERCEIRO)
		{
			this.floorDestiny = FLOOR.PRIMEIRO;
			
		}
		
		this.elevador.setBounds((int)this.positionX - 50, (int)this.positionY - 75, 100, 150);
	}

	private void Input() {

		
	}

	
	private void ChangeState()
	{
		switch(this.state)
		{
			case UP:
				this.state = STATE.DOWN;
				break;
			case DOWN:
				this.state = STATE.UP;
				break;
		default:
			break;
		}
		
	}
	
	private void UpdateState(float deltaTime)
	{
		switch(this.state)
		{
			case UP:
				this.positionY -= speed * deltaTime;
				break;
			case DOWN:
				this.positionY += speed * deltaTime;
				break;
			case REST:
				
				break;
		}
		
	}
	private void ChangeFloor()
	{
		
		switch(this.floor)
		{
			case PRIMEIRO:
				switch(this.floorDestiny)
				{
					case SEGUNDO:
						state = STATE.UP;
						break;
					case TERCEIRO:
						state = STATE.UP;
						break;
					case QUARTO:
						state = STATE.UP;
						break;
				default:
					state = STATE.REST;
					break;
				}
				break;
			case SEGUNDO:
				switch(this.floorDestiny)
				{
					case PRIMEIRO:
						state = STATE.DOWN;
						break;
					case TERCEIRO:
						state = STATE.UP;
						break;
					case QUARTO:
						state = STATE.UP;
						break;
				default:
					state = STATE.REST;
					break;
				}
				break;
			case TERCEIRO:
				switch(this.floorDestiny)
				{
					case PRIMEIRO:
						state = STATE.DOWN;
						break;
					case SEGUNDO:
						state = STATE.DOWN;
						break;
					case QUARTO:
						state = STATE.UP;
						break;
					default:
						state = STATE.REST;
						break;
				}
				break;
			
			default:
				break;
		}
	}
	
	private void UpdateFloor()
	{
		if(this.positionY > 450)
		{
			this.floor = FLOOR.PRIMEIRO;
			
		}
		if(this.positionY < 300 && this.positionY > 280)
		{
			this.floor = FLOOR.SEGUNDO;
			
		}
		if(this.positionY < 80 && this.positionY > 50)
		{
			this.floor = FLOOR.TERCEIRO;
		}
		
		if( this.positionY < 10)
		{
			this.state = STATE.REST;  
		}
		if( this.positionY > 540)
		{
			this.state = STATE.REST;  
		}
		
		 
	}
	
}
