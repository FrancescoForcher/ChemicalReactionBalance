
import java.io.Console;//importa la console
import java.io.IOException;//Prima o poi userò questa libreria...
import java.util.regex.Pattern;//importa le espressioni regolari
import java.util.regex.Matcher;//vedi sopra
import java.util.*;//importa il Java Collection Framework (nota che i package non sono gerarchici, importando util.* non importo anche le regex qui sopra)
//import java.math.*;

/**
 * Programma che bilancia una reazione chimica
 * 
 * @author Francesco Forcher
 * @version 0.9, 15\04\2012
 *
 */
public class Chimbase {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		
		//Controlla se c'è la console
		Console c = System.console();
		if (c == null) {
			System.err.println("Errore: console non rilevata");	
			System.exit(-1);
		}
/*
		double[][] MATTEST = {{1,2,1,0},
							  {2,3,0,1}};
		double[][] MATTEST2 = {{0,12,8,2},
				{1,-1,6,3},
				{1,2,4,2},
				{0,6,4,1},
				{0,12,8,2}};
		double[][] MatVuota;
		double[] vettsoltest;
*/

		/*reazioni x i test:
		 * "Normali":
		 * H2+O2=H2O
		 * Bi(NO3)3+KOH=H3BiO3+KNO3
		 * 
		 * Redox:
		 * HNO3+H2S=NO+S+H2O
		 * Mn(NO3)2+PbO2+HNO3=Pb(NO3)2+HMnO4+H2O
		 * KMnO4+FeSO4+H2SO4=K2SO4+MnSO4+Fe2(SO4)3+H2O
		 * 
		 * Ioniche:
		 * ClO3Cc-1=ClO4Cc-1+ClCc-1
		 * MnO3+FeCc2+HCc=MnCc2+FeCc3+H2O
		 * CrCc3+MnO4Cc-1+H2O=Cr2O7Cc-2+MnCc2+HCc
		 * Cr2O7Cc-2+NH3+HCc=CrCc3+N2+H2O
		 * 
		 */
		
		//Ecco le istruzioni iniziali
		//System.out.println("\n");
		/*System.out.println("\nEcco qua la prima beta release del programma!\nversione: 0.9\n" +
							"\n" +
							"-Le formule non ioniche vanno scritte ad es. cosi': H2+O2=H2O (si possono\n" +
							" mettere tranquillamente degli spazi, qua li usero' se serve chiarezza).\n" +
							" Ecco qualche esempio: HNO3+H2S=NO+S+H2O, Mn(NO3)2+PbO2+HNO3=Pb(NO3)2+HMnO4+H2O," +
							" Bi(NO3)3 + KOH = H3BiO3 + KNO3\n" +
							"\n" +
							"-Nelle formule ioniche, inserire nel composto l'\"elemento\" Cc, che rappresenta\n" +
							" una carica elettrica, seguito da un \"indice stechiometrico\" (che puo' essere,\n" +
							" a differenza del solito, anche negativo) il cui segno rappresenta il fatto che\n" +
							" la carica sia positiva o negativa e il cui numero rappresenta il numero di\n" +
							" carica del composto. Per esempio, prendiamo l'Esercizio Guidato 3 a pag 287\n" +
							" del libro di chimica: il composto si scrivera' cosi: \n" +
							"  Cr2O7Cc-2 + NH3 + HCc = CrCc3 + N2 + H2O \n" +
							" Esempi: ClO3Cc-1 = ClO4Cc-1 + ClCc-1, MnO3+FeCc2+HCc=MnCc2+FeCc3+H2O\n" +
							"\n" +
							"Non fate caso al resto dell'output, serve a me per il debug. In teoria basta\n" +
							"vedere solo l'ultima riga. Per uscire digitare \"esci\" (senza virgolette).");*/
		
