package DAO;

import Classi.Utente;
import jakarta.persistence.EntityManager;


public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiUtente(Utente utente){
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }



    public  Utente findUtente( int id){
        return em.find(Utente.class,id);
    }

    public void eliminaUtente(int id){
        Utente u= findUtente(id);
        if(u!=null){
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
        }else{
            System.out.println("Utente non trovato");
        }
    }
}
