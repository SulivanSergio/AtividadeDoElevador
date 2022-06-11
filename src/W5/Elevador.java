package W5;

import javax.swing.*;


public class Elevador extends Thread{
	
	
	ImageIcon elevVermelho = new ImageIcon(getClass().getResource("elevadorVermelho.png"));
	ImageIcon elevVerde = new ImageIcon(getClass().getResource("elevadorVerde.png"));
	JLabel elevador = new JLabel();
	
	private float positionX = 400;
	private float positionY = 300;
	private float speed = 60;
	private int idPerson = -1;
	
	public boolean full = false;
	public boolean choice = false;
	
	public STATE state = STATE.UP;
	public FLOOR floor = FLOOR.SEGUNDO;
	public FLOOR floorDestiny = FLOOR.TERCEIRO;
	
	Pessoa pessoa[];
	
	
	public Elevador(JLabel elevador, Pessoa pessoa[]) {
		
		
		this.elevador = elevador;
		this.elevador.setIcon(elevVerde);
		this.pessoa = pessoa;
		
		
		
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
		
		this.elevador.setBounds((int)this.positionX - 50, (int)this.positionY - 75, 100, 150);
	}

	private void Update(float deltaTime) {
		
		UpdateState(deltaTime);
		
		ChangeFloor();
		UpdateFloor();
		
		if(this.choice == false)
		{
			ChoicePerson();
		}
		if(this.full == true)
		{
			UpdatePerson();
			FecharPorta();
		}
		if(idPerson != -1)
		{
			EnterPerson();
			QuitPerson();
		}
		
	}

	private void Input() {

		
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
		if(this.positionY < 90 && this.positionY > 50)
		{
			this.floor = FLOOR.TERCEIRO;
		}
		
		if( this.positionY < 80)
		{
			this.state = STATE.REST;  
		}
		if( this.positionY > 460)
		{
			this.state = STATE.REST;  
		}
		
		 
	}
	
	private void ChoicePerson()
	{
		
		try{
				
			
			for(int i = 0; i < pessoa.length; i++)
			{
				
				if(pessoa[i].floor != pessoa[i].floorDestiny)
				{
					
					this.floorDestiny = pessoa[i].floor;
					this.idPerson = pessoa[i].id;
					
					this.choice = true;
					return;
					
				}
				
				
			}
		}
		catch(Exception e) {
			
		}
		
	}
	
	private void UpdatePerson()
	{
		this.pessoa[idPerson].SetPosition(this.positionX, this.positionY);;
		
		
	}
	private void EnterPerson()
	{
		if(this.floor == this.floorDestiny)
		{
			
			this.floorDestiny = this.pessoa[idPerson].floorDestiny;
			this.full = true;
			
		}
		
	}
	private void QuitPerson()
	{
		if(this.floor == this.pessoa[idPerson].floorDestiny)
		{
			this.pessoa[idPerson].floor = this.floorDestiny;
			this.pessoa[idPerson].SetFloor();
			this.full = false;
			this.choice = true;
			AbrirPorta();
		}
		
	}
	
	private void AbrirPorta()
	{
		this.elevador.setIcon(elevVerde);
	}
	
	private void FecharPorta()
	{
		this.elevador.setIcon(elevVermelho);
	}
	
	
}
