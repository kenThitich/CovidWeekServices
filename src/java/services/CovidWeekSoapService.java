/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Covidweek;

/**
 *
 * @author TUFGAMING
 */
@WebService(serviceName = "CovidWeekSoapService")
public class CovidWeekSoapService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CovidWeekSoapPU");

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findAllCovidWeek")
    public List<Covidweek> findAllCovidWeek() {
        EntityManager em = emf.createEntityManager();
        List<Covidweek> covidList = (List<Covidweek>) em.createNamedQuery("Covidweek.findAll").getResultList();
        return covidList;
    }   

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findByYear")
    public List<Covidweek> findByYear(@WebParam(name = "year") int year) {
         EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Covidweek.findByCovidyear");
        query.setParameter("covidyear", year);
        List<Covidweek> covidList = (List<Covidweek>) query.getResultList();
        return covidList;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findBySeq")
    public Covidweek findBySeq(@WebParam(name = "seq") int seq) {
        EntityManager em = emf.createEntityManager();
        Covidweek covid = em.find(Covidweek.class, seq);
        em.close();
        return covid;
    }
}
