import java.util.*;

/**
 * Deposito di dati sugli elementi
 * 
 * @author Francesco Forcher
 * @version 1.0, 15\04\2012
 *
 */
public class Periodica {

		//"Cc" rappresenta una carica (positiva o negativa dipende se l'indice stechiometrico è positivo o negativo)
		private static final String[] elemarr2 = {"Cc","H","He","Li","Be","B","C","N","O","F","Ne","Na","Mg","Al","Si","P","S","Cl","Ar","K","Ca","Sc","Ti","V","Cr","Mn","Fe","Co","Ni","Cu","Zn","Ga","Ge","As","Se","Br","Kr","Rb","Sr","Y","Zr","Nb","Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe","Cs","Ba","La","Ce","Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu","Hf","Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn","Fr","Ra","Ac","Th","Pa","U","Np","Pu","Am","Cm","Bk","Cf","Es","Fm","Md","No","Lr","Rf","Db","Sg","Bh","Hs","Mt","Ds","Rg","Uub","Uut","Uuq","Uup","Uuh","Uus","Uuo"}; 
		private static final String[] nomiarr = {"Carica","Hydrogen","Helium","Lithium","Beryllium","Boron","Carbon","Nitrogen","Oxygen","Fluorine","Neon","Sodium","Magnesium","Aluminium","Silicon","Phosphorus","Sulfur","Chlorine","Argon","Potassium","Calcium","Scandium","Titanium","Vanadium","Chromium","Manganese","Iron","Cobalt","Nickel","Copper","Zinc","Gallium","Germanium","Arsenic","Selenium","Bromine","Krypton","Rubidium","Strontium","Yttrium","Zirconium","Niobium","Molybdenum","Technetium","Ruthenium","Rhodium","Palladium","Silver","Cadmium","Indium","Tin","Antimony","Tellurium","Iodine","Xenon","Caesium","Barium","Lanthanum","Cerium","Praseodymium","Neodymium","Promethium","Samarium","Europium","Gadolinium","Terbium","Dysprosium","Holmium","Erbium","Thulium","Ytterbium","Lutetium","Hafnium","Tantalum","Tungsten","Rhenium","Osmium","Iridium","Platinum","Gold","Mercury","Thallium","Lead","Bismuth","Polonium","Astatine","Radon","Francium","Radium","Actinium","Thorium","Protactinium","Uranium","Neptunium","Plutonium","Americium","Curium","Berkelium","Californium","Einsteinium","Fermium","Mendelevium","Nobelium","Lawrencium","Rutherfordium","Dubnium","Seaborgium","Bohrium","Hassium","Meitnerium","Darmstadtium","Roentgenium","Ununbium","Ununtrium","Ununquadium","Ununpentium","Ununhexium","Ununseptium","Ununoctium"};
		private static final double[] massearr = {-1,1.00794,4.002602,6.941,9.012182,10.811,12.0107,14.00674,15.9994,18.9984032,20.1797,22.98976928,24.305,26.9815386,28.0855,30.973762,32.065,35.4527,39.948,39.0983,40.078,44.955912,47.867,50.9415,51.9961,54.938045,55.845,58.933195,58.6934,63.546,65.409,69.723,72.64,74.9216,78.96,79.904,83.798,85.4678,87.62,88.90585,91.224,92.90638,95.96,98,101.07,102.9055,106.42,107.8682,112.411,114.818,118.71,121.76,127.6,126.90447,131.293,132.9054519,137.327,138.90547,140.116,140.90765,144.242,145,150.36,151.964,157.25,158.92535,162.5,164.93032,167.259,168.93421,173.054,174.9668,178.49,180.94788,183.84,186.207,190.23,192.217,195.084,196.966569,200.59,204.3833,207.2,208.9804,209,210,222,223,226,227,232.03806,231.03588,238.02891,237,244,243,247,247,251,252,257,258,259,262,261,262,266,264,277,268,271,272,285,284,289,288,293,0,294};
		private static final double[] densitaarr = {-1,0.0899,0.1785,535,1848,2460,2260,1.251,1.429,1.696,0.9,968,1738,2700,2330,1823,1960,3.214,1.784,856,1550,2985,4507,6110,7140,7470,7874,8900,8908,8920,7140,5904,5323,5727,4819,3120,3.75,1532,2630,4472,6511,8570,10280,11500,12370,12450,12023,10490,8650,7310,7310,6697,6240,4940,5.9,1879,3510,6146,6689,6640,7010,7264,7353,5244,7901,8219,8551,8795,9066,9321,6570,9841,13310,16650,19250,21020,22610,22650,21090,19300,13534,11850,11340,9780,9196,0,9.73,0,5000,10070,11724,15370,19050,20450,19816,0,13510,14780,15100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		private static final double[] affinitaelettrarr = {-1,72.8,0,59.6,0,26.7,153.9,7,141,328,0,52.8,0,42.5,133.6,72,200,349,0,48.4,2.37,18.1,7.6,50.6,64.3,0,15.7,63.7,112,118.4,0,28.9,119,78,195,324.6,0,46.9,5.03,29.6,41.1,86.1,71.9,53,101.3,109.7,53.7,125.6,0,28.9,107.3,103.2,190.2,295.2,0,45.5,13.95,48,50,50,50,50,50,50,50,50,50,50,50,50,50,50,0,31,78.6,14.5,106.1,151,205.3,222.8,0,19.2,35.1,91.2,183.3,270.1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		private static final double[] elettronegatarr = {-1,2.2,0,0.98,1.57,2.04,2.55,3.04,3.44,3.98,0,0.93,1.31,1.61,1.9,2.19,2.58,3.16,0,0.82,1.,1.36,1.54,1.63,1.66,1.55,1.83,1.88,1.91,1.9,1.65,1.81,2.01,2.18,2.55,2.96,3.,0.82,0.95,1.22,1.33,1.6,2.16,1.9,2.2,2.28,2.2,1.93,1.69,1.78,1.96,2.05,2.1,2.66,2.6,0.79,0.89,1.1,1.12,1.13,1.14,0,1.17,0,1.2,0,1.22,1.23,1.24,1.25,0,1.27,1.3,1.5,2.36,1.9,2.2,2.2,2.28,2.54,2.,1.62,2.33,2.02,2.,2.2,0,0.7,0.9,1.1,1.3,1.5,1.38,1.36,1.28,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		private static final String[] tipi = {"Alkali","Alkaline","Transition","Other metals","Metalloid","Nonmetal","Halogen","Noble","Lanthanoid","Actinoid","undef"};
		//i numeri di "tipoarr" corrispondono alla posizione in "tipi"
		private static final double[] tipoarr = {11,6,8,1,2,5,6,6,6,7,8,1,2,4,5,6,6,7,8,1,2,3,3,3,3,3,3,3,3,3,3,4,5,5,6,7,8,1,2,3,3,3,3,3,3,3,3,3,3,4,4,5,5,7,8,1,2,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,3,3,3,3,3,3,3,3,3,4,4,4,5,7,8,1,2,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,3,3,3,3,3,3,3,3,3,4,4,4,4,11,8};
		
		final List<String> elemarr = new ArrayList<String>();
		final List<String> nomi = new ArrayList<String>();
		final List<Number> masse = new ArrayList<Number>();
		final List <Number> densita = new ArrayList<Number>();
		
		/* Nota: nonostante sia formata da un elenco di costanti non è una classe statica,
		 * perchè per far creare le Collection ho dovuto metterci un costruttore
		 * 
		 * Ma a pensarci forse è una stronzata... forse basta usare new in modo adeguato
		 */
		public Periodica() {
		//Crea i Set dagli array
		for (String el : elemarr2)
			elemarr.add(el);
		for (String no : nomiarr)
			nomi.add(no);
		for (double ma : massearr)
			masse.add(ma);
		for (double de : densitaarr)
			densita.add(de);
		}
}