import java.util.*;
import java.math.*;

/**
 * Tutto ciò che concerne i sistemi (lineari, per ora) e le matrici
 * 
 * @author Francesco Forcher
 * @version 0.92, 15\04\2012
 */
public class Sistemi {
	
	//Il MathContext per impostare la precisione
	static MathContext mc1=new MathContext(14);
	
	public static MathContext getMc1() {
		return mc1;
	}

	public static void setMc1Precision(int prec) {
		Sistemi.mc1=new MathContext(prec);
	}

	/**Algoritmo di gauss
	 * 
	 * @param mat la matrice di imput
	 * @return Una matrice ridotta con il metodo di gauss
	 */
	
	
	static public double[][] gauss(double[][] mat) {
		/*
		 * Potrei fare il partial pivoting, prima o poi...
		 */
		int i,k,j;
		double[][] mat1 = mat;
		int nr = mat1.length;
		int nc = mat1[1].length;
		double[] tempr; //Riga temporanea
		double fatt;//Fattore di moltiplicazione di una riga
		int tuttelerighe = 0;//Mi serve per quando ci sn righe vuote. Implementare meglio


		/*			        ***    ***    ***    ***	           ***	  **0	 *00	100
		 * Algoritmo: Gauss *** => 0** => 0** => 0**   => Jordan   0*0 => 0*0 => 0*0 => 010
		 *			        ***    ***    0**    00*		       00*	  00*	 00*	001
		 */
		//Inizia gauss:
		Gauss1:
			for (i=0;i<=nc-1;i++){
				/*grande ciclo che per tutte le colonne (numerate da i) prima cerca di mettere una riga con l'elemento diverso da zero
				 *(il pivot) all'inizio  poi azzera tutti gli el della parte di colonna che rimane con mat1[j][k]=(mat1[j][k]-fatt*mat1[i][k]);
				 */

				/*	   0**						    ***
				 *  se *** allora scambia le righe: 0**. Se non c'è un numero diverso da zero passa alla riga dopo. se tutta
				 *     ***		 				    ***	 la colonna è di 0, passa alla colonna dopo
				 */
				
				tuttelerighe=0;
				for (j=i;j<=nr-1;j++){
					if (mat1[j][i]!=0) {
						tempr=mat1[i];
						mat1[i]=mat1[j];
						mat1[j]=tempr;
						tuttelerighe = 1;
						break;
						//PROBLEMA, mettere cond x le matrici che hanno pivot non sulla diagonale
						//FATTO!!
					}
				}
				if (tuttelerighe==0)
					continue Gauss1;

				/*zerizza tutti gli elementi della (sotto)matrice triangolare inferiore
				 *
				 */
				for(j=i+1;j<=nr-1;j++){
					fatt=mat1[j][i]/mat1[i][i];
					for (k=0;k<=nc-1;k++) {
						mat1[j][k]=(mat1[j][k]-fatt*mat1[i][k]);
					}
				}
			}

		System.out.println("Dopo Gauss:");
		Sistemi.vediMat(mat1);
		System.out.println();


		System.out.println("esco da gauss");
		return mat1;
	}

	
	public static void vediMat(double[][] Mat) {
		for (int i=0;i<Mat.length;i++) {
			for (int j=0;j<Mat[1].length;j++) {
				System.out.print(" "+Mat[i][j]);
			}
			System.out.println();
		}
	}

	public static void vediMat(int[] Vett) {
		for (int i=0;i<Vett.length;i++)
			System.out.println(" "+Vett[i]);;
	}
	
	public static void vediMat(int[][] Mat) {
		for (int i=0;i<Mat.length;i++) {
			for (int j=0;j<Mat[1].length;j++) {
				System.out.print(" "+Mat[i][j]);
			}
			System.out.println();
		}
	}

