/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.Nombreproducto;

/**
 *
 * @author Sebastian
 */
@Stateless
public class NombreproductoFacade extends AbstractFacade<Nombreproducto> implements NombreproductoFacadeLocal {

    @PersistenceContext(unitName = "jndiSeguro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NombreproductoFacade() {
        super(Nombreproducto.class);
    }
    
}
