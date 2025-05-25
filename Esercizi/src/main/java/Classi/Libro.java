package Classi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Libro extends Catalogo{
    @Column(nullable = false)
    private String autore;
    @Column(nullable = false)
    private String genere;

    public Libro(){
    }

    public Libro(Long ISBN, String titolo, int data, Integer numeroDiPagine, String autore, String genere) {
        super(ISBN, titolo, data, numeroDiPagine);
        this.autore=autore;
        this.genere=genere;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    @Override
    public String toString() {
        return "Libro: " +
                "autore: " + autore  +
                super.toString() +
                ", genere: " + genere ;
    }
}

