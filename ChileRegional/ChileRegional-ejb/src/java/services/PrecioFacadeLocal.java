/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.Precio;

/**
 *
 * @author Sebastian
 */
@Local
public interface PrecioFacadeLocal {

    void create(Precio precio);

    void edit(Precio precio);

    void remove(Precio precio);

    Precio find(Object id);

    List<Precio> findAll();

    List<Precio> findRange(int[] range);

    int count();
    
}