	public static void vediMat(double[] Vett) {
		for (int i=0;i<Vett.length;i++)
			System.out.println(" "+Vett[i]);;
	}
	
	
	/**
	 * Algoritmo di Jordan
	 * @param mat Matrice di double, dovrebbe della forma colonne=righe+1
	 * @return La matrice ridotta
	 */
	static public double[][] jordan(double[][] mat) {
		/*
		 *          ***	   **0	  *00	 100
		 *Jordan:   0*0 => 0*0 => 0*0 => 010
		 *          00*	   00*	  00*	 001
		 */


		//Inizia jordan
		System.out.println("Inizia Jordan: ");
		/* IMPORTANTE: Sembra che il for non funzioni decrescendo, a quanto pare: 
		 * for (i=nr-1;i==0;i--) non eseguiva mai il contenuto del ciclo
		 */
		int i,k,j;
		double[][] mat1 = mat;
		int nr = mat1.length;
		int nc = mat1[1].length;
		//double[] tempr;
		double fatt;
		//int tuttelerighe = 0;
		i=nr-1;
		while (i!=0){
			System.out.println("for1: i="+i);
			j=i-1;
			while(j!=-1){
				System.out.println("for2: j="+j);
				fatt=mat1[j][i]/mat1[i][i];
				for (k=0;k<=nc-1;k++) {
					mat1[j][k]=(mat1[j][k]-fatt*mat1[i][k]);
					//System.out.println("Op: k="+k);
					//Sistemi.vediMat(mat1);
				}
				j--;
			}
			i--;
		}
		//Rendiamo tutti gli elementi sulla diagonale 1
		for (i=0;i<nr;i++){
			fatt=1/mat1[i][i];
			for (k=0;k<=nc-1;k++) {
				mat1[i][k]=fatt*mat1[i][k];
			}
		}


		System.out.println("Esco da Jordan");
		return mat1;
	}

	/**Risolve sistemi lineari con il metodo di Gauss-Jordan
	 * 
	 * @param mat Matrice
	 * @param vTerNoti Vettore dei temini noti
	 * @return Vettore delle soluzioni
	 */
	public static double[] risolviSistLin(double[][] mat,double[] vTerNoti) {
		int i,j;
		double[][] tmat= new double[mat.length][mat[1].length+1];
		BigDecimal[][] tmatBD = new BigDecimal[mat.length][mat[1].length+1];
		
		
		//Crea la matrice completa del sistema mettendo vTerNoti nell'ultima colonna di tmat
		for (i=0;i<mat.length;i++) {
			for (j=0;j<mat[1].length;j++)
				tmat[i][j]=mat[i][j];
		}
		for (i=0;i<mat.length;i++)
			tmat[i][mat[1].length]=vTerNoti[i];


		double[][] finalmat;
		System.out.println("Matrice Completa: ");
		Sistemi.vediMat(tmat);
		
		//Arrotonda
		tmatBD=convertMatToBD(tmat);
		tmat=convertMatToDouble(tmatBD);
		
		//fai gauss, poi togli le righe di zeri (equazioni ridondanti), poi fai jordan
		tmat=gauss(tmat);
		finalmat=toglirighe(tmat);
		finalmat=jordan(finalmat);
		
		//Arrotonda
		tmatBD=convertMatToBD(finalmat);
		finalmat=convertMatToDouble(tmatBD);
		
		//scrivi le soluzioni nel vettore (le soluzioni sono l'ultima colonna di finalmat)
		double[] vettSolOut=new double[finalmat.length];
		System.out.println("Eccoci quasi...");
		Sistemi.vediMat(vettSolOut);
		Sistemi.vediMat(finalmat);
		for(i=0;i<finalmat.length;i++)
			vettSolOut[i]=finalmat[i][finalmat[1].length-1];

		return vettSolOut;


	}


	/**
	 * Riempe la Matrice Mat con il numero (double) num1
	 * 
	 * @param mat La matrice da riempire
	 * @param num1 Il numero che riempirà la matrice
	 * @return Una matrice di dimensioni uguali a Mat, riempita con num1
	 */
	public static double[][] riempi(double[][] mat,double num1){
		double[][] matReturn=new double[mat.length][mat[1].length];
		for (int i=0;i<mat.length;i++) {
			for (int j=0;j<mat[1].length;j++) {
				matReturn[i][j]=num1;
			}
		}
		return matReturn;
	}


	//Forse non serve a niente
	/**
	 * Versione ridotta di argmax (vedi wikipedia)
	 * @param arrnum Array di double
	 * @return L'int della posizione nell'array arrnum con il valore assoluto maggiore
	 */
	public static int absmax(double[] arrnum) {
		List<Double> numList= new ArrayList<Double>();
		List<Double> numListNonOrdinata= new ArrayList<Double>();
		//cambia segno ai valori negativi
		for (double num : arrnum) {
			if (Math.signum(num)==-1)
				num=-num;
		}

		for (double num : arrnum){
			numList.add(num);
			numListNonOrdinata.add(num);
		}

		//Ordina numList (nota che si usano i metodi di Collections ("s" alla fine))
		Collections.sort(numList);
		//trova la posizione in numListNonOrdinata dell'ultimo elemento di numlist
		return numListNonOrdinata.lastIndexOf(numList.get(numList.size()));
	}


