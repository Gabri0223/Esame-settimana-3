package Classi;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract  class Catalogo {
    @Id
    private Long ISBN;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private int data;
    @Column(nullable = false)
    private Integer numeroDiPagine;

    public Catalogo(){
    }

    public Catalogo(Long ISBN, String titolo, int data, Integer numeroDiPagine) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.data = data;
        this.numeroDiPagine = numeroDiPagine;
    }

    public Long getISBN() { return ISBN; }

    public void setISBN(Long ISBN) {
            this.ISBN = ISBN;
        }

        public String getTitolo() {
            return titolo;
        }

        public void setTitolo(String titolo) {
            this.titolo = titolo;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Integer getNumeroDiPagine() {
            return numeroDiPagine;
        }

        public void setNumeroDiPagine(Integer numeroDiPagine) {
            this.numeroDiPagine = numeroDiPagine;
        }

        @Override
        public String toString() {
            return " titolo:" + titolo  +
                    ", anno di publicazione:" + data +
                    ", numeroDiPagine:" + numeroDiPagine ;
        }
    }



