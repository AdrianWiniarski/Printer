
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class Banknot {
	String waluta;
	int wartosc;

	public Banknot(String waluta, int wartosc) {
		this.waluta = waluta;
		this.wartosc = wartosc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((waluta == null) ? 0 : waluta.hashCode());
		result = prime * result + wartosc;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Banknot other = (Banknot) obj;
		if (waluta == null) {
			if (other.waluta != null)
				return false;
		} else if (!waluta.equals(other.waluta))
			return false;
		if (wartosc != other.wartosc)
			return false;
		return true;
	}

// Tu proszê dopisaæ brakuj¹ce metody, które s¹ niezbêdne,
// by obiekty klasy Banknot mog³y byæ przechowywane w kolekcji


} // koniec klasy Banknot



// KLASA POMOCNICZA DO ZADAÑ 4, 5
//W TEJ KLASIE NIE TRZEBA NIC MODYFIKOWAÆ !!!
class WidokKolekcji extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private JTable tabela;
	private DefaultTableModel modelTabeli;
	Collection<Banknot> kolekcja;

/* Do konstruktora nale¿y przekazaæ referencjê
* na kolekcjê, której zawartoœæ ma byæ wyœwietlana
* na panelu
*/
WidokKolekcji(Collection<Banknot> kolekcja) {
	String[] kolumny = { "Nazwa:", "Rozmiar" };
	modelTabeli = new DefaultTableModel(kolumny, 0);
	tabela = new JTable(modelTabeli);
	tabela.setRowSelectionAllowed(false);
	this.kolekcja = kolekcja;
	setViewportView(tabela);
	setPreferredSize(new Dimension(150, 200));
	setBorder(BorderFactory.createTitledBorder("Pudelka"));
}

/* Metodê refresh() nale¿y wywo³aæ po ka¿dej
* modyfikacji zawartoœci wyœwietlanej kolejcji
*/
	void refresh(){
		modelTabeli.setRowCount(0);
		for (Banknot p : kolekcja) {
			String[] s = { p.waluta, ""+p.wartosc };
			modelTabeli.addRow(s);
		}
	}
} // koniec klasy WidokKolekcji





class Panel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	private boolean isButtonPressed = false;
	private int x = 50, y = 150;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		
		if(x > getSize().getWidth()-25) {
			x = -25;
		} else if(x < -25){
			x = getSize().width-25;
		}
		if(y > getSize().getHeight()-25) {
			y = -25;
		} else if(y < -25) {
			y = getSize().height-25;
		}
		g.drawOval(x, y, 50, 50);
		if(isButtonPressed) {
			g.fillOval(x, y, 50, 50);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		isButtonPressed = true;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			przesun(0, -5);
			break;
		case KeyEvent.VK_DOWN:
			przesun(0, 5);
			break;
		case KeyEvent.VK_LEFT:
			przesun(-5, 0);
			break;
		case KeyEvent.VK_RIGHT:
			przesun(5, 0);
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isButtonPressed = false;
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		isButtonPressed = true;
		repaint();
	}
	
	public void przesun(int x, int y) {
		this.x += x;
		this.y += y;
	}

// Tu proszê dopisaæ brakuj¹ce metody oraz uzupe³niæ
// zadeklarowane poni¿ej metody implementuj¹ce
// niezbêdne interfejsy.




} // koniec klasy Panel


