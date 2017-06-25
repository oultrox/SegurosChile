/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.Supervisor;

/**
 *
 * @author Pelao
 */
@Stateless
public class SupervisorFacade extends AbstractFacade<Supervisor> implements SupervisorFacadeLocal {

    @PersistenceContext(unitName = "jndiSeguro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupervisorFacade() {
        super(Supervisor.class);
    }
    
}
