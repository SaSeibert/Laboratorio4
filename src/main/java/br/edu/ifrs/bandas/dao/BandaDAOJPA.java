package br.edu.ifrs.bandas.dao;

import java.util.List;

import br.edu.ifrs.bandas.dominio.Banda;
import br.edu.ifrs.bandas.dominio.Genero;
import jakarta.persistence.EntityManager;

public class BandaDAOJPA implements BandaDAO {

    private EntityManager em;

    public BandaDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salvar(Banda banda) {
        em.getTransaction().begin();
        em.persist(banda);
        em.getTransaction().commit();
    }

    @Override
    public Banda buscarPorId(Long id) {
        return em.find(Banda.class, id);
    }

    @Override
    public List<Banda> listarTodos() {
        return em.createQuery("SELECT b FROM Banda b", Banda.class)
                 .getResultList();
    }

    @Override
    public void atualizar(Banda banda) {
        em.getTransaction().begin();
        em.merge(banda);
        em.getTransaction().commit();
    }

    @Override
    public void remover(Long id) {
        em.getTransaction().begin();
        Banda banda = em.find(Banda.class, id);
        em.remove(banda);
        em.getTransaction().commit();
    }

    @Override
    public List<Banda> listarPorGenero(String genero) {
        return em.createQuery(
                "SELECT b FROM Banda b WHERE b.genero = :genero", Banda.class)
                 .setParameter("genero", Genero.valueOf(genero))
                 .getResultList();
    }
}
