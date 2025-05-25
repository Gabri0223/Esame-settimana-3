package DAO;


import Classi.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class PRESTITODAO {
    private EntityManager em;

    public PRESTITODAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiPrestito(Prestito prestito){
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    public  Prestito findPrestito( int id){
        return em.find(Prestito.class,id);
    }

    public void eliminaPrestito(int id){
        Prestito p= findPrestito(id);
        if(p!=null){
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        }else{
            System.out.println("Prestito non trovato");
        }
    }

    public  List<Prestito> trovaTuttiPrestiti(){
        return em.createQuery("SELECT p FROM Prestito p",Prestito.class).getResultList();
    }

    public List<Prestito> ElementiInPrestito(int numeroTessera){
        TypedQuery<Prestito> query=em.createQuery("SELECT c FROM Prestito c WHERE c.utente.numeroTessera= :numeroTessera AND c.dataRestituzioneEffettiva IS NULL",Prestito.class);
        query.setParameter("numeroTessera",numeroTessera);
        return query.getResultList();
    }

    public  List<Prestito> ElementiInRitardo(){
        TypedQuery<Prestito> query=em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataInizioPrestito< :oggi",Prestito.class);
        query.setParameter("oggi",LocalDate.now());
        return query.getResultList();
    }


}
