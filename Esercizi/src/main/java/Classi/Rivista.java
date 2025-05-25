package Classi;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table
public class Rivista extends Catalogo{
    @Enumerated(value = EnumType.STRING)
    private Periodicità periodicita;

    public Rivista(){
    }

    public Rivista(Long ISBN, String titolo, int data, Integer numeroDiPagine, Periodicità periodicita) {
        super(ISBN, titolo, data, numeroDiPagine);
        this.periodicita=periodicita;
    }

    public Periodicità getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicità periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return
                "Rivista: " +
                        super.toString() +
                        ", periodicita=" + periodicita;
    }
}
