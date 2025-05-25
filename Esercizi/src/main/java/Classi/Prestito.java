package Classi;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Prestito {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE,generator = "sequence1")
    @SequenceGenerator(name = "sequence1",sequenceName = "sequence1", initialValue = 1,allocationSize = 1)
    private int id;
    @ManyToOne(optional = false)
    private Utente utente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "catalogo_id")
    private Catalogo elementoPrestato;
    @Column(nullable = false)
    private LocalDate dataInizioPrestito;
    private LocalDate dataFinePrestito;
    private LocalDate dataRestituzioneEffettiva;

    public Prestito(){
    }

    public Prestito(Utente utente, Catalogo elementoPrestato, LocalDate dataInizioPrestito, LocalDate dataRestituzioneEffettiva) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataFinePrestito = dataInizioPrestito.plusDays(30);;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Catalogo getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Catalogo elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataFinePrestito() {
        return dataFinePrestito;
    }

    public void setDataFinePrestito(LocalDate dataFinePrestito) {
        this.dataFinePrestito = dataFinePrestito;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "utente=" + utente +
                ", elementoPrestato= {" + elementoPrestato +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataFinePrestito=" + dataFinePrestito +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }
}
