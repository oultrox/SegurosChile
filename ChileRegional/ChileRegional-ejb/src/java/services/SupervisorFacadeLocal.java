/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.Supervisor;

/**
 *
 * @author Sebastian
 */
@Local
public interface SupervisorFacadeLocal {

    void create(Supervisor supervisor);

    void edit(Supervisor supervisor);

    void remove(Supervisor supervisor);

    Supervisor find(Object id);

    List<Supervisor> findAll();

    List<Supervisor> findRange(int[] range);

    int count();
    
}
