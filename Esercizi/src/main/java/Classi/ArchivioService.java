package Classi;

import DAO.CatalogoDAO;
import DAO.PRESTITODAO;
import DAO.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.time.LocalDate;
import java.util.*;

public class ArchivioService {
    private EntityManagerFactory emf;
    private EntityManager em;
    private CatalogoDAO catalogoDAO;
    private UtenteDAO utenteDAO;
    private PRESTITODAO prestitoDAO;
    Scanner scanner = new Scanner(System.in);

    public ArchivioService() {
        this.emf = Persistence.createEntityManagerFactory("esame-3");
        this.em = emf.createEntityManager();
        this.catalogoDAO = new CatalogoDAO(em);
        this.utenteDAO = new UtenteDAO(em);
        this.prestitoDAO = new PRESTITODAO(em);
    }

    public void avvia() {
        while (true) {
            try {
                mostraMenu();
                int scelta = scanner.nextInt();
                scanner.nextLine();
                switch (scelta) {
                    case 1: {
                        aggiungiCatalogo();
                        System.out.println("Elemento aggiunto");
                        break;
                    }
                    case 2: {
                        if (catalogoDAO.controlloArchivioVuoto()) {
                            System.err.println("l'archivio è vuoto aggiungi nuovi elementi");
                        } else {
                            eliminaCatalogo();
                        }
                        break;
                    }
                    case 3: {
                        if (catalogoDAO.controlloArchivioVuoto()) {
                            System.err.println("l'archivio è vuoto aggiungi nuovi elementi");
                            break;
                        } else {
                            Catalogo catalogo = ricercaISBN();
                            if (catalogo == null) {
                                System.out.println("catalogo non trovato");
                            } else {
                                System.out.println(catalogo);
                            }
                        }
                        break;
                    }
                    case 4: {
                        if (catalogoDAO.controlloArchivioVuoto()) {
                            System.err.println("l'archivio è vuoto aggiungi nuovi elementi");
                            break;
                        } else {
                            List<Catalogo> catalogo = ricercaAnno();
                            if (catalogo.isEmpty()) {
                                System.out.println("catalogo non trovato");
                            } else {
                                System.out.println(catalogo);
                            }
                            break;
                        }
                    }
                    case 5: {
                        if (catalogoDAO.controlloArchivioVuoto()) {
                            System.err.println("l'archivio è vuoto aggiungi nuovi elementi");
                            break;
                        } else {
                            List<Catalogo> catalogo = ricercaTitolo();
                            if (catalogo.isEmpty()) {
                                System.out.println("catalogo non trovato");
                            } else {
                                System.out.println(catalogo);
                            }
                            break;
                        }
                    }
                    case 6: {
                        if (catalogoDAO.controlloArchivioVuoto()) {
                            System.err.println("l'archivio è vuoto aggiungi nuovi elementi");
                            break;
                        } else {
                            List<Libro> libro = ricercaAutore();
                            if (libro.isEmpty()) {
                                System.out.println("libro non trovato");
                            } else {
                                System.out.println(libro);
                            }
                            break;
                        }
                    }
                    case 7: {
                        mostraMenuSecondario();
                        break;
                    } case 0:{
                        System.out.println("Sto uscendo...");
                        return;
                    } default:{
                        System.err.println("Numero dato non valido solo numeri tra 0 e 7");
                    }
                }
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
    }

    public void mostraMenu() {
        System.out.println("Benvenuto cosa vuoi fare?");
        System.out.println("1: aggiungi un nuovo catalogo - 2: elimina un catalogo");
        System.out.println("3: ricerca catalogo tramite ISBN - 4: ricerca per anno di pubblicazione 5: ricerca per titolo - 6: ricerca per autore");
        System.out.println("7: visualizza cataloghi prestati ");
    }

    public void aggiungiCatalogo() {
        String risposta;
        Long isbn = 0L;
        Catalogo catalogo = null;
        int anno = 0;
        int pagine = 0;

        while (true) {
            System.out.println("Vuoi inserire un libro o una rivista?");
            risposta = scanner.nextLine();
            if (!risposta.equalsIgnoreCase("libro") && !risposta.equalsIgnoreCase("rivista")) {
                System.err.println("Dato inserito non corretto inserire libro o rivista");
            } else {
                break;
            }
        }
        System.out.println("Inserisci il titolo:");
        String titolo = scanner.nextLine();
        while (true) {
            try {
                System.out.println("Inserisci il codice ISBN:");
                isbn = scanner.nextLong();
                scanner.nextLine();
                if (catalogoDAO.controlloISBN(isbn)) {
                    break;
                } else {
                    System.err.println("ISBN gia esistente");
                }
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("Inserisci l'anno di pubblicazione:");
                anno = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("Inserisci il numero di pagine:");
                pagine = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
        if (risposta.equalsIgnoreCase("libro")) {
            System.out.println("Inserire l'autore del libro:");
            String autore = scanner.nextLine();
            System.out.println("Inserire il genere del libro:");
            String genere = scanner.nextLine();
            catalogo = new Libro(isbn, titolo, anno, pagine, autore, genere);
        } else {
            while (true) {
                System.out.println("Inserisci la periodicità (settimanale/mensile/semestrale)");
                String periodicità = scanner.nextLine().toUpperCase();
                if (!periodicità.equalsIgnoreCase("settimanale") && !periodicità.equalsIgnoreCase("mensile") && !periodicità.equalsIgnoreCase("semestrale")) {
                    System.err.println("Dato inserito non corretto inserisci settimanale/mensile/semestrale");
                } else {
                    catalogo = new Rivista(isbn, titolo, anno, pagine, Periodicità.valueOf(periodicità));
                    break;
                }
            }
        }
        catalogoDAO.AggiungiCatalogo(catalogo);
    }

    public void eliminaCatalogo() {
        while (true) {
            try {
                System.out.println("Inserisci il codice ISBN del catalogo che si vuole eliminare");
                Long isbn = scanner.nextLong();
                scanner.nextLine();
                catalogoDAO.eliminaCatalogo(isbn);
                break;
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
    }

    public Catalogo ricercaISBN() {
        while (true) {
            try {
                System.out.println("Inserisci il codice ISBN che vuoi cercare:");
                Long isbn = scanner.nextLong();
                scanner.nextLine();
                return catalogoDAO.findCatalogo(isbn);
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
    }

    public List<Catalogo> ricercaAnno() {
        while (true) {
            try {
                System.out.println("Inserisci l'anno di pubblicazione del catalogo");
                int anno = scanner.nextInt();
                scanner.nextLine();
                return catalogoDAO.trovaAttraversoAnno(anno);
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
    }

    public List<Catalogo> ricercaTitolo() {
        System.out.println("Inserisci il titolo o parte di esso che vuoi cercare:");
        String titolo = scanner.nextLine();
        return catalogoDAO.trovaAttraversoTitolo(titolo);
    }

    public List<Libro> ricercaAutore() {
        System.out.println("Inserisci l'autore del libro che vuoi cercare:");
        String autore = scanner.nextLine();
        return catalogoDAO.trovaAttraversoAutore(autore);
    }

    public void mostraMenuSecondario() {
        inizializzaDati();
        while (true) {
            try {
                System.out.println("I prestiti al momento sono:");
               List<Prestito> totPrestiti=prestitoDAO.trovaTuttiPrestiti();
               for(Prestito p:totPrestiti){
                   System.out.println(p);
               }
                System.out.println("cosa vuoi fare?");
                System.out.println("1: ricerca elementi attualmente in prestito per utenti - 2: ricerca elementi prestati in ritardo - 0: torna al menù principale");
                int risposta=scanner.nextInt();
                scanner.nextLine();
                switch (risposta){
                    case 1:{
                        List<Prestito> prestiti=TrovaCataloghiInPrestito();
                        for (Prestito p : prestiti) {
                            System.out.println(p);
                        }
                        eliminaTuttiIPrestiti();
                        break;
                    }
                    case 2:{
                        List<Prestito> prestiti = prestitoDAO.ElementiInRitardo();
                        System.out.println("I prestiti in ritardo sono:");
                        for (Prestito p: prestiti){
                            System.out.println(p);
                        }
                        eliminaTuttiIPrestiti();
                        break;
                    } case 0:{
                        System.out.println("Sto uscendo...");
                        eliminaTuttiIPrestiti();
                        break;
                    }
                    default:{
                        System.err.println("Numero non valido inserisci 1/2/0");
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
    }

    public List<Prestito> TrovaCataloghiInPrestito(){
        while(true) {
            try {
                System.out.println("Inserisci il numero di tessara dell'utente:");
                int numeroTessera = scanner.nextInt();
                scanner.nextLine();

                List<Prestito> prestitoUtente = prestitoDAO.ElementiInPrestito(numeroTessera);
                if(prestitoUtente.isEmpty()){
                    System.out.println("I prestiti per la tessera n°" + numeroTessera + " non esistono");
                    return prestitoUtente;
                }else{
                    System.out.println("I prestiti per la tessera n°"+ numeroTessera +  " sono:" );
                    return prestitoUtente;
                }
            } catch (InputMismatchException e) {
                System.err.println("dato inserito non valido inserisci un numero");
                scanner.nextLine();
            }
        }
    }

    public void eliminaTuttiIPrestiti() {
        List<Prestito> tuttiPrestiti = prestitoDAO.trovaTuttiPrestiti();
        Set<Utente> utentiDaEliminare = new HashSet<>();
        Set<Catalogo> cataloghiDaEliminare = new HashSet<>();

        for (Prestito p : tuttiPrestiti) {
            utentiDaEliminare.add(p.getUtente());
            cataloghiDaEliminare.add(p.getElementoPrestato());
        }

        em.getTransaction().begin();
        for (Prestito p : tuttiPrestiti) {
            em.remove(em.contains(p) ? p : em.merge(p));
        }
        for (Catalogo c : cataloghiDaEliminare) {
            em.remove(em.contains(c) ? c : em.merge(c));
        }
        for (Utente u : utentiDaEliminare) {
            em.remove(em.contains(u) ? u : em.merge(u));
        }
        em.getTransaction().commit();
    }

    public void inizializzaDati(){
        Random rand=new Random();

        Long isbn1 = rand.nextLong(12345, 67891);
        Long isbn2 = rand.nextLong(12345, 67891);
        Long isbn3 = rand.nextLong(12345, 67891);
        Long isbn4 = rand.nextLong(12345, 67891);

        Libro libro1=new Libro(isbn1,"cado malato",2025,489,"Mr.Luchino","Drammatico");
        Libro libro2 =new Libro(isbn2,"Topo Gigio e l'eurovision perduto",2020,160,"Stefano","Fantasy");
        Rivista rivista1=new Rivista(isbn3,"Coppa Italia Lazio 2013 che goduria",2013,280,Periodicità.SEMESTRALE);
        Rivista rivista2=new Rivista(isbn4,"Ho finito le idee",2025,10,Periodicità.SETTIMANALE);

        Utente utente1=new Utente("Girolamo","Trombetta", LocalDate.of(2015,5,28));
        Utente utente2=new Utente("Marza","Pane",LocalDate.of(1980,12,25));

        Prestito prestito1=new Prestito(utente1,libro1,LocalDate.now(),null);
        Prestito prestito2=new Prestito(utente1,rivista1,LocalDate.of(2025,3,20),LocalDate.of(2025,3,30));
        Prestito prestito3=new Prestito(utente2,libro2,LocalDate.of(2025,2, 12),null);
        Prestito prestito4 = new Prestito(utente2,rivista2,LocalDate.now(),null);

        catalogoDAO.AggiungiCatalogo(libro1);
        catalogoDAO.AggiungiCatalogo(libro2);
        catalogoDAO.AggiungiCatalogo(rivista1);
        catalogoDAO.AggiungiCatalogo(rivista2);
        utenteDAO.aggiungiUtente(utente1);
        utenteDAO.aggiungiUtente(utente2);
        prestitoDAO.aggiungiPrestito(prestito1);
        prestitoDAO.aggiungiPrestito(prestito2);
        prestitoDAO.aggiungiPrestito(prestito3);
        prestitoDAO.aggiungiPrestito(prestito4);
    }
}