		public static String chimicaMetodo(String Formula) {
		
		System.out.println();
			try {
				while (true) {

					double[][] MatriceReazione; // La matrice che arriverà da analizzaFormula
					double[] vettTerminiNoti;//Vettore dei termini noti
					double[] vettSoluzioneTemporanea;
					//nota che TempVetSol contiene double, mentre VettSoluzione contiene int
					int[] vettSoluzione;
					List<String> CompEst=new ArrayList<String>();/*Dove verranno memorizzati i composti 
																   e la posizione del simbolo di reazione ("=")*/											
					int i;
					int numDiInteri;
					String FormulaOutput;
					int numTentativi=150;
					
					i=0;
					numDiInteri = 0;
					/*
					//Leggi dalla console la formula ed esci se si scrive "Esci" (maiscole non contano)
					String Formula=c.readLine("Inserisci la formula non bilanciata: ");
					if (Formula.equalsIgnoreCase("esci"))
						System.exit(0);
					
					if ((Formula.equalsIgnoreCase("set numTentativi"))) {
						numTentativi=Integer.parseInt(c.readLine("Scrivi il nuovo valore per il numero massimo di tentativi da effettuare \n" +
								"per risolvere il sistema"));
					}
					*/
					//analizza la formula
					MatriceReazione=analizzaFormula(Formula,CompEst);

					vettSoluzioneTemporanea= new double[MatriceReazione.length];

					/*Crea il vettore dei termini noti, mettendo a=1
					 * es di vettore termini noti:	0
					 * 								0
					 * 								0
					 * 								1
					 */
					vettTerminiNoti=new double[MatriceReazione.length];
					for (i=0;i<MatriceReazione.length-1;i++);{
						vettTerminiNoti[i]=0d;
					}
					vettTerminiNoti[vettTerminiNoti.length-1]=1;
					System.out.println("VettoreTerminiNoti: ");
					Sistemi.vediMat(vettTerminiNoti);

					//Risolvi con a=1 (vedi due righe sopra), se i coeff non sono tutti interi,risolvi con a=2, e così via...
					risolvi:
						while (!(numDiInteri==vettSoluzioneTemporanea.length)) {
							//se a ha superato 150, interrmopi e dà errore
							if (vettTerminiNoti[vettTerminiNoti.length-1]>=numTentativi){
								System.err.println("Errore: per a=150 non è ancora stata trovata una soluzione");
								System.exit(-2);
							}
							
							numDiInteri = 0;

							vettSoluzioneTemporanea=Sistemi.risolviSistLin(MatriceReazione, vettTerminiNoti);

							//ORRENDO ma non mi ricordo il metodo printf. Da cambiare, quando ho tempo
							System.out.print("La soluzione temporanea (per a=");
							System.out.print(vettTerminiNoti[vettTerminiNoti.length-1]);
							System.out.println(") e':");
							Sistemi.vediMat(vettSoluzioneTemporanea);
							for (Double sol:vettSoluzioneTemporanea) {
								//bdsol.round(mc);
								//ts=ts.substring(0, ts.length()-1);
								//double numts=Double.parseDouble(ts);

								if (Math.rint(sol)==sol){
									numDiInteri++;
								} else {
									vettTerminiNoti[vettTerminiNoti.length-1]=vettTerminiNoti[vettTerminiNoti.length-1]+1;
									continue risolvi;
								}
							}
						}

					//Rendi le soluzioni integer e spostale su VettoreSoluzione
					vettSoluzione=new int[vettSoluzioneTemporanea.length];
					for (i=0;i<vettSoluzioneTemporanea.length;i++)
						vettSoluzione[i]=(int) Math.round(vettSoluzioneTemporanea[i]);

					//stampa TempVetSol
					System.out.println("La soluzione finale e' ");
					Sistemi.vediMat(vettSoluzione);

					//Costruisci la stringa finale
					StringBuilder ForFin= new StringBuilder(100);

					//posizione di npiu
					int PosDiNpiu=CompEst.size()-1;
					//CompEst.size()-1 perchè nell'ultimo posto c'è npiu
					for(i=0;i<CompEst.size()-1;i++){
						//se siamo all'inizio non mettere niente prima del coefficiente e del composto
						if (i==CompEst.size()-1)
							continue;
						if (i==0) {
							//ogni volta controlla: se il coeff è 1 non mettere niente (stringa vuota)
							ForFin.append((vettSoluzione[i]==1) ? "" : vettSoluzione[i]);
							ForFin.append(CompEst.get(i));
						}
						//se siamo arrivati ai prodotti (i=npiu) metti un "="
						else if (i==Integer.parseInt(CompEst.get(PosDiNpiu))) {
							ForFin.append("=");
							ForFin.append((vettSoluzione[i]==1) ? "" : vettSoluzione[i]);
							ForFin.append(CompEst.get(i));
						}
						//nel resto dei casi metti un "+"
						else {
							ForFin.append("+");
							//PROBLEMA: Dopo la prima volta dice ArrayIndexOutOfBoundException: 3
							ForFin.append((vettSoluzione[i]==1) ? "" : vettSoluzione[i]);
							ForFin.append(CompEst.get(i));
						}
					}

					FormulaOutput=ForFin.toString();
					/*
					//stampa i risultati
					System.out.println();
					System.out.println("La reazione bilanciata e': ");
					System.out.println(FormulaOutput);
					System.out.println();
					*/
					return FormulaOutput;

				}
			} catch (Exception e) {
				return "Errore: "+e.getMessage();
			}

	}


