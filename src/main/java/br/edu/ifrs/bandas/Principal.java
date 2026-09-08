package br.edu.ifrs.bandas;

import br.edu.ifrs.bandas.dao.BandaDAO;
import br.edu.ifrs.bandas.dao.BandaDAOJPA;
import br.edu.ifrs.bandas.dominio.Album;
import br.edu.ifrs.bandas.dominio.Banda;
import br.edu.ifrs.bandas.dominio.Funcao;
import br.edu.ifrs.bandas.dominio.Genero;
import br.edu.ifrs.bandas.dominio.Integrante;
import br.edu.ifrs.bandas.dominio.Musica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Principal {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4_bandas");
        EntityManager em = emf.createEntityManager();

        BandaDAO dao = new BandaDAOJPA(em);

        // Cria a banda com integrante, album e musica
        Banda gorillaz = new Banda("Gorillaz", Genero.ROCK);

        Integrante vocalista = new Integrante("2-D", 40, Funcao.VOCALISTA);
        gorillaz.adicionarIntegrante(vocalista);

        Album demonDays = new Album("Demon Days", Genero.ROCK, 2005);
        gorillaz.adicionarAlbum(demonDays);

        Musica feelGoodInc = new Musica("Feel Good Inc.", 3.41);
        demonDays.adicionarMusica(feelGoodInc);

        // Persiste cada objeto separadamente
        em.getTransaction().begin();
        em.persist(gorillaz);
        em.persist(vocalista);
        em.persist(demonDays);
        em.persist(feelGoodInc);
        em.getTransaction().commit();

        System.out.println("=== Todas as bandas ===");
        for (Banda b : dao.listarTodos()) {
            System.out.println(b);
        }

        System.out.println("\n=== Bandas de ROCK ===");
        for (Banda b : dao.listarPorGenero("ROCK")) {
            System.out.println(b);
        }

        em.close();
        emf.close();
    }
}
