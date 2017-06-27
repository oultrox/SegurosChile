/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.Precio;

/**
 *
 * @author Sebastian
 */
@Stateless
public class PrecioFacade extends AbstractFacade<Precio> implements PrecioFacadeLocal {

    @PersistenceContext(unitName = "jndiSeguro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrecioFacade() {
        super(Precio.class);
    }
    
}
