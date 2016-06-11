import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

//Codice generato dall'editor e un pò modificato ("a mano", diciamo) da me
public class ChimGUI {

	private JFrame frmBilanciatoreDiReazioni;
	private JTextField textFieldIn;
	private JTextField txtFieldOut;
	private JTextArea txtrEccoQuaLa;
	private JScrollPane scrollPane;
	private final Action actionBilancia = new SwingAction();
	private String formulaOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChimGUI window = new ChimGUI();
					window.frmBilanciatoreDiReazioni.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChimGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBilanciatoreDiReazioni = new JFrame();
		frmBilanciatoreDiReazioni.setTitle("Bilanciatore di reazioni");
		frmBilanciatoreDiReazioni.setBounds(100, 100, 622, 112);
		frmBilanciatoreDiReazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{167, 396, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmBilanciatoreDiReazioni.getContentPane().setLayout(gridBagLayout);
		
		textFieldIn = new JTextField();
		textFieldIn.setToolTipText("Inserisci qui la formula da bilanciare, ad esempio H2+O2=H2O");
		GridBagConstraints gbc_textFieldIn = new GridBagConstraints();
		gbc_textFieldIn.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIn.gridx = 1;
		gbc_textFieldIn.gridy = 0;
		frmBilanciatoreDiReazioni.getContentPane().add(textFieldIn, gbc_textFieldIn);
		textFieldIn.setColumns(10);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frmBilanciatoreDiReazioni.getContentPane().add(scrollPane, gbc_scrollPane);
		
		txtrEccoQuaLa = new JTextArea();
		scrollPane.setViewportView(txtrEccoQuaLa);
		txtrEccoQuaLa.setWrapStyleWord(true);
		txtrEccoQuaLa.setLineWrap(true);
		txtrEccoQuaLa.setEditable(false);
		txtrEccoQuaLa.setText("Ecco qua la prima beta release del programma!\r\nversione: 0.9\r\n\r\n-Le formule non ioniche vanno scritte ad es. cosi': H2+O2=H2O (si possono mettere tranquillamente degli spazi, qua li usero' se serve chiarezza).\r\nEcco qualche esempio: \r\nHNO3+H2S=NO+S+H2O, Mn(NO3)2+PbO2+HNO3=Pb(NO3)2+HMnO4+H2O,\r\nBi(NO3)3 + KOH = H3BiO3 + KNO3.\r\n\r\n-Nelle formule ioniche, inserire nel composto l'\\\"elemento\\\" Cc, che rappresenta una carica elettrica, seguito da un \\\"indice stechiometrico\\\" (che puo' essere, a differenza del solito, anche negativo) il cui segno rappresenta il fatto che la carica sia positiva o negativa e il cui numero rappresenta il numero di carica del composto. Per esempio, prendiamo l'Esercizio Guidato 3 a pag 287 del libro di chimica: il composto si scrivera' cosi: Cr2O7Cc-2 + NH3 + HCc = CrCc3 + N2 + H2O.\r\n\r\nEsempi: ClO3Cc-1 = ClO4Cc-1 + ClCc-1, MnO3+FeCc2+HCc=MnCc2+FeCc3+H2O\r\n\r\nNon fate caso al resto dell'output, serve a me per il debug. In teoria basta vedere solo l'ultima riga. Per uscire digitare \"esci\" (senza virgolette).");

		JButton btnNewButton = new JButton("Bilancia reazione");
		btnNewButton.setAction(actionBilancia);
		btnNewButton.setText("Bilancia reazione");
		btnNewButton.setToolTipText("Bilancia la reazione scritta sopra");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		frmBilanciatoreDiReazioni.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		txtFieldOut = new JTextField();
		txtFieldOut.setToolTipText("Qui apparir\u00E0 la formula bilanciata");
		txtFieldOut.setEditable(false);
		GridBagConstraints gbc_txtFieldOut = new GridBagConstraints();
		gbc_txtFieldOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOut.gridx = 1;
		gbc_txtFieldOut.gridy = 2;
		frmBilanciatoreDiReazioni.getContentPane().add(txtFieldOut, gbc_txtFieldOut);
		txtFieldOut.setColumns(10);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			formulaOutput=Chimbase.chimicaMetodo(textFieldIn.getText());
			txtFieldOut.setText(formulaOutput);
		}
	}
}
