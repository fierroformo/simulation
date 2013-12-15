import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ListaPersonas extends JFrame implements ActionListener, Runnable
{
	public static int contador = 1, cc = 0;
	public static int NumElementos = 0;
	public static Thread hilo,hilo2,hilo3;
	JButton iniciar, parar;
	public static JTextArea out, out2;
	public static JLabel et1, et2;
	Font fuente = new Font("Arial", Font.BOLD, 14);
	public static String temp = new String("");
	public static JTextField tf[]=new JTextField[3];
	public static JLabel l[]=new JLabel[3];
	public static JPanel pnl;
	public static String auxx;

	public static String[] name = {"Blanca", "Carolina", "Rita", "Ivonne", "Estela", "Javier", "Tetin", "Rocio",
	                              "Tere", "Itzel", "Fatima", "Elsa", "Rebeca", "Sandra", "Cecilia", "Hortencia",
	                              "Maria", "Dulce", "Anahi", "Lucia", "Alejandra", "Gaby", "Karla", "Katia", "Martha",
	                              "Graciela", "Lupe", "Norma", "Micaela", "Karen", "Altagracia", "Mercedez", "Veronica",
	                              "Altamisa", "Yoloxochitl", "Citlali", "Denisse", "Dayana", "Esmeralda", "Fernanda", "Gaviota",
	                              "Herlinda", "Genoveva", "Gicela", "Erendira", "Gicela", "Irma", "Imelda", "Jessica", 
	                              "Kassandra", "Katia", "Lizet", "Manuela", "Melissa", "Mirna", "Jaquelin", "Zaida", "Mayda",
	                              "Gertrudis", "Zulema",
	                              "Alfonso",   "Agustin",   "José Alejandro",  "Mauricio",   "Edgar",   "Esaul",   "Everardo",
	                              "Saul",      "Hernan",    "Erick",      "Miguel",     "Manuel",  "Felipe",  "Luis",
	                              "David",     "Enrique",   "Jose",       "Adrian",     "Chito",   "Rafael",  "Agustin",
	                              "Eleno",     "Mariano",   "Carlos",     "Bonifacio",  "Bernabe", "Claudio", "Casimiro",
	                              "Luciano",   "Leobardo",  "Tadeo",      "Nicolas",    "Mauro",   "Fidel",   "Filiberto",
	                              "Christian",   "Elias",     "Alonzo",     "Maximiliano","Giovanni" };
	                              
	public static String[] apellido= {"Betancourt",  "Montellano", "Avila",    "Martinez",  "Murillo",   "Soto",     "Rodriguez",
	                                  "Castañon",    "Gonzales",   "Cardenas", "Suacedo",   "Jaramillo", "Muro",     "Hernandez",
	                                  "Ornelas",     "Piña",       "Gurrola",  "Estrella",  "Sosa",      "Tapia",    "Vidales",
	                                  "Lopez",       "Alvarez",    "Alvarado", "Romero",    "Rosas",     "Limones",  "Herrera",
	                                  "Almada",      "Arellano",   "Arteaga",  "Benitez",   "Villaluz",  "Carrillo", "Frausto",
	                                  "Torres",      "Flores",     "Cervantes","Cabañas",   "Aquino",    "Garcia",   "Camarena",
	                                  "Perez",       "Santibañes", "Chavez",   "Gaytan",    "Marquez",   "Davila",   "Luna",
	                                  "De Santiago", "Biancucci",  "Saldaña",  "Aguirre",   "Ceballos",  "Montiel",  "Licea",
	                                  "Diaz",        "Gimenez",    "Corona",   "Mota",      "Campos",    "Chavez",   "Nilo", 
	                                  "Saens",       "Diera",      "Sierra",   "Alamillo",  "Aguilar",   "Tabullo",  "Robles",
	                                  "Pedroza",     "Valadez",    "Bonilla",  "Gallegos",  "Morales",   "Tolentino","Araujo",
	                                  "Jara",        "Rivera",     "Valles",   "Falcon",    "Bañuelos",  "Becerril", "Ibarra",
	                                  "Jacobo",      "Velez",      "Messi",    "Balcazar",  "Ochoa",     "Cabrera",  "Bautista",
	                                  "Rodarte",     "Duarte",     "Vargas",   "Aleman",    "De Casas",  "Davish",   "Dorado",
	                                  "Magallanes",  "Castañeda",  "Guzman"};
	/*public static String[] eventualidades={"HORA DE COMER","RECESO","FALTA DE MERCANCIA","FALTA DE ENERGIA ELECTRICA",
	                                        "ACCIDENTE", "CAIDA DEL SISTEMA","CORTE DE CAJA","PROBLEMAS CON LA IMPRESORA",
	                                        "LLEGO UNA VIEJITA CON PURAS MONEDAS DE A CENTAVO","SISMO"};                                                                          
	*/
	public static String eventualidad= "Lllego una viejita con monedas de 1 centavo";
	public static Lista primero = null;
	public static Lista ultimo = null;
	
	public ListaPersonas()
	{
		super("Tortilleria el VALLE");
		Box caja = Box.createHorizontalBox();
		Container contenedor = getContentPane();
		contenedor.setLayout( new FlowLayout() );
	    pnl=new JPanel();
	    pnl.setLayout(new FlowLayout());
		iniciar = new JButton("Iniciar");
		iniciar.addActionListener( this );
		contenedor.add( iniciar );
		parar = new JButton("Detener");
		parar.addActionListener( this );
		contenedor.add( parar );
		for(int i=0; i < 3; i++) {
			l[i] = new JLabel("Caja "+(i+1));
			pnl.add(l[i]);
			tf[i] = new JTextField(19);
			pnl.add(tf[i]);
		}
		contenedor.add(pnl);
		out = new JTextArea(20, 25);
		out.setFont( fuente );
		caja.add( new JScrollPane( out ));
		out2 = new JTextArea(20, 30);
		out2.setFont( fuente );
		caja.add( new JScrollPane( out2 ));
		contenedor.add( caja );

		et1 = new JLabel(" Linea  de  Espera ");
		et2 = new JLabel("/  Sin eventualidad ");
		JPanel bx = new JPanel();
		bx.setLayout(new FlowLayout());
		bx.add( et1 );
		bx.add( et2 );
		contenedor.add( bx, BorderLayout.SOUTH);

		setSize(800, 500);
		setLocationRelativeTo( null );
		setVisible( true );

		if(hilo == null) {
			hilo = new Thread(this);
			hilo2 = new Thread(this);
			hilo3 = new Thread(this);
		} else {
			System.out.println("Stop");
		}
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == iniciar ) {
			RelojDigital.runner.start();
			hilo.start();
			hilo2.start();
			hilo3.start();
		}
		if(event.getSource() == parar )
		{
			RelojDigital.runner.stop();
			hilo.stop();
			hilo2.stop();
			hilo3.stop();
		}		
	}

	public static boolean isVacia()
	{
		return primero == null;
	}

	public static void inserta()
	{
		int a = (int)(Math.random()*99);
		int b = (int)(Math.random()*99);
		auxx = name[a] + " " + apellido[b];
		NumElementos++;
		et1.setText("Elementos en la Fila " + NumElementos); 
		inserta( auxx + "  " + RelojDigital.hora + ":" + RelojDigital.minuto);
		out2.append(contador + "\t< - " + auxx + "  " + RelojDigital.hora + ":" + RelojDigital.minuto + "\n");
		contador++;
	}

	public static void inserta(String nombre)
	{
		Lista aux;
		if(isVacia()) {			   
		    aux = new Lista(nombre);
		    primero = ultimo = aux;
		} else {
			if(nombre.equalsIgnoreCase("soto")) {
			   JOptionPane.showMessageDialog(null, "Ese wey no!!!! xD");
			} else {
				aux = new Lista(nombre);
				ultimo.next = aux;
				ultimo = ultimo.next;
			}
		}
		out.setText( recorre() );
	}

	public static void elimina()
	{		
		if(isVacia()) {
			System.out.println("No hay elementos sabe!");
		} else {
			tf[cc].setText("cliente");
			cc++;
			out2.append("\t- > " + primero.nombre + " / " + RelojDigital.hora + ":" + RelojDigital.minuto + "\n");
			primero = primero.next;
			if(cc == 3) cc = 0;
		}
	}

	public static String recorre()
	{
		Lista aux;
		temp = "";
		if(isVacia()) {
			temp = "La lista esta vacia sabee";
		} else {
			aux = primero;
			while(aux != null)
			{
				temp += aux.nombre + "\n";
				aux = aux.next;
			}
		}
		return temp;
 	}

	public void run()
	{
		try
		{
			while( true )
			{ 
				int a = 250 + (int)(Math.random()*4750);
				if(a > 2000 && a < 2150) {
					et2.setText( eventualidad );
					Thread.sleep(10000);
					et2.setText("/  Sin eventualidad ");
				}
				
				Thread.sleep(a);
				elimina();
				tf[cc].setText("");
				if(NumElementos > 0) NumElementos--;
				et1.setText("Elementos en la Fila " + NumElementos);
				out.setText( recorre() );		
			}
		}
		catch(InterruptedException e) 
		{
			System.out.println("Ha ocurrido un error");
		}		
	}

	public static void main(String args[])
	{
		ListaPersonas aplicacion = new ListaPersonas();
		new RelojDigital();
		aplicacion.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}//fin de la clase InterfazColas
