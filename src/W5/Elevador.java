package W5;


import java.util.concurrent.Semaphore;

import javax.swing.*;


public class Elevador extends Thread{
	
	
	ImageIcon elevVermelho = new ImageIcon(getClass().getResource("elevadorVermelho.png"));
	ImageIcon elevVerde = new ImageIcon(getClass().getResource("elevadorVerde.png"));
	JLabel elevador = new JLabel();
	
	private float positionX = 400;
	private float positionY = 300;
	private float speed = 60;
	private int idPerson = -1;
	private int id = -1;
	
	public boolean full = false;
	public boolean choice = false;
	
	public STATE state = STATE.UP;
	public FLOOR floor = FLOOR.SEGUNDO;
	public FLOOR floorDestiny = FLOOR.TERCEIRO;
	
	Pessoa pessoa[];
	
	static Semaphore semaforo = new Semaphore(1);
	
	
	public Elevador(JLabel elevador, Pessoa pessoa[], int id) {
		
		
		this.elevador = elevador;
		this.elevador.setIcon(elevVerde);
		this.pessoa = pessoa;
		this.id = id;
		
		
		this.elevador.setBounds((int)this.positionX - 50, (int)this.positionY - 75, 100, 150);
		
	}
	
	
	

	@Override
	public void run() {
		System.out.println("Elevador: " + this.id);
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
		
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.choice == false)
		{
			ChoicePerson();
			//System.out.println("Elevador: " + this.id + "Pessoa: "+ this.idPerson);
		}
		semaforo.release();
		
		if(this.full == true)
		{
			UpdatePerson();
			ElevadorCheio();
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
				ChangeFloor();
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
		
	
		for(int i = 0; i < pessoa.length; i++)
		{
			
			if(pessoa[i].floor != pessoa[i].floorDestiny && this.pessoa[i].escolhido != true)
			{
				
				this.floorDestiny = pessoa[i].floor;
				this.idPerson = pessoa[i].id;
				this.pessoa[i].escolhido = true;
				this.choice = true;
				return;
				
			}
			
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
			this.choice = false;
			this.pessoa[idPerson].SetFinish();
			ElevadorVazio();
			
		}
		
	}
	
	private void ElevadorVazio()
	{
		this.elevador.setIcon(elevVerde);
	}
	
	private void ElevadorCheio()
	{
		this.elevador.setIcon(elevVermelho);
	}
	
	
}
