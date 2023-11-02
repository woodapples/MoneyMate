package dda;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;


public class Geld {
    private final UUID id;
    private final Instant erstellungsdatum;
    private double betrag;
    private String kommentar;
    private int bezahlmethode; // 0=Bar, 1=Karte, 2=Überweisung, 3=Lastschrift, 4=PayPal
    private int kategorieAusgabe; // 0=Fixkosten, 1=Lebensmittel, 2=Leben&Freizeit, 4=Anschaffungen, 5=Sparen, 6=Versicherungen
    private ZoneId zoneId = ZoneId.of("Europe/Berlin");


    // Konstruktoren
    public Geld() {
        this(0.0, "Leerer Eintrag", 0, 0);
    }
    public Geld(double betrag, String kommentar, int bezahlmethode, int kategorieAusgabe) {
        this.id = UUID.randomUUID();
        this.erstellungsdatum = Instant.now();
        this.betrag = betrag;
        this.kommentar = kommentar;
        this.bezahlmethode = bezahlmethode;
        this.kategorieAusgabe = kategorieAusgabe;
    }


    // Getter Methoden
    public UUID getID() {
        return id;
    }
    public double getBetrag() {
        return betrag;
    }
    public String getKommentar() {
        return kommentar;
    }
    // 0=Bar, 1=Karte, 2=Überweisung, 3=Lastschrift, 4=PayPal
    public String getBezahlmethode() {
        if (bezahlmethode == 0) {
            return "Bar";
        } else if (bezahlmethode == 1) {
            return "Karte";
        } else if (bezahlmethode == 2) {
            return "Überweisung";
        } else if (bezahlmethode == 3) {
            return "Lastschrift";
        } else if (bezahlmethode == 4) {
            return "PayPal";
        } else {
            return "Falscher Typ";
        }
    }
    // 0=Fixkosten, 1=Lebensmittel, 2=Leben&Freizeit, 3=Anschaffungen, 4=Sparen, 5=Versicherungen
    public String getKategorieAusgabe() {
        if (kategorieAusgabe == 0) {
            return "Fixkosten";
        } else if (kategorieAusgabe == 1) {
            return "Lebensmittel";
        } else if (kategorieAusgabe == 2) {
            return "Leben&Freizeit";
        } else if (kategorieAusgabe == 3) {
            return "Anschaffung";
        } else if (kategorieAusgabe == 4) {
            return "Sparen";
        } else if (kategorieAusgabe == 5) {
            return "Versicherungen";
        } else {
            return "Falsche Eingabe";
        }
    }
    public Instant getErstellungsdatum() {
        return erstellungsdatum;
    }
    public ZoneId getZoneId() {
        return zoneId;
    }

    // Setter Methoden
    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }
    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
    public void setBezahlmethode(int bezahlmethode) {
        this.bezahlmethode = bezahlmethode;
    }
    public void setKategorieAusgabe(int kategorieAusgabe) {
        this.kategorieAusgabe = kategorieAusgabe;
    }
    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }


    @Override
    public String toString() {
        return "ID: " + getID() + "\nBetrag: " + getBetrag() + "\nKommentar: " + getKommentar() + "\nBezahlmethode: " +
                getBezahlmethode() + "\nKategorie: " + getKategorieAusgabe() + "\nErstellungsdatum: " + getErstellungsdatum() + "\n";
    }


}
