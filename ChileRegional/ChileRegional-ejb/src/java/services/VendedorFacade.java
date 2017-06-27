/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.Vendedor;

/**
 *
 * @author Sebastian
 */
@Stateless
public class VendedorFacade extends AbstractFacade<Vendedor> implements VendedorFacadeLocal {

    @PersistenceContext(unitName = "jndiSeguro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendedorFacade() {
        super(Vendedor.class);
    }
    
}
