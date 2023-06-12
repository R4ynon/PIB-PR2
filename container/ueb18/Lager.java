/**
 * Klasse zum Lagern und Verwalten von Artikel Objekten.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 12.01.2023 / 20:00
 */
package ueb18;

import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Lager{
    //Konstanten
    private static final int MAX_LAGER = 10;
    
    //Attribute
    private Artikel[] lagerFeld;
    private final int lagerGroesse;
    private int lagerBestand;
    // bound to changes
    private Artikel [] filteredLager;
    private Artikel [] sortedLager;

    
    
    /**
     * Konstruktor fuer Lager, der Array mit uebergebener Groesse initialisiert
     * und <code>lagerGroesse</code> sowie <code>lagerBestand</code> initialisiert.
     * 
     * @param lagerGroesse Der Wert fuer die laenge des Arrays und fue <code>lagerGroesse</code>
     */
    public Lager(int lagerGroesse){
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_LAGER_LAGERGROESSE_MUSS_GROESSER,
                                        lagerGroesse);
        
        this.lagerFeld = new Artikel[lagerGroesse];
        this.lagerGroesse = lagerGroesse;
        this.lagerBestand = 0;
    }
    
    /**
     * Standardkonstruktor, der den Ersten mit dem Uebergabeparameter 10 aufruft.
     */
    public Lager(){
        this(MAX_LAGER);
    }
    
    /**
     * Speichert die Referenz eines Artikels in das Lager Array ein.
     * 
     * @param artikel Der Artikel der in das Array gespeichert wird.
     * 
     * @throws IllegalArgumentException Wenn kein Objekt uebergeben wurde.
     * @throws IllegalArgumentException Wenn Array bereits voll ist.
     * @throws IllegalArgumentException Wenn Artikelnummer bereits vergeben ist.
     */
    public void legeAnArtikel(Artikel artikel){
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_LAGER_KEIN_OBJEKT,
                                        artikel);
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_LAGER_LAGER_VOLL,
                                        lagerBestand, lagerGroesse);
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_LAGER_ARTIKEL_EXISTIERT,
                                        istArtikelGelagert(artikel));
        
        lagerFeld[lagerBestand] = artikel;
        lagerBestand++;
        
    }
    
    /**
     * Loescht die Referenz eines Artikels aus dem Array.
     * 
     * @param artikelNr Die Artikelnummer des zu loeschenden Artikels.
     * 
     * @throws IllegalArgumentException Wenn zu Artikelnummer gehoerender Artikel
     * in Array gefunden wurde.
     */
    public void entferneArtikel(int artikelNr){
        int index = getIndexArtikel(artikelNr);
        
        lagerFeld[index] = null;
        lagerFeld[index] = lagerFeld[lagerBestand - 1];
        lagerFeld[lagerBestand - 1] = null;
        lagerBestand--;
    }
    
    /**
     * Ruft die bucheZugang Methode auf einen Artikel aus dem Lager auf.
     * 
     * @param artikelNr Artikelnummer des Artikels auf den gebucht werden soll.
     * @param zugang Menge die dazugebucht werden soll.
     */
    public void bucheZugang(int artikelNr, int zugang){
        int index = getIndexArtikel(artikelNr);
        
        getArtikel(index).bucheZugang(zugang);
    }
    
    /**
     * Ruft die bucheAbgang Methode auf einen Artikel aus dem Lager auf.
     * 
     * @param artikelNr Artikelnummer des Artikels auf den gebucht werden soll.
     * @param abgang Menge die abgebucht werden soll.
     */
    public void bucheAbgang(int artikelNr, int abgang){
        int index = getIndexArtikel(artikelNr);
        
        getArtikel(index).bucheAbgang(abgang);
    }
    
    /**
     * Aendert den Preis eines Artikels aus dem Lager um einen Prozentsatz.
     * 
     * @param artikelNr Artikelnummer des Artikels, bei dem der Preis geandert wird.
     * @param prozent Der Prozentsatz um den der Preis geeandert wird.
     */
    public void aenderePreisEinesArtikels(int artikelNr, double prozent){
        int index = getIndexArtikel(artikelNr);
        Artikel tmp = getArtikel(index);
        
        tmp.aenderePreis(prozent);
    }
    
    /**
     * Aendert den Preis aller Artikel aus dem Lager um einen Prozentsatz.
     * 
     * @param prozent Der Prozentsatz um den der Preis geeandert wird.
     */
    public void aenderePreisAllerArtikel(double prozent){
        for(Artikel tmp: lagerFeld){
            if(tmp != null){
                tmp.aenderePreis(prozent);
            }
        }
    }
    
    /**
     * Gibt den Bestand des Lager in einem formatiertem String zurueck.
     * Enthaelt Zeilenweise:
     * artNr - getBeschreibung - preis - bestand - preis * bestand
     * 
     * @return formatierte Ausgabe von Bestand
     */
    public String ausgebenBestandsListe(){
        double gesamtPreis = 0.0;
        double artikelPreis;
        int artikelNr;
        int artikelBestand;
        StringBuilder artikelBeschreibung = new StringBuilder("");
        StringBuilder ausgabe = new StringBuilder();
        
        ausgabe.append("-----------------------------------------------------------------------------\n");
        ausgabe.append(String.format("|%-5s %-40s %8s %-7s %9s  |\n", "ArtNr", "Beschreibung", "Preis",
                                    "Bestand", "Gesamt"));
        ausgabe = ausgabe.append("|---------------------------------------------------------------------------|\n");
        for(Artikel artikel: lagerFeld){
            if(artikel != null){
                artikelPreis = artikel.getPreis();
                artikelNr = artikel.getArtikelNr();
                artikelBestand = artikel.getBestand();
                artikelBeschreibung.delete(0, artikelBeschreibung.length());
                artikelBeschreibung.append(artikel.getBeschreibung());
                
                ausgabe = ausgabe.append(String.format("|%-5d %-40.40s %8.2f %-7.7s %9.2f  |\n",
                                                        artikelNr,
                                                        artikelBeschreibung.toString(),
                                                        artikelPreis,
                                                        String.valueOf(artikelBestand),
                                                        artikelPreis * artikelBestand));
                                                        
                gesamtPreis += artikelPreis * artikelBestand;
            }
        }
        ausgabe = ausgabe.append("|---------------------------------------------------------------------------|\n");
        ausgabe = ausgabe.append(String.format("|%-10s%62.2f  |\n","Gesamtwert:",gesamtPreis));
        ausgabe = ausgabe.append("-----------------------------------------------------------------------------\n");
        
        return ausgabe.toString();
    }
    
    /**
     * Gibt einen Artikel an einem uebergebenen Index aus dem Feld zurueck.
     * 
     * @param index Stelle im Array die zurueckgegeben werden soll.
     * 
     * @return Gewuenschter Artikel.
     * 
     * @throws IllegalArgumentException Wenn Index ausserhalb des Bereichs vom Array ist.
     */
    public Artikel getArtikel(int index){
        if(index < 0 || index > lagerGroesse - 1){
            throw new IllegalArgumentException(ErrorMessages.ERROR_LAGER_INDEX_FALSCH + String.valueOf(lagerGroesse - 1));
        }
        
        return lagerFeld[index];
    }
        
    public int getArtikelAnzahl(){
        return lagerBestand;
    }
    
    public int getLagerGroesse(){
        return lagerGroesse;
    }
    
    @Override
    public String toString(){
        String lager = ";\nLagerFeld:\n";
        for(Artikel tmp: lagerFeld){
            if(tmp != null){
                lager += "Klasse: " + tmp.getClass().getSimpleName() + " : " + tmp + "\n";
            }
        }
        
        return "Lagergroesse: " + String.valueOf(lagerGroesse) + "; Lagerbestand: " 
                + String.valueOf(lagerBestand) + lager;
    }
    
    /**
     * Prueft ob Artikel im Lager ist.
     * 
     * @return true wenn Artikel gefunden, false wenn Artikel nicht gefunden.
     */
    private boolean istArtikelGelagert(Artikel artikel){
        for(int i = 0; i < lagerBestand; i++){
            if(lagerFeld[i].getArtikelNr() == artikel.getArtikelNr()){
                return true;
            }
            if(lagerFeld[i].equals(artikel)){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Sucht an welchem Index ein Artikel steht.
     * 
     * @param artikelNr Artikelnummer des Artikels der gesucht werden soll.
     * 
     * @return Index des Artikels, wenn er gefunden wurde.
     * 
     * @throws IllegalArgumentException Wenn Artikel nicht gefunden wurde.
     * Es wird kein Index zurueckgegeben.
     */
    public int getIndexArtikel(int artikelNr){
        int index;
        for(int i = 0; i < lagerBestand; i++){
            if(lagerFeld[i].getArtikelNr() == artikelNr){
                index = i;
                return index;
            }
        }
        
        throw new IllegalArgumentException(ErrorMessages.ERROR_LAGER_ARTIKEL_NICHT_GEFUNDEN.getMessage());
    }

    /**
     *
     * @param biPredicate
     * @return
     */
    public Artikel[] getSorted (BiPredicate<Artikel, Artikel> biPredicate){
        this.sortedLager = new Artikel[lagerBestand];
        sortedLager = lagerFeld;
        for(int j = 0 ; j < lagerBestand-1; j++){
            for (int i = 0 ; i < lagerBestand-1-i ; i++) {
                Artikel einArtikel = lagerFeld[i];
                Artikel andereArtikel = lagerFeld[i + 1];
                if (biPredicate.test(einArtikel, andereArtikel)) {
                    swap(i, i + 1, sortedLager);
                }
            }
        }

      return sortedLager;
    }

    /**
     * Methode, die Arraystellenvertauscht.
     * @param o Arraystellenwert
     * @param p Arraystellenwert
     */
    public Artikel [] swap(int o, int p, Artikel [] array){
        Artikel help = array[o];
        array[o] = array[p];
        array[p] = help;
        return array;
    }
    public void applyToArticles(Consumer consumer){
        for (Artikel einArtiekl:lagerFeld) {
            consumer.accept(einArtiekl);
        }
    }
    public Artikel[] filter(Predicate<Artikel> predicate){
        int i = 0;
        this.filteredLager = new Artikel[lagerBestand];
        for (Artikel oneArtikel:lagerFeld
             ) {
            if(predicate.test(oneArtikel)){
                filteredLager[i] = oneArtikel;
                i++;
            }
        }
        return filteredLager;
    }

    /**
     * Methode applyToSomeArticles, die eine Operation auf die Artikel
     * anwendet, welche ein bestimmtes Kriterium erfüllen
     * @param predicate ein übergebenes Suchkriterium
     * @param consumer
     */
    public void applyToSomeArticles(Predicate <Artikel> predicate, Consumer <Artikel> consumer){
        for (int i = 0 ; i < lagerBestand ; i++){
            Artikel einArtikel = lagerFeld[i];
            if(predicate.test(einArtikel)){
                consumer.accept(einArtikel);
            }
        }

    }
    public Artikel[] getArticels(Predicate <Artikel> predicateEins, BiPredicate <Artikel, Artikel> predicateZwei){
        sortedLager = getSorted(predicateZwei);
        Artikel [] xLager = new Artikel[sortedLager.length];
        for (int i = 0 ; i < sortedLager.length ; i++){
            Artikel einArtikel = sortedLager[i];
            if(predicateEins.test(einArtikel)){
                xLager[i]=einArtikel;
            }
        }
        return xLager;
    }

    /**
     * Methode filterAll, die eine beliebige Menge an Filterkriterien als
     * Parameter entgegennimmt und die Artikel des Lagers zurück gibt, die alle Filterkriterien erfüllen.
     * @param predicates Filterkriterium
     * @return sortedLager nach Filterkriterium sortiertes Lager.
     */
    public Artikel[] filterAll(Predicate... predicates){
        this.sortedLager = new Artikel[lagerBestand];
        int help = 0;
        boolean hilfe = true;
        for(int i = 0; i < lagerBestand; i++){
            Artikel einArtikel = lagerFeld[i];
            for(int j = 0; j < predicates.length; j++){
                if(predicates[j].test(einArtikel) == false){
                    hilfe = false;

                }
            }
            if(hilfe == true){
                this.sortedLager[help] = lagerFeld[i];
                help++;
            }
        }
        return sortedLager;
    }
}