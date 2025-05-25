package Classi;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Utente {
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false)
    private LocalDate dataNascita;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence")
    @SequenceGenerator(name="sequence",sequenceName = "sequence", initialValue =1,allocationSize = 1)
    private int numeroTessera;

    public Utente(){
    }

    public Utente(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public int getId() {
        return numeroTessera;
    }

    public void setId(int id) {
        this.numeroTessera = numeroTessera;
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", numeroTessera=" + numeroTessera +
                '}';
    }
}
