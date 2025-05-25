package DAO;

import Classi.Catalogo;
import Classi.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


import java.util.List;

public class CatalogoDAO {
    private EntityManager em;

    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    public void AggiungiCatalogo(Catalogo catalogo){

        em.getTransaction().begin();
        em.persist(catalogo);
        em.getTransaction().commit();
    }

    public boolean controlloISBN(Long isbn) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Catalogo c WHERE c.ISBN= :isbn", Long.class);
        query.setParameter("isbn", isbn);
        Long count = query.getSingleResult();
        return count==0;
    }

    public  Catalogo findCatalogo( Long isbn){
        return em.find(Catalogo.class,isbn);
    }

    public void eliminaCatalogo(Long isbn){
        Catalogo c= findCatalogo(isbn);
        if(c!=null){
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            System.out.println("Elemento eliminato");
        }else{
            System.out.println("Elemento non trovato");
        }
    }

    public boolean controlloArchivioVuoto(){
        TypedQuery<Long>query=em.createQuery("SELECT COUNT(c) FROM Catalogo c",Long.class);
        Long count=query.getSingleResult();
        return count==0;
    }

    public List<Catalogo> trovaAttraversoAnno(int data){
        TypedQuery<Catalogo> query=em.createQuery("SELECT c FROM Catalogo c WHERE c.data= :data",Catalogo.class);
        query.setParameter("data", data);
        return query.getResultList();
    }

    public List<Libro> trovaAttraversoAutore(String autore){
        TypedQuery<Libro> query=em.createQuery("SELECT l FROM Libro l WHERE LOWER(l.autore)= LOWER(:autore)", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<Catalogo> trovaAttraversoTitolo(String titolo){
        TypedQuery<Catalogo> query=em.createQuery("SELECT c FROM Catalogo c WHERE LOWER(c.titolo) LIKE LOWER(:titolo)",Catalogo.class);
        query.setParameter("titolo",titolo + "%");
        return query.getResultList();
    }

}