public class Kolos extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Tu proszê dopisaæ brakuj¹ce metody oraz uzupe³niæ
	// zadeklarowane poni¿ej metody implementuj¹ce
	// niezbêdne interfejsy.

	private Panel panel = new Panel();
	
	private HashSet<Banknot> banknots = new HashSet<>();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("Plik");
	private JMenuItem authorMenu = new JMenuItem("Autor");
	private JMenuItem exitMenu = new JMenuItem("Zakoñcz");
	
	private JLabel waluta = new JLabel("Waluta: ");
	private JTextField walutaField = new JTextField(5);
	
	private JLabel wartosc = new JLabel("Wartoœæ: ");
	private JTextField wartoscField = new JTextField(5);
	
	private JButton authorButton = new JButton("Autor");
	private JButton	addButton = new JButton("Dodaj Banknot");
	private JButton	deleteButton = new JButton("Usuñ Banknot");
	private JButton	deleteAllButton = new JButton("Usuñ wszystkie");
	
	private WidokKolekcji widokKolekcji = new WidokKolekcji(banknots);

	public Kolos() {
		super("Adrian W.");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		authorButton.addActionListener(this);
		authorMenu.addActionListener(this);
		exitMenu.addActionListener(this);
		addButton.addActionListener(this);
		deleteAllButton.addActionListener(this);
		deleteButton.addActionListener(this);
		
		setJMenuBar(menuBar);
		menuBar.add(file);
		file.add(authorMenu);
		file.add(exitMenu);
		
		panel.add(authorButton);
		
		panel.add(waluta);
		panel.add(walutaField);
		panel.add(wartosc);
		panel.add(wartoscField);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(deleteAllButton);
		panel.add(widokKolekcji);
		
		panel.addKeyListener(panel);
		panel.setFocusable(true);
		
		
		setContentPane(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Kolos();
		
		Drukarka drukarka = new Drukarka();
		Producent producentJeden = new Producent(drukarka, "Adrian W.", 1);
		Producent producentDwa = new Producent(drukarka, "Adrian W.", 2);
		
		producentJeden.start();
		producentDwa.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == authorButton) {
			JOptionPane.showMessageDialog(this, "Adrian W.", "Autor", JOptionPane.INFORMATION_MESSAGE);
		}
		if(source == authorMenu) {
			JOptionPane.showMessageDialog(this, "Adrian W.", "Autor", JOptionPane.INFORMATION_MESSAGE);
		}
		if(source == exitMenu) {
			System.exit(0);
		}
		if(source == addButton) {
			int wartosc;
			String waluta;
			
			try {
				wartosc = Integer.parseInt(wartoscField.getText());
				if(walutaField.getText() != null && !walutaField.getText().equals("")) {
					waluta = walutaField.getText();
					banknots.add(new Banknot(waluta, wartosc));
					widokKolekcji.refresh();
				}else {
					JOptionPane.showMessageDialog(this, "Pole waluta nie moze byc puste", "B³¹d", JOptionPane.ERROR_MESSAGE);
				}
			}catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "W polu wartoœæ musi byæ liczba ca³kowita", "B³¹d", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(source == deleteAllButton){
			banknots.removeAll(banknots);
			walutaField.setText("");
			wartoscField.setText("");
			widokKolekcji.refresh();
		}
		if(source == deleteButton) {
			int wartosc;
			String waluta;
			
			try {
				wartosc = Integer.parseInt(wartoscField.getText());
				if(walutaField.getText() != null && !walutaField.getText().equals("")) {
					waluta = walutaField.getText();
					
					banknots.remove(new Banknot(waluta, wartosc));
					widokKolekcji.refresh();
				}else {
					JOptionPane.showMessageDialog(this, "Pole waluta nie moze byc puste", "B³¹d", JOptionPane.ERROR_MESSAGE);
				}
			}catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "W polu wartoœæ musi byæ liczba ca³kowita", "B³¹d", JOptionPane.ERROR_MESSAGE);
			}
		}
		panel.requestFocus();
	}

} // koniec klasy Okno


//KLASA POMOCNICZA DO ZADANIA NR 6
class Drukarka {

	volatile int id = 0; // id Producenta, który ostatnio korzysta³ z drukarki
	int nr = 0; // numer zadania (automatycznie incrementowany). 

// Proszê zmodyfikowaæ metodê drukuj zgodnie z wytycznymi do zadania 6.

	synchronized void drukuj(String tekst, int id) {
		if(this.id == id) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(nr++ + ": " + tekst + " " + id);
			this.id = id;
		}
		notify();

	}
} // koniec klasy Drukarka


// KLASA POMOCNICZA DO ZADANIA NR 6
// W TEJ KLASIE NIE TRZEBA NIC MODYFIKOWAÆ!!!
class Producent extends Thread {	
	Drukarka drukarka;
	String tekst;
	int id;

	public Producent(Drukarka drukarka, String tekst, int id) {
		this.drukarka = drukarka;
		this.tekst = tekst;
		this.id = id;
	}

	@Override
	public void run() {
		Random random = new Random();
		while (true){
			drukarka.drukuj(tekst, id);
			try {
				sleep(500 + random.nextInt(3000));
			} catch (InterruptedException e) { }
		}
	}	
	} // koniec klasy Producent
