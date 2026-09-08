package br.edu.ifrs.bandas;

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

        Banda Gorillaz = new Banda("Gorillaz", Genero.ROCK);
        Banda TheStrokes = new Banda("The Strokes", Genero.INDIE);

        Album DemonDays = new Album("Demon Days",Genero.INDIE,2005);
        Album TheNewAbnormal = new Album("The New Abnormal",Genero.INDIE,2020);
        Album RealityAwaits = new Album("Reality Awaits",Genero.INDIE,2026);

        Gorillaz.adicionarAlbum(DemonDays);
        TheStrokes.adicionarAlbum(TheNewAbnormal);
        TheStrokes.adicionarAlbum(RealityAwaits);

        TheStrokes.adicionarIntegrante(new Integrante("Julian Casablancas",48, Funcao.VOCALISTA));
        TheStrokes.adicionarIntegrante(new Integrante("Albert Hammond Jr", 50, Funcao.GUITARRISTA));
        TheStrokes.adicionarIntegrante(new Integrante("Nikolai Fraiture", 41, Funcao.BAIXISTA));
        TheStrokes.adicionarIntegrante(new Integrante("Fabrizio Moretti", 46, Funcao.BATERISTA));

        Gorillaz.adicionarIntegrante(new Integrante("Murdoc Niccals", 38, Funcao.BAIXISTA));
        Gorillaz.adicionarIntegrante(new Integrante("Russel Robs", 40, Funcao.BATERISTA));
        Gorillaz.adicionarIntegrante(new Integrante("Noodle", 30, Funcao.GUITARRISTA));
        Gorillaz.adicionarIntegrante(new Integrante("2-D", 40, Funcao.VOCALISTA));

        DemonDays.adicionarMusica(new Musica("Intro",1.3));
        DemonDays.adicionarMusica(new Musica("Last Living souls",3.11));
        DemonDays.adicionarMusica(new Musica("Feel Good Inc.",3.41));
        DemonDays.adicionarMusica(new Musica("DARE",4.0));

        TheNewAbnormal.adicionarMusica(new Musica("The Adults are Talking",5.0));
        TheNewAbnormal.adicionarMusica(new Musica("Selfless",3.2));
        TheNewAbnormal.adicionarMusica(new Musica("Bad Decisions",3.5));
        TheNewAbnormal.adicionarMusica(new Musica("Eternal Summer",6.0));
        TheNewAbnormal.adicionarMusica(new Musica("Why Sundays are so depressing",4.3));

        RealityAwaits.adicionarMusica(new Musica("Psycho Shit",3.0));
        RealityAwaits.adicionarMusica(new Musica("Dine N'Dash",3.4));
        RealityAwaits.adicionarMusica(new Musica("Going Shopping",4.2));


        System.out.println();
        System.out.println("Banda: " + TheStrokes);
        System.out.println("Albuns: " + TheStrokes.getAlbuns());
        System.out.println("Integrantes: " + TheStrokes.getIntegrantes());
        System.out.println();

        for (Album album : TheStrokes.getAlbuns()){
            System.out.println(album + " | Ano: " + album.getAnoLancamento() + " | Musicas: " + album.getMusicas());
        }

        //CREATE DATABASE IF NOT EXISTS lab4_bandas;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4_bandas");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(TheStrokes);
        em.getTransaction().commit();






        
    }
}