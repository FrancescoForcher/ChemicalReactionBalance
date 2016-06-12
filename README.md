# Chimica

A program to balance a chemical reaction (in italian). It uses regular expressions to transform the equation to a linear system resolved with Gauss-Jordan.

# Instructions
* Execute the .jar file, for example with: `java -jar ./Chimica.jar`
* Write in the upper text field a reaction, like `H2+O2=H2O` or `Mn(NO3)2+PbO2+HNO3=Pb(NO3)2+HMnO4+H2O`
* Click `Bilancia reazione` and see the result in the lower text field.
* Ionic reaction are written with a fake element Cc which is the electric charge, and can have a negative stoichiometric index if it is a negative ion.
    Examples:
    * Cr2O7-- + NH3 + H+ = Cr+++ + N2 + H2O -> `Cr2O7Cc-2 + NH3 + HCc = CrCc3 + N2 + H2O`
    * MnO3 + Fe++ + H+ = Mn++ + Fe+++ + H2O -> `MnO3+FeCc2+HCc=MnCc2+FeCc3+H2O`

# Original notes

>Nella cartella "source" c'è il codice sorgente.
>
>Per avviare il programma fare doppio click su "Chimica.jar"
>
>
>Ecco le scritte sul lato:
>
>Ecco qua la prima beta release del programma!
>versione: 0.9
>
>Le formule non ioniche vanno scritte ad es. cosi': H2+O2=H2O (si possono mettere tranquillamente degli spazi, qua li usero' se serve chiarezza).
>Ecco qualche esempio: 
>HNO3+H2S=NO+S+H2O, Mn(NO3)2+PbO2+HNO3=Pb(NO3)2+HMnO4+H2O,
>Bi(NO3)3 + KOH = H3BiO3 + KNO3.
>
>Nelle formule ioniche, inserire nel composto l'\"elemento\" Cc, che rappresenta una carica elettrica, seguito da un \"indice stechiometrico\" (che puo' essere, a differenza del solito, anche negativo) il cui segno rappresenta il fatto che la carica sia positiva o negativa e il cui numero rappresenta il numero di carica del composto. Per esempio, prendiamo l'Esercizio Guidato 3 a pag 287 del libro di chimica: il composto si scrivera' cosi: Cr2O7Cc-2 + NH3 + HCc = CrCc3 + N2 + H2O.
>
>Esempi: ClO3Cc-1 = ClO4Cc-1 + ClCc-1, MnO3+FeCc2+HCc=MnCc2+FeCc3+H2O
>
>Non fate caso al resto dell'output, serve a me per il debug. In teoria basta vedere solo l'ultima riga. Per uscire digitare "esci" (senza virgolette).
