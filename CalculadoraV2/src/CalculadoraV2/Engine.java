package CalculadoraV2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Hugo Perez
 * @version 2.0
 */
public class Engine extends JFrame implements ActionListener {
	// Atributos de la clase Engine
	// Marco de la ventana
	private Container panelPrincipal;
	private JFrame frame;
	// Panel general que ocupa toda la ventana
	private JPanel contentPanel;
	// Panel norte que contiene el display
	private JPanel displayPanel;
	private JPanel botonesOperadores;
	private JPanel botonesRegulares;
	private JPanel botonesBase;

	// Panel sur que contiene los botones
	private JPanel buttonPanel;
	// Display
	private JTextField pantalla;
	// Botones
	private JButton n0;
	private JButton n1;
	private JButton n2;
	private JButton n3;
	private JButton n4;
	private JButton n5;
	private JButton n6;
	private JButton n7;
	private JButton n8;
	private JButton n9;
	private JButton divide;
	private JButton multiply;
	private JButton subtract;
	private JButton add;
	private JButton equal;
	private JButton reset;
	private JButton eliminar;
	private JButton coma;
	private JButton base2;
	private JButton base8;
	private JButton base10;
	private JButton base16;
	private JButton INFO;
	private JButton onwer;

	// Tipos de boton
	private enum ButtonType {
		REGULAR, OPERATOR, INFO, BASE
	}

	// Almacenar temporalmente ciertos valores
	private double num1, num2, result;
	private char operation;