	/**
	 * Analizza la formula e restituisce una matrice di double che rappresenta la matrice incompleta del
	 * sistema da risolvere per bilanciare una reazione.
	 * 
	 * @param formin La formula di input, una reazione chimica non bilanciata (per es "H2+O2=H2O")
	 * @param compout Nella variabile indicata dal paramtero Compout verranno memorizzati tutti i composti della formula e la posizione (npiu) del simbolo di reazione ("=")
	 * @return una matrice di double che rappresenta il sistema per il bilanciamento
	 */
	public static double[][] analizzaFormula(String formin,List<String> compout) {
		
		//a ben pensarci di pattern e di matcher forse ne bastava una sola coppia, ma così è più chiaro
		String form1 = formin.replace(" ", "");;
		//ho rimosso gli spazi dalla formula con replace
		Pattern pnomenum = Pattern.compile("([A-Z][a-z]?\\-?[1-9]?[0-9]?)"); //espressione regolare
		Matcher mnomenum = pnomenum.matcher(form1);
		Pattern pelnome = Pattern.compile("([A-Z][a-z]?)");
		Matcher melnome = pelnome.matcher(form1);
		Pattern ppu = Pattern.compile("[\\+=]");
		Matcher mpu = ppu.matcher(form1);
		Pattern pnum = Pattern.compile("((\\-)?[1-9]?[0-9]?)");
		Matcher mnum=pnum.matcher("STRINGA INUTILE");//tanto per sicurezza lo inizializziamo
		Pattern ptrova=Pattern.compile("STRINGA INUTILE");//idem
		Matcher mtrova=ptrova.matcher("STRINGA INUTILE");//idem

		Pattern ppar=Pattern.compile("(\\(.*?\\)(\\d\\d?))");//qualsiasi cosa tra parentesi seguito da una o due cifre
		Matcher mpar=ptrova.matcher("STRINGA INUTILE");

		String[]ArrComp;
		Periodica per1 = new Periodica();
		// es di elementi1= [H2,O,K3,Cr,H3,O4,K,Cr2], es di elementi2 [H,O,K,Cr], o [H2 O2 H2 O] e [H O]
		List<String> elementi1 = new ArrayList<String>();
		List<String> elementi2 = new ArrayList<String>();
		//Usiamo l'interfaccia collection per 
		Collection<String> NomiElementi = new LinkedHashSet<String>();
		List<String> Composti = new ArrayList<String>();
		Set<String> tel2 = new LinkedHashSet<String>();

		//riempiamo elementi1 e elementi2
		while (mnomenum.find()) {
			elementi1.add(mnomenum.group());

		}
		while (melnome.find()) {
			elementi2.add(melnome.group());

		}
		//togliamo gli elementi presenti 2 volte
		tel2.addAll(elementi2);
		elementi2.clear();
		elementi2.addAll(tel2);

		//controlliamo che gli elementi esistano
		if (!(per1.elemarr.containsAll(elementi2))) {
			System.err.println("Errore: Inseriti elementi non riconosciuti nella formula: ");
			System.err.println(elementi2);
			System.err.println(per1.elemarr.removeAll(elementi2));
			System.exit(0);
		}
		System.out.println(elementi1);
		System.out.println(elementi2);
		//cerca i nomi degli elementi
		for (String str : elementi2) {
			NomiElementi.add(per1.nomi.get(per1.elemarr.indexOf(str)));
		}
		System.out.println(NomiElementi);
		//dividi la formula nei composti
		ArrComp=ppu.split(form1);
		for (String str : ArrComp) {
			Composti.add(str);
		}
		System.out.println(Composti);

		//trova il numero di composti prima dell'"=" (cioè il numero di "+" più 1)
		Integer npiu=1;
		mpu.reset();
		while (mpu.find()) {
			if (mpu.group().matches("=")){ 
				break;
			}
			npiu=npiu+1;
		}

		//Salva i composti e npiu in Compout
		compout.addAll(Composti);
		compout.add(npiu.toString());

		//crea MatSistema
		double[][] MatSistema = new double[elementi2.size()+1][Composti.size()];
		MatSistema=Sistemi.riempi(MatSistema,-1);//-1 per vedere se restano celle non riscritte, dopo

		melnome.reset();
		mnomenum.reset();
		
		/*
		 * PROBLEMA: per es in "H2CrO" se cerco Cr non lo trova (ma se lo cerco in "Cr" si); RISOLTO: Non usare 
		 * il fottuto Matcher.lookingAt ma Matcher.find(0)
		 */
		
		/* Per ogni composto, cerca gli elementi, se li trova metti nella posizione corrispondente
		 * della matrice il numero di atomi di quel tipo in quel composto.
		 * 
		 */
		for (int i=0;i<elementi2.size();i++) {
			for (int j=0;j<Composti.size();j++) {
				String RegStr="(("+elementi2.get(i)+")(\\-)?[1-9]?[0-9]?)";//Forse le parentesi sull'elemento non servono
				ptrova=Pattern.compile(RegStr);
				mtrova=ptrova.matcher(Composti.get(j));
				pnum=Pattern.compile("(\\-?[1-9][0-9]?)");
				System.out.println(" "+i+" "+elementi2.get(i)+" "+j+" "+Composti.get(j)+" "+RegStr+" "+mtrova.find(0));
				if (!(mtrova.find(0))) {
					// se l'elemento non c'è nel composto, metti 0 nella matrice
					MatSistema[i][j]=0;
				}
				else {
					mnum.reset();
					mnum=pnum.matcher(mtrova.group());
					System.out.println("trovato: "+mtrova.group());
					if (!(mnum.find(0))) {
						// se l'elemento c'è ma senza numero dopo, metti 1 nella matrice
						MatSistema[i][j]=1;
					}
					else {
						// se c'è e ha anche il numero di atomi, metti il numero nella matrice
						MatSistema[i][j]=Float.parseFloat(mnum.group());
						System.out.println("Numero: "+mnum.group());
					}
				}
				
				//Parentesi
				/*Controlla se nel composto ci sono parentesi, quindi cerca dentro di esse l'elemento:
				 * se c'è moltiplica la relativa posizione nella matrice per il numero dopo le parentesi
				 */
				mpar.reset();
				mpar=ppar.matcher(Composti.get(j));
				//Finchè ci sono parentesi (anche annidate)... (TEST)
				while (mpar.find()){
					//il numero dopo le parentesi (vedi pg 412 di Java Tutorial)
					int multnum=Integer.parseInt(mpar.group(2));
					ptrova=Pattern.compile(elementi2.get(i));
					//cerca dentro le parentesi gli elementi
					mtrova=ptrova.matcher(mpar.group());
					if (mtrova.find(0)) {
						MatSistema[i][j]=MatSistema[i][j]*multnum;
					}
					
				}
			}
		}


		//metti in negativo tutti i numeri dopo l'uguale (es H2+O2=H2O diventa (per così dire) H2+O2-H2O=0)
		for (int i=0;i<MatSistema.length;i++) {
			for (int j=npiu;j<MatSistema[1].length;j++) {
				MatSistema[i][j]=-MatSistema[i][j];
			}
		}

		// Aggiungi alla fine una riga con a=1: 1 0 0 0 0... (=1 poi nel VettTerNoti)
		for (int j=0;j<MatSistema[1].length;j++) {
			//If abbreviato => (cond)?v1:v2
			MatSistema[MatSistema.length-1][j]=(j==0) ? 1 : 0;
		}


		System.out.println();
		Sistemi.vediMat(MatSistema);
		return MatSistema;

	}

}

