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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	private JPanel botonesLetras;
	private JPanel botonMarca;

	//
	private JPanel ventanaInfo;

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
	private JButton A;
	private JButton B;
	private JButton C;
	private JButton D;
	private JButton E;
	private JButton F;
	private JButton marca;

	// Tipos de boton
	private enum ButtonType {
		REGULAR, OPERATOR, INFO, BASE, LETRA, CASIO
	}

	// Almacenar temporalmente ciertos valores
	private double num1, num2, result;
	private int base = 10;
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

		// Configuro el panel donde se van a ver los números seleccionados por los
		// botones
		this.pantalla = new JTextField(20);

		// Panel de abajo
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new FlowLayout());
		this.buttonPanel.setBackground(new Color(45, 45, 45)); // Cambiar color del panel inferior

		// Configuro los botones
		this.botonMarca = new JPanel();
		this.botonMarca.setBackground(new Color(45, 45, 45));
		this.botonesBase = new JPanel();
		this.botonesBase.setBackground(new Color(45, 45, 45)); // Cambiar color de fondo de botones base
		this.botonesLetras = new JPanel(new GridLayout(2, 3, 5, 5));
		this.botonesLetras.setBackground(new Color(45, 45, 45)); // Cambiar color de fondo de botones base
		this.botonesOperadores = new JPanel(new GridLayout(5, 3, 5, 5));
		this.botonesOperadores.setBackground(new Color(45, 45, 45)); // Cambiar color de fondo de operadores
		this.botonesRegulares = new JPanel(new GridLayout(5, 1, 5, 5));
		this.botonesRegulares.setBackground(new Color(45, 45, 45)); // Cambiar color de fondo de regulares

		// Inicializo los botones con el método
		inicializarBotones();

		// Agregar botones base
		this.botonMarca.add(this.marca);
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
		this.botonesLetras.add(this.A);
		this.botonesLetras.add(this.B);
		this.botonesLetras.add(this.C);
		this.botonesLetras.add(this.D);
		this.botonesLetras.add(this.E);
		this.botonesLetras.add(this.F);

		// Agregar botones de operaciones
		this.botonesOperadores.add(this.add);
		this.botonesOperadores.add(this.subtract);
		this.botonesOperadores.add(this.multiply);
		this.botonesOperadores.add(this.divide);
		this.botonesOperadores.add(this.eliminar);

		// Agregar los botones a la ventana
		this.buttonPanel.add(this.botonesLetras, BorderLayout.SOUTH);
		this.buttonPanel.add(this.botonesBase, BorderLayout.SOUTH);
		this.buttonPanel.add(this.botonesRegulares, BorderLayout.CENTER);
		this.buttonPanel.add(this.botonesOperadores, BorderLayout.EAST);
		this.buttonPanel.add(this.botonMarca, BorderLayout.CENTER);
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
		this.setSize(500, 900);

		// Configuración de la pantalla
		this.pantalla.setEditable(false); // El usuario no podrá escribir en la pantalla
		this.pantalla.setHorizontalAlignment(JTextField.RIGHT); // Alinear texto a la derecha
		this.pantalla.setPreferredSize(new Dimension(50, 50));
		this.pantalla.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		this.pantalla.setFont(new Font("Verdana", Font.BOLD, 20));
		this.pantalla.setBackground(new Color(255, 255, 255)); // Fondo
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
		//Boton marca
		this.marca = createButton("CASIO", ButtonType.CASIO);
		
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
		this.A = createButton("A", ButtonType.LETRA);
		this.B = createButton("B", ButtonType.LETRA);
		this.C = createButton("C", ButtonType.LETRA);
		this.D = createButton("D", ButtonType.LETRA);
		this.E = createButton("E", ButtonType.LETRA);
		this.F = createButton("F", ButtonType.LETRA);

		// Botones de operadores
		this.add = createButton("+", ButtonType.OPERATOR);
		this.subtract = createButton("-", ButtonType.OPERATOR);
		this.multiply = createButton("*", ButtonType.OPERATOR);
		this.divide = createButton("/", ButtonType.OPERATOR);
		this.equal = createButton("=", ButtonType.OPERATOR);
		this.reset = createButton("Cc", ButtonType.OPERATOR);
		this.eliminar = createButton("\u2190", ButtonType.OPERATOR);

		// Botones de base
		this.base2 = createButton("B2", ButtonType.BASE);
		this.base8 = createButton("B8", ButtonType.BASE);
		this.base10 = createButton("B10", ButtonType.BASE);
		this.base16 = createButton("B16", ButtonType.BASE);

		// Botones info
		this.INFO = createButton("INFO", ButtonType.INFO);
		this.onwer = createButton("OWN", ButtonType.INFO);

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
		} else if (tipo == ButtonType.LETRA) {
			boton.setBackground(Color.GREEN);
		} else if (tipo == ButtonType.CASIO) {
			boton.setBackground(Color.WHITE);
			boton.setPreferredSize(new Dimension(80, 20));
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
				this.base2, this.base8, this.base10, this.base16, this.INFO, this.onwer, this.A, this.B, this.C, this.D,
				this.E, this.F };
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
		case "A":
		case "B":
		case "C":
		case "D":
		case "E":
		case "F":
			this.pantalla.setText(this.pantalla.getText() + comando);
			break;
		case "+":
		case "-":
		case "*":
		case "/":
			if (!textoActual.isEmpty()) {
				if (textoActual.matches(".*[0-9A-F]$")) { // Si el texto termina en un número
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
				if (this.base == 10) {
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
				} else if (this.base == 2) {
					// Separar la operación de la pantalla
					String[] partes = this.pantalla.getText().split(" ");
					if (partes.length == 3) { // Asegurar que hay formato correcto (num1 operador num2)
						try {
							// Validar que las cadenas de entrada son números binarios válidos
							String binario1 = partes[0];
							String binario2 = partes[2];
							if (!binario1.matches("[01]+") || !binario2.matches("[01]+")) {
								this.pantalla.setText("Error: Formato binario inválido");
								break;
							}

							// Convertir binarios a decimales como `double`
							this.num1 = (double) Integer.parseInt(binario1, 2);
							this.num2 = (double) Integer.parseInt(binario2, 2);
							this.operation = partes[1].charAt(0);

							if (this.operation == '/' && this.num2 == 0) {
								this.pantalla.setText("Error: No se puede dividir entre 0");
							} else {
								// Llamada al método operacionBinario()
								this.result = operacionBinario();

								// Convertir el resultado decimal de nuevo a binario
								String resultadoBinario = Integer.toBinaryString((int) this.result);
								this.pantalla.setText(resultadoBinario); // Mostrar resultado en binario
							}
						} catch (NumberFormatException ex) {
							this.pantalla.setText("Error: Formato binario inválido");
						}
					}
				} else if (this.base == 8) {
					// Operación en base octal
					String[] partes = this.pantalla.getText().split(" ");
					if (partes.length == 3) {
						try {
							// Validar que las cadenas de entrada son números octales válidos
							String octal1 = partes[0];
							String octal2 = partes[2];
							if (!octal1.matches("[0-7]+") || !octal2.matches("[0-7]+")) {
								this.pantalla.setText("Error: Formato octal inválido");
								break;
							}

							// Convertir octales a decimales como `double`
							this.num1 = (double) Integer.parseInt(octal1, 8);
							this.num2 = (double) Integer.parseInt(octal2, 8);
							this.operation = partes[1].charAt(0);

							if (this.operation == '/' && this.num2 == 0) {
								this.pantalla.setText("Error: No se puede dividir entre 0");
							} else {
								// Llamada al método operacionOctal()
								this.result = operacionOctal();

								// Convertir el resultado decimal de nuevo a octal
								String resultadoOctal = Integer.toOctalString((int) this.result);
								this.pantalla.setText(resultadoOctal); // Mostrar resultado en octal
							}
						} catch (NumberFormatException ex) {
							this.pantalla.setText("Error: Formato octal inválido");
						}
					}
				} else if (this.base == 16) {
					// Lógica para números hexadecimales
					String[] partes = this.pantalla.getText().split(" ");
					if (partes.length == 3) {
						try {
							// Convertir las partes hexadecimales a decimales
							this.num1 = hexadecimalADecimal(partes[0]);
							this.operation = partes[1].charAt(0);
							this.num2 = hexadecimalADecimal(partes[2]);

							if (this.operation == '/' && this.num2 == 0) {
								this.pantalla.setText("Error: No se puede dividir entre 0");
							} else {
								this.result = operacion();
								String resultadoHexadecimal = decimalAHexadecimal(this.result); // Convertir el
																								// resultado a
																								// hexadecimal
								this.pantalla.setText(resultadoHexadecimal); // Mostrar resultado en pantalla
							}
						} catch (NumberFormatException ex) {
							this.pantalla.setText("Error: Formato hexadecimal inválido");
						}
					}
				}
			}
			break;
		case "Cc":
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
					if (this.base == 8) {
						this.base = 2; // Indico a que base va a pasar para operaciones futuras
						// Convertir el texto de la pantalla (octal) a un número entero en decimal
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en octal
						if (!input.matches("[0-7]+")) {
							this.pantalla.setText("Error: Formato octal inválido");
							break;
						}

						int num = Integer.parseInt(input, 8); // Convertir octal a decimal

						// Crear un StringBuilder para almacenar el resultado binario
						StringBuilder binary = new StringBuilder();

						// Si el número es 0, el resultado binario es "0"
						if (num == 0) {
							binary.append("0");
						} else {
							// Convertir el número de decimal a binario
							while (num > 0) {
								int bin = num % 2; // Obtener el residuo (0 o 1)
								binary.insert(0, bin); // Insertar al inicio de la cadena
								num = num / 2; // Dividir el número entre 2
							}
						}

						// Establecer el resultado en la pantalla (en binario)
						this.pantalla.setText(binary.toString());
					}
					if (this.base == 10) {
						this.base = 2;// Indico en que base estoy
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
					}
					if (this.base == 16) {
						this.base = 2; // Indico en qué base estoy (binario)

						// Convertir el texto de la pantalla (hexadecimal) a un número entero en decimal
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en hexadecimal
						if (!input.matches("[0-9A-Fa-f]+")) {
							this.pantalla.setText("Error: Formato hexadecimal inválido");
							break;
						}

						int num = Integer.parseInt(input, 16); // Convertir hexadecimal a decimal

						// Crear un StringBuilder para almacenar el resultado binario
						StringBuilder binary = new StringBuilder();

						// Si el número es 0, el resultado binario es "0"
						if (num == 0) {
							binary.append("0");
						} else {
							// Convertir el número de decimal a binario
							while (num > 0) {
								int bin = num % 2;
								binary.insert(0, bin);
								num = num / 2;
							}
						}

						// Establecer el resultado en la pantalla (en binario)
						this.pantalla.setText(binary.toString());

					}

				} catch (NumberFormatException ex) {
					this.pantalla.setText("Error: Formato inválido");
				}
			} else {
				this.base = 2;
			}
			break;
		case "B10":
			if (!this.pantalla.getText().isEmpty()) {
				try {
					if (this.base == 2) {
						this.base = 10; // Indico en qué base estoy (decimal)

						// Convertir el texto de la pantalla (binario) a un número entero en decimal
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en binario
						if (!input.matches("[01]+")) {
							this.pantalla.setText("Error: Formato binario inválido");
							break;
						}

						// Convertir el binario a decimal
						int num = Integer.parseInt(input, 2); // Convertir binario a decimal

						// Establecer el resultado en la pantalla (en decimal)
						this.pantalla.setText(Integer.toString(num));
					}
					if (this.base == 8) {
						this.base = 10; // Indico en qué base estoy (decimal)

						// Convertir el texto de la pantalla (octal) a un número entero en decimal
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en octal
						if (!input.matches("[0-7]+")) {
							this.pantalla.setText("Error: Formato octal inválido");
							break;
						}

						// Convertir el octal a decimal
						int num = Integer.parseInt(input, 8); // Convertir octal a decimal

						// Establecer el resultado en la pantalla (en decimal)
						this.pantalla.setText(Integer.toString(num));
					}
					if (this.base == 16) {
						this.base = 10; // Indico en qué base estoy (decimal)

						// Obtener el texto en hexadecimal
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en hexadecimal
						if (!input.matches("[0-9A-Fa-f]+")) {
							this.pantalla.setText("Error: Formato hexadecimal inválido");
							break;
						}

						// Llamar al método hexadecimalADecimal para convertir de hexadecimal a decimal
						double num = hexadecimalADecimal(input);

						// Establecer el resultado en la pantalla (en decimal)
						this.pantalla.setText(Double.toString(num));
					}
				} catch (NumberFormatException ex) {
					this.pantalla.setText("Error: Formato inválido");
				}
			} else {
				this.base = 2;
			}
			break;
		case "B8":
			if (!this.pantalla.getText().isEmpty()) {
				try {
					if (this.base == 2) {
						this.base = 8; // Indico en qué base estoy (octal)
						// Obtener el texto en binario
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en binario
						if (!input.matches("[01]+")) {
							this.pantalla.setText("Error: Formato binario inválido");
							break;
						}

						// Convertir binario a decimal
						int decimal = Integer.parseInt(input, 2);

						// Convertir decimal a octal
						String octal = Integer.toOctalString(decimal);

						// Establecer el resultado en la pantalla (en octal)
						this.pantalla.setText(octal);
					}
					if (this.base == 10) {
						this.base = 8; // Establecer que la base es octal

						// Obtener el número que está en la pantalla y convertirlo a decimal
						double numeroDecimal = Double.parseDouble(this.pantalla.getText().replace(",", "."));

						// Convertir el número decimal a octal
						String resultadoOctal = decimalAOctal(numeroDecimal);

						// Mostrar el resultado en octal en la pantalla
						this.pantalla.setText(resultadoOctal);
					}
					if (this.base == 16) {
						this.base = 8; // Indico en qué base estoy (octal)

						// Obtener el texto en hexadecimal
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en hexadecimal
						if (!input.matches("[0-9A-Fa-f]+")) {
							this.pantalla.setText("Error: Formato hexadecimal inválido");
							break;
						}

						// Convertir hexadecimal a decimal
						int decimal = Integer.parseInt(input, 16);

						// Convertir decimal a octal
						String octal = Integer.toOctalString(decimal);

						// Establecer el resultado en la pantalla (en octal)
						this.pantalla.setText(octal);
					}
				} catch (NumberFormatException ex) {
					this.pantalla.setText("Error: Formato inválido");
				}
			} else {
				this.base = 8;
			}
			break;
		case "B16": // Si se presiona el botón para convertir a hexadecimal
			if (!this.pantalla.getText().isEmpty()) {
				try {
					if (this.base == 2) {
						this.base = 16; // Indico en qué base estoy (hexadecimal)

						// Obtener el texto en binario
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en binario
						if (!input.matches("[01]+")) {
							this.pantalla.setText("Error: Formato binario inválido");
							break;
						}

						// Convertir binario a decimal
						int decimal = Integer.parseInt(input, 2);

						// Convertir decimal a hexadecimal
						String hexadecimal = Integer.toHexString(decimal).toUpperCase();

						// Establecer el resultado en la pantalla (en hexadecimal)
						this.pantalla.setText(hexadecimal);

					}
					if (this.base == 8) {
						this.base = 16; // Indico en qué base estoy (hexadecimal)

						// Obtener el texto en octal
						String input = this.pantalla.getText();

						// Verificar que la entrada sea válida en octal
						if (!input.matches("[0-7]+")) {
							this.pantalla.setText("Error: Formato octal inválido");
							break;
						}

						// Convertir octal a decimal
						int decimal = Integer.parseInt(input, 8);

						// Convertir decimal a hexadecimal
						String hexadecimal = Integer.toHexString(decimal).toUpperCase();

						// Establecer el resultado en la pantalla (en hexadecimal)
						this.pantalla.setText(hexadecimal);

					}
					if (this.base == 10) {
						this.base = 16;

						// Obtener el número que está en la pantalla y convertirlo a decimal
						double numeroDecimal = Double.parseDouble(this.pantalla.getText().replace(",", "."));

						// Convertir el número decimal a hexadecimal
						String resultadoHexadecimal = decimalAHexadecimal(numeroDecimal);

						// Mostrar el resultado en hexadecimal en la pantalla
						this.pantalla.setText(resultadoHexadecimal);
					}
				} catch (NumberFormatException ex) {
					this.pantalla.setText("Error: Formato inválido");
				}
			} else {
				this.base = 16;
			}
			break;
		case "INFO":
			JFrame ventanaInfo = new JFrame("Información");
			ventanaInfo.setSize(500, 300);
			ventanaInfo.setLocationRelativeTo(null); // Centrar ventana en la pantalla

			// Crear el panel principal con un diseño BorderLayout
			JPanel panelPrincipalI = new JPanel(new BorderLayout());
			panelPrincipalI.setBackground(new Color(240, 240, 240)); // Fondo gris claro

			// Título con estilo
			JLabel tituloInfo = new JLabel("Información de la calculadora:");
			tituloInfo.setFont(new Font("Arial", Font.BOLD, 18));
			tituloInfo.setHorizontalAlignment(SwingConstants.CENTER);
			tituloInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

			// Panel para la lista de información
			JPanel panelContenido = new JPanel(new GridLayout(0, 1, 5, 5)); // Un GridLayout para la lista
			panelContenido.setBackground(new Color(240, 240, 240)); // Igual que el fondo principal

			// Añadir la información al panel con etiquetas
			String[] funcionalidades = { "• Suma", "• Resta", "• Multiplicación", "• División",
					"• Conversión de bases numéricas (binario, octal, decimal, hexadecimal)", "• Borrar y reiniciar" };

			for (String texto : funcionalidades) {
				JLabel etiqueta = new JLabel(texto);
				etiqueta.setFont(new Font("Arial", Font.PLAIN, 16));
				etiqueta.setHorizontalAlignment(SwingConstants.LEFT);
				panelContenido.add(etiqueta);
			}

			// Agregar el título y el contenido al panel principal
			panelPrincipalI.add(tituloInfo, BorderLayout.NORTH);
			panelPrincipalI.add(panelContenido, BorderLayout.CENTER);

			// Configuración de la ventana
			ventanaInfo.add(panelPrincipalI);
			ventanaInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ventanaInfo.setVisible(true);
			break;
		case "OWN":
			JFrame ventanaAutor = new JFrame("Autor");
			ventanaAutor.setSize(400, 200);
			ventanaAutor.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

			// Panel principal con BorderLayout
			JPanel panelPrincipalA = new JPanel(new BorderLayout());
			panelPrincipalA.setBackground(new Color(240, 240, 240)); // Fondo gris claro

			// Título destacado
			JLabel tituloAutor = new JLabel("Información del Autor");
			tituloAutor.setFont(new Font("Arial", Font.BOLD, 18));
			tituloAutor.setHorizontalAlignment(SwingConstants.CENTER);
			tituloAutor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

			// Contenido del autor
			JPanel panelContenido1 = new JPanel(new GridLayout(0, 1, 5, 5));
			panelContenido1.setBackground(new Color(240, 240, 240)); // Igual al fondo principal

			// Información del autor
			String[] informacionAutor = { "• Nombre: Hugo Pérez", "• Curso: 2º DAM", "• Proyecto: Calculadora V2" };

			for (String texto : informacionAutor) {
				JLabel etiqueta = new JLabel(texto);
				etiqueta.setFont(new Font("Arial", Font.PLAIN, 16));
				etiqueta.setHorizontalAlignment(SwingConstants.LEFT);
				panelContenido1.add(etiqueta);
			}

			// Agregar componentes al panel principal
			panelPrincipalA.add(tituloAutor, BorderLayout.NORTH);
			panelPrincipalA.add(panelContenido1, BorderLayout.CENTER);

			// Configuración de la ventana
			ventanaAutor.add(panelPrincipalA);
			ventanaAutor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ventanaAutor.setVisible(true);
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
	
	/**
	 * 
	 * @return
	 */
	public double operacionBinario() {
		try {
			// Convertir los valores de this.num1`y this.num2 a enteros
			int num1 = (int) this.num1;
			int num2 = (int) this.num2;

			// Verificar que num1 y num2 sean representaciones válidas en binario
			String binario1 = Integer.toBinaryString(num1);
			String binario2 = Integer.toBinaryString(num2);

			// Convertir las representaciones binarias a decimales para operar
			int decimalNum1 = Integer.parseInt(binario1, 2);
			int decimalNum2 = Integer.parseInt(binario2, 2);
			double resultadoDecimal = 0;

			// Realizar la operación indicada
			switch (this.operation) {
			case '+':
				resultadoDecimal = decimalNum1 + decimalNum2;
				break;
			case '-':
				resultadoDecimal = decimalNum1 - decimalNum2;
				break;
			case '*':
				resultadoDecimal = decimalNum1 * decimalNum2;
				break;
			case '/':
				if (decimalNum2 == 0) {
					throw new ArithmeticException("División por cero");
				}
				resultadoDecimal = (double) decimalNum1 / decimalNum2;
				break;
			default:
				throw new IllegalArgumentException("Operación no válida");
			}

			return resultadoDecimal;

		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Error: Formato binario inválido", e);
		}
	}

	/**
	 * Convierte un número hexadecimal a decimal
	 * 
	 * @param hex Cadena en formato hexadecimal
	 * @return Valor decimal correspondiente
	 */
	public double hexadecimalADecimal(String hex) {
		try {
			// Convertir el valor hexadecimal a un número decimal
			return Integer.parseInt(hex, 16);
		} catch (NumberFormatException ex) {
			throw new NumberFormatException("Formato hexadecimal inválido");
		}
	}

	/**
	 * Convierte un número decimal a hexadecimal
	 * 
	 * @param numeroDecimal Número decimal a convertir
	 * @return Cadena en formato hexadecimal
	 */
	public String decimalAHexadecimal(double numeroDecimal) {
		// Convertir el número decimal a entero (solo parte entera)
		int numeroEntero = (int) numeroDecimal;
		// Convertir el entero a hexadecimal
		return Integer.toHexString(numeroEntero).toUpperCase(); // Formato consistente en mayúsculas
	}

	/**
	 * Comvierte de numero decimal a octal
	 * 
	 * @param numeroDecimal
	 * @return
	 */
	public String decimalAOctal(double numeroDecimal) {
		// Convertir el número decimal a entero (solo parte entera)
		int numeroEntero = (int) numeroDecimal;

		// Verificar si el número es 0
		if (numeroEntero == 0) {
			return "0";
		}

		// Crear un StringBuilder para almacenar el resultado octal
		StringBuilder octal = new StringBuilder();

		// Mientras el número sea mayor que 0
		while (numeroEntero > 0) {
			// Obtener el residuo de la división entre 8
			int residuo = numeroEntero % 8;
			// Insertar el residuo al principio del resultado
			octal.insert(0, residuo);
			// Dividir el número entre 8
			numeroEntero = numeroEntero / 8;
		}

		// Retornar el resultado octal como una cadena
		return octal.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public double operacionOctal() {
		double resultado = 0;

		switch (this.operation) {
		case '+':
			resultado = this.num1 + this.num2;
			break;
		case '-':
			resultado = this.num1 - this.num2;
			break;
		case '*':
			resultado = this.num1 * this.num2;
			break;
		case '/':
			if (this.num2 != 0) {
				resultado = this.num1 / this.num2;
			} else {
				this.pantalla.setText("Error: No se puede dividir entre 0");
				return 0; // Retornar 0 en caso de división por cero
			}
			break;
		default:
			this.pantalla.setText("Error: Operador no válido");
			return 0; // Retornar 0 si el operador es inválido
		}

		return resultado;
	}

}