	/**
	 * 
	 * 
	 * @param msg
	 */
	public Engine(String msg) {
	    super(msg);

	    // Contenedor principal
	    this.contentPanel = new JPanel(new BorderLayout());
	    this.setContentPane(this.contentPanel);
	    this.contentPanel.setBackground(new Color(45, 45, 45)); // Cambiar color del fondo principal

	    // Panel de arriba
	    this.displayPanel = new JPanel();
	    this.displayPanel.setBackground(new Color(45, 45, 45)); // Cambiar color del panel superior

	    // Configuro el panel donde se van a ver los números seleccionados por los botones
	    this.pantalla = new JTextField(20);

	    // Panel de abajo
	    this.buttonPanel = new JPanel();
	    this.buttonPanel.setLayout(new FlowLayout());
	    this.buttonPanel.setBackground(new Color(45, 45, 45)); // Cambiar color del panel inferior

	    // Configuro los botones
	    this.botonesBase = new JPanel();
	    this.botonesBase.setBackground(new Color(45, 45, 45)); // Cambiar color de fondo de botones base
	    this.botonesOperadores = new JPanel(new GridLayout(5, 3, 5, 5));
	    this.botonesOperadores.setBackground(new Color(45, 45, 45)); // Cambiar color de fondo de operadores
	    this.botonesRegulares = new JPanel(new GridLayout(5, 1, 5, 5));
	    this.botonesRegulares.setBackground(new Color(45, 45, 45)); // Cambiar color de fondo de regulares

	    // Inicializo los botones con el método
	    inicializarBotones();

	    // Agregar botones base
	    this.botonesBase.add(this.base2);
	    this.botonesBase.add(this.base8);
	    this.botonesBase.add(this.base10);
	    this.botonesBase.add(this.base16);

	    // Agregar botones numéricos
	    this.botonesRegulares.add(this.n1);
	    this.botonesRegulares.add(this.n2);
	    this.botonesRegulares.add(this.n3);
	    this.botonesRegulares.add(this.n4);
	    this.botonesRegulares.add(this.n5);
	    this.botonesRegulares.add(this.n6);
	    this.botonesRegulares.add(this.n7);
	    this.botonesRegulares.add(this.n8);
	    this.botonesRegulares.add(this.n9);
	    this.botonesRegulares.add(this.reset);
	    this.botonesRegulares.add(this.n0);
	    this.botonesRegulares.add(this.equal);
	    this.botonesRegulares.add(this.INFO);
	    this.botonesRegulares.add(this.coma);
	    this.botonesRegulares.add(this.onwer);

	    // Agregar botones de operaciones
	    this.botonesOperadores.add(this.add);
	    this.botonesOperadores.add(this.subtract);
	    this.botonesOperadores.add(this.multiply);
	    this.botonesOperadores.add(this.divide);
	    this.botonesOperadores.add(this.eliminar);

	    // Agregar los botones a la ventana
	    this.buttonPanel.add(this.botonesBase, BorderLayout.SOUTH);
	    this.buttonPanel.add(this.botonesRegulares, BorderLayout.CENTER);
	    this.buttonPanel.add(this.botonesOperadores, BorderLayout.EAST);
	    this.buttonPanel.setSize(800, 500);

	    // Asignar eventos a los botones y configuraciones de diseño
	    setSettings();
	    addActionEvent();

	    // Agregar los paneles al panel principal
	    this.contentPanel.add(this.buttonPanel, BorderLayout.CENTER);
	    this.displayPanel.setLayout(new FlowLayout());

	    // Hacer visible la ventana
	    this.setVisible(true);

	    // Cerrar la ventana
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// Método para configurar la ventana
	public void setSettings() {
	    this.setLocation(50, 100);
	    this.setSize(500, 700);

	    // Configuración de la pantalla
	    this.pantalla.setEditable(false); // El usuario no podrá escribir en la pantalla
	    this.pantalla.setHorizontalAlignment(JTextField.RIGHT); // Alinear texto a la derecha
	    this.pantalla.setPreferredSize(new Dimension(50, 50));
	    this.pantalla.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    this.pantalla.setFont(new Font("Verdana", Font.BOLD, 20));
	    this.pantalla.setBackground(Color.LIGHT_GRAY); // Fondo
	    this.pantalla.setForeground(Color.BLACK); // Texto
	    this.displayPanel.add(this.pantalla);
	    this.contentPanel.add(this.displayPanel, BorderLayout.NORTH);
	}


	/**
	 * Metodo que se encarga de inicializar todos los botones de forma automatica
	 * llamando asi a el metodo de createButton que se encarga de verificar que tipo
	 * de boton es para darle propiedades segun su tipo
	 */
	public void inicializarBotones() {
		// Botones numéricos
		this.n0 = createButton("0", ButtonType.REGULAR);
		this.n1 = createButton("1", ButtonType.REGULAR);
		this.n2 = createButton("2", ButtonType.REGULAR);
		this.n3 = createButton("3", ButtonType.REGULAR);
		this.n4 = createButton("4", ButtonType.REGULAR);
		this.n5 = createButton("5", ButtonType.REGULAR);
		this.n6 = createButton("6", ButtonType.REGULAR);
		this.n7 = createButton("7", ButtonType.REGULAR);
		this.n8 = createButton("8", ButtonType.REGULAR);
		this.n9 = createButton("9", ButtonType.REGULAR);
		this.coma = createButton(".", ButtonType.REGULAR);

		// Botones de operadores
		this.add = createButton("+", ButtonType.OPERATOR);
		this.subtract = createButton("-", ButtonType.OPERATOR);
		this.multiply = createButton("*", ButtonType.OPERATOR);
		this.divide = createButton("/", ButtonType.OPERATOR);
		this.equal = createButton("=", ButtonType.OPERATOR);
		this.reset = createButton("C", ButtonType.OPERATOR);
		this.eliminar = createButton("\u2190", ButtonType.OPERATOR);

		// Botones de base
		this.base2 = createButton("B2", ButtonType.BASE);
		this.base8 = createButton("B8", ButtonType.BASE);
		this.base10 = createButton("B10", ButtonType.BASE);
		this.base16 = createButton("B16", ButtonType.BASE);

		// Botones info
		this.INFO = createButton("INFO", ButtonType.INFO);
		this.onwer = createButton("ONW", ButtonType.INFO);

	}

	/**
	 * Metodo que se encarga de crear los botones que es llamado en la clase que se
	 * encarga de inizializarlo, este metodo verifica que tipo de boton es el que es
	 * y segun el tipo le proporciona un color diferente o no
	 * 
	 * @param texto
	 * @param tipo
	 * @return
	 */
	public JButton createButton(String texto, ButtonType tipo) {
		JButton boton = new JButton(texto);
		boton.setPreferredSize(new Dimension(100, 80));

		if (tipo == ButtonType.OPERATOR) {
			boton.setBackground(Color.LIGHT_GRAY);
		} else if (tipo == ButtonType.REGULAR) {
			boton.setBackground(Color.WHITE);
		} else if (tipo == ButtonType.BASE) {
			boton.setBackground(Color.RED);
		} else if (tipo == ButtonType.INFO) {
			boton.setBackground(Color.BLUE);
		}
		boton.setFont(new Font("Arial", Font.BOLD, 20)); // Fuente grande
		boton.setFocusPainted(false); // Quitar borde de foco
	    boton.setBorder(BorderFactory.createEmptyBorder()); // Quitar bordes
		return boton;

	}

	/**
	 * Metodo que recoge todos los botones de la calculadora, los mete en un array
	 * de botones y va recorriendo el array y añadiendo AcionListener a todos los
	 * botones del array de forma
	 */
	public void addActionEvent() {
		JButton[] buttons = { this.n0, this.n1, this.n2, this.n3, this.n4, this.n5, this.n6, this.n7, this.n8, this.n9,
				this.divide, this.multiply, this.subtract, this.add, this.equal, this.reset, this.eliminar, this.coma,
				this.base2 };
		for (JButton button : buttons) {
			button.addActionListener(this);
		}
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand(); // Contiene el contenido del botón
		String textoActual = this.pantalla.getText();
		switch (comando) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case ".":
			this.pantalla.setText(this.pantalla.getText() + comando);
			break;
		case "+":
		case "-":
		case "*":
		case "/":
			if (!textoActual.isEmpty()) {
				if (textoActual.matches(".*[0-9.]$")) { // Si el texto termina en un número
					this.pantalla.setText(textoActual + " " + comando + " ");
				} else {
					this.pantalla.setText(textoActual.substring(0, textoActual.length() - 3) + " " + comando + " ");
				}
			} else if (this.num1 != 0) { // Usar el resultado previo como num1 si está disponible
				this.pantalla.setText(this.num1 + " " + comando + " ");
			}
			break;
		case "=":
			if (!this.pantalla.getText().isEmpty()) {
				// Separar la operación de la pantalla
				String[] partes = this.pantalla.getText().split(" ");
				if (partes.length == 3) { // Asegurar que hay formato correcto (num1 operador num2)
					try {
						this.num1 = Double.parseDouble(partes[0].replace(",", "."));
						this.operation = partes[1].charAt(0);
						this.num2 = Double.parseDouble(partes[2]);
						if (this.operation == '/' && this.num2 == 0) {
							this.pantalla.setText("Error: No se puede dividir entre 0");
						} else {
							this.result = operacion();
							String resultadoReducido = String.format("%.2f", this.result); // Limitar a 2 decimales
							this.pantalla.setText(resultadoReducido);
						}
					} catch (NumberFormatException ex) {
						this.pantalla.setText("Error: Formato inválido");
					}
				}
			}
			break;
		case "C":
			// Reiniciar pantalla y valores
			this.pantalla.setText("");
			this.num1 = 0;
			this.num2 = 0;
			break;
		case "\u2190": // Botón de borrar un carácter
			if (!textoActual.isEmpty()) {
				this.pantalla.setText(textoActual.substring(0, textoActual.length() - 1));
			}
			break;
		case "B2":
			if (!this.pantalla.getText().isEmpty()) {
				try {
					// Convertir el texto de la pantalla a un número entero
					int num = Integer.parseInt(this.pantalla.getText());

					// Crear un StringBuilder para almacenar el resultado binario
					StringBuilder binary = new StringBuilder();

					// Si el número es 0, el resultado binario es "0"
					if (num == 0) {
						binary.append("0");
					} else {
						// Convertir el número a binario
						while (num > 0) {
							int bin = num % 2; // Obtener el residuo (0 o 1)
							binary.insert(0, bin); // Insertar al inicio de la cadena
							num = num / 2; // Dividir el número entre 2
						}
					}

					// Establecer el resultado en la pantalla
					this.pantalla.setText(binary.toString());

				} catch (NumberFormatException ex) {
					this.pantalla.setText("Error");
				}
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Metodo que opera todo los posibles resultados
	 * 
	 * @return
	 */
	public double operacion() {
		switch (this.operation) {
		case '+':
			return this.num1 + this.num2;
		case '-':
			return this.num1 - this.num2;
		case '*':
			return this.num1 * this.num2;
		case '/':
			return this.num1 / this.num2;
		default:
			return 0.0;
		}

	}
}
