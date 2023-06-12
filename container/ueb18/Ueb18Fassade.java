package ueb18;

import java.util.concurrent.Callable;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * <p>Diese Klasse ist eine Fassade, hinter der Sie Ihre Loesung verstecken. Der Test ruft nur die hier definierten
 * Schnittstellenmethoden auf. Loeschen Sie bitte keine Methoden. Wenn Sie eine Methode nicht implementieren
 * moechten, lassen Sie bitte die leere Implementierung stehen. Innerhalb der Methoden und in allen anderen Klassen,
 * die Sie noch benoetigen, haben Sie alle Freiheiten.</p>
 *
 * <p>Wenn Sie Ihre Loesung mit JUnit testen moechten, testen Sie diese Schnittstellenmethoden.</p>
 * @author christopher
 *
 */
public class Ueb18Fassade {
    /**
     * Loest die Aufgabe (c) i.
     * <br />
     * Sortierung nach den folgenden Kriterien:
     * <ol>
     * 	<li>Unterkategorie (alphabetisch)</li>
     * 	<li>Bestand</li>
     * 	<li>Preis</li>
     * </ol>
     * @param lager Das Lager mit der unsortierten Artikelliste.
     * @return Die sortierte Artikelliste.
     */
    public Artikel[] aufgabe_c_i(Lager lager) {
        BiPredicate<Artikel, Artikel> preislich = (x,y) -> {
            double help1 = x.getPreis();
            double help2 = y.getPreis();
            if(help1 < help2){
                return true;
            }
            return false;
        };

        BiPredicate<Artikel, Artikel> bestaendig = (x,y) -> {
            int help1 = x.getBestand();
            int help2 = y.getBestand();
            if(help1 < help2){
                return true;
            } else if (help1 > help2) {
                return false;
            }return preislich.test(x, y);
        };
        BiPredicate<Artikel, Artikel> aphabetic = (x,y) -> {
            String help1 = x.getArt();
            String help2 = y.getArt();
            if(help1.compareTo(help2)<0){
                return true;
            } else if (help1.compareTo(help2)>0) {
                return false;
            }return bestaendig.test(x, y);
        };

        return lager.getSorted(aphabetic);
    }

    /**
     * Loest die Aufgabe (c) ii.
     * <br />
     * Der Preis aller Artikel wird um 10% reduziert.
     * @param lager Das Lager mit den Artikeln, deren Preise geaendert werden sollen.
     */
    public void aufgabe_c_ii(Lager lager) {
        Consumer<Artikel> c = (x) -> x.aenderePreis(-10.0);
        lager.applyToArticles(c);
    }

    /**
     * Loest die Aufgabe (c) iii.
     * <br />
     * An alle Artikelbezeichnungen wird das Suffix (Sonderangebot) angehaengt.
     * @param lager Das Lager mit den Artikeln, deren Bezeichnungen geaendert werden sollen.
     */
    public void aufgabe_c_iii(Lager lager) {
        Consumer<Artikel> addSuffix = (artikel) -> {
            String s = artikel.getArt() + "Sonderangebot";
        };
        lager.applyToArticles(addSuffix);
        //test
    }

    /**
     * Loest die Aufgabe (c) iv.
     * <br />
     * Die beiden Operatoren aus den Aufgabenteilen ii und iii werden konkateniert, d.h. alle Preise werden
     * um 10 % reduziert und alle Bezeichnungen werden um das Suffix (Sonderangebot) erweitert.
     * @param lager Das Lager mit den Artikeln, deren Preise und Bezeichnungen geaendert werden sollen.
     */
    public void aufgabe_c_iv(Lager lager) {
        //muss geaendert werden
        Consumer<Artikel> c = (x) -> x.aenderePreis(-10.0);
        lager.applyToArticles(c);

        Consumer<Artikel> addSuffix = (artikel) -> {
            String s = artikel.getArt() + "Sonderangebot";
        };

        lager.applyToArticles(addSuffix);
    }

    /**
     * Loest die Aufgabe (h) i.
     * <br />
     * Der Preis aller CDs wird um 10 % erhoeht.
     * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in diesem Objekt vorgenommen.
     */
    public void aufgabe_h_i(Lager lager) {
        //lager.applyToSomeArticles(a -> a instanceof CD, a -> a.aenderePreis(10));
    }

    /**
     * Loest die Aufgabe (h) ii.
     * <br />
     * Der Preis aller Artikel, von denen der Bestand hoechstes zwei ist, wird um 5 % reduziert.
     * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in diesem Objekt vorgenommen.
     */
    public void aufgabe_h_ii(Lager lager) {
    }

    /**
     * Loest die Aufgabe (h) iii.
     * <br />
     * Der Preis der Buecher eines bestimmten Autors wird um 5 % reduziert.
     * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in diesem Objekt vorgenommen.
     * @param gesuchterAutor Der Autor, dessen Buecher guenstiger werden sollen.
     */
    public void aufgabe_h_iii(Lager lager, String gesuchterAutor) {
    }

    /**
     * Loest die Aufgabe (h) iv.
     * <br />
     * Der Preis aller CDs wird um 10 % erhoeht und der Preis aller Artikel, von denen der Bestand hoechstes zwei ist, wird um 5 % reduziert.
     * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in diesem Objekt vorgenommen.
     */
    public void aufgabe_h_iv(Lager lager) {
    }

    /**
     * Loest die Aufgabe (h) v.
     * <br />
     * @param lager Das Lager mit den Artikeln.
     * @return Eine Liste mit allen Buechern, sortiert nach den Namen der Autoren.
     */
    public Artikel[] aufgabe_h_v(Lager lager) {
        BiPredicate<Artikel, Artikel> aphabeticAuthor = (x,y) -> {
            String help1 = x.getArt();
            String help2 = y.getArt();
            if(help1.compareTo(help2)<0){
                return true;
            } else //if (help1.compareTo(help2)>0) {
                return false;
            //}
        };
        Predicate <Artikel> buchJaNein = (x) -> {
            if(x instanceof Buch){
                return true;
            }
            return false;
        };
        return lager.getArticels(buchJaNein, aphabeticAuthor);
    }

    /**
     * Loest die Aufgabe (h) vi.
     * <br />
     * @param lager Das Lager, dessen Artikel gefiltert werden sollen.
     * @param gesuchterAutor Der Autor, nach dem gefiltert werden soll.
     * @param minPreis Der kleinste Preis, den die zu filternden Buecher haben sollen.
     * @param maxPreis Der hoechste Preis, den die zu filternden Buecher haben sollen.
     * @return Alle Buecher vom Autor autor und mit einem Preis, der zwischen minPreis und maxPreis liegt.
     */
    public Artikel[] aufgabe_h_vi(Lager lager, String gesuchterAutor, double minPreis, double maxPreis) {
        Predicate<Artikel> p1 = (x) -> {
            if(x instanceof Buch){
                Artikel artikel = (Buch)x;
                if(((Buch) x).getAutor().equals(gesuchterAutor) ){
                    return true;
                }
            }
            return false;
        };

        Predicate<Artikel> p2 = (x) -> {
            if(x.getPreis() >= minPreis && x.getPreis() <= maxPreis){
                return true;
            }
            return false;
        };

        return lager.filterAll(p1, p2);
    }
}