	/**
	 * Togle le righe di zeri da matIn e memorizza il risultato nella variabile indicata dal parametro matOut
	 * @param matIn Matrice di input
	 * @return Una matrice senza righe vuote alla fine
	 */
	public static double[][] toglirighe(double[][] matIn) {
		int i,j;
		double[] tempr;
		int nrighefinali=matIn.length;
		int numDiZeri;


		//crea una riga di zeri
		double[] rigaVuota=new double[matIn[1].length];
		for (i=0;i<rigaVuota.length;i++)
			rigaVuota[i]=0;

		//Forse sarebbe meglio partire dal basso, ma vedi il commento sul for in jordan
		for (i=0;i<matIn.length;i++){
			tempr=matIn[i];
			numDiZeri=0;
			//se numDiZeri è uguale alle dimensioni della riga, allora è una riga nulla
			for (j=0;j<tempr.length;j++){
				if (tempr[j]==0)
					numDiZeri++;
			}
			if (numDiZeri==tempr.length){
				nrighefinali--;
				System.out.println("Tolgo una riga: ");
			}	
		}
		double[][] matOut= new double[nrighefinali][matIn[1].length];

		//Crea la matrice di output
		for (i=0;i<nrighefinali;i++)
			matOut[i]=matIn[i];

		return matOut;
	}

	/**
	 * Rovaescia la matrice matIn e memorizza il e memorizza il risultato nella variabile indicata dal parametro matOut
	 * @param matIn Matrice di input
	 * @param matOut Matrice di output
	 */
	public static void rovesciaMat(double[][] matIn,double[][] matOut){
		int i;
		int nr=matIn.length;
		double[] tempriga;
		matOut=matIn;
		for (i=0;i<nr/2;i++) {
			tempriga=matOut[i];
			matOut[i]=matOut[nr-1-i];
			matOut[nr-i-1]=tempriga;
		}
	}

	//Metodi di conversione sovraccaricati
	/**
	 * Converte una matrice di double in una matrice di BigDecimal, arrotondati come indica mc
	 * 
	 * @param mat Matrice di double
	 * @return Matrice di BigDecimal
	 */
	private static BigDecimal[][] convertMatToBD(double[][] mat){
		BigDecimal[][] outmat=new BigDecimal[mat.length][mat[1].length];
		for (int i=0;i<mat.length;i++)
			for(int j=0;j<mat[1].length;j++){
				outmat[i][j]=BigDecimal.valueOf(mat[i][j]);
				outmat[i][j]=outmat[i][j].round(mc1);
			}
		return outmat;
	}
	
	/**
	 * Converte un vettore di double in un vettore di BigDecimal, arrotondati come indica mc
	 * 
	 * @param mat Vettore di double
	 * @return Vettore di BigDecimal
	 */
	private static BigDecimal[] convertMatToBD(double[] mat){
		BigDecimal[] outmat=new BigDecimal[mat.length];
		for (int i=0;i<mat.length;i++){
				outmat[i]=BigDecimal.valueOf(mat[i]);
				outmat[i]=outmat[i].round(mc1);
			}
		return outmat;
	}
	
	/**
	 * Converte un vettore di BigDecimal in un vettore di double
	 * 
	 * @param mat Vettore di BigDecimal
	 * @return	Vettore di double
	 */
	private static double[] convertMatToDouble(BigDecimal[] mat){
		double[] outmat=new double[mat.length];
		for (int i=0;i<mat.length;i++){
				outmat[i]=mat[i].doubleValue();
			}
		return outmat;
	}
	
	
	/**
	 * Converte una matrice di BigDecimal in una matrice di double
	 * 
	 * @param mat Matrice di BigDecimal
	 * @return	matrice di double
	 */
	private static double[][] convertMatToDouble(BigDecimal[][] mat){
		double[][] outmat=new double[mat.length][mat[1].length];
		for (int i=0;i<mat.length;i++)
			for(int j=0;j<mat[1].length;j++){
				outmat[i][j]=mat[i][j].doubleValue();
			}
		return outmat;
	}
	
}

