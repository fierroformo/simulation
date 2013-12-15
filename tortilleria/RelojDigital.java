import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferStrategy;
 
public class RelojDigital extends JFrame implements Runnable
{
	Font fuente = new Font("Arial", Font.BOLD, 32);
	public BufferStrategy strategy;
	public static String hora;
	public static String minuto;
	public static Thread runner;
	public static int c=0;
 
	public RelojDigital()
	{
		super("reloj");

		Container contenedor = getContentPane();
		setSize(200, 200);
		setResizable( false );
		setVisible(true);
		if(runner == null)
		{
			runner = new Thread(this);
		}
		else
		{
			System.out.println("Stop");
		}
		createBufferStrategy(2);
        strategy = getBufferStrategy();
	}

	//cambio
	public void run()
	{
		for(int i=7; i<22; i++)
		{
			
			for(int j=0; j<=59; j++)
			{
				hora = String.valueOf( i );
				if( j < 10 )
					minuto = "0" + String.valueOf( j ); 
				else
					minuto = String.valueOf( j );
				if( i < 13 ){
					int cxm=0;//clientes por minuto
					if(i==7){if(j%10==0)ListaPersonas.inserta();}
					else if(i==8)cxm=2;
					else if(i==9||i==10)cxm=5;
					else if(i==11)cxm=2;
					else if(j%15==0)ListaPersonas.inserta();
					for(c=0;c<cxm;c++)ListaPersonas.inserta();
			    }	
				paint2();
				try
				{
					Thread.sleep(1000); //1 segundo
				}
				catch(InterruptedException e)
				{
					System.out.println("Ha ocurrido un error");
				}
			}//cierre 2do for
		} //fin del 1er for
		hora = "SE TERMINO";
		minuto = "DE ATENDER";
		repaint();
	} //fin del metodo run
 

	public void paint2()
	{
		Graphics g = strategy.getDrawGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setFont(fuente);
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, 200, 200);
		g2.setColor(Color.RED);
		g2.drawString(hora + " : " + minuto, 50, 100);
		
		strategy.show();
	}//fin del metodo paint
	
	
	public static void main(String args[])
	{
		RelojDigital aplicacion = new RelojDigital();
		aplicacion.paint2();
		aplicacion.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
} //fin de la clase 
