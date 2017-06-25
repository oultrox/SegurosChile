/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Local;
import pojos.SuperAdministrador;

/**
 *
 * @author Pelao
 */
@Local
public interface SuperAdministradorFacadeLocal {

    void create(SuperAdministrador superAdministrador);

    void edit(SuperAdministrador superAdministrador);

    void remove(SuperAdministrador superAdministrador);

    SuperAdministrador find(Object id);

    List<SuperAdministrador> findAll();

    List<SuperAdministrador> findRange(int[] range);

    int count();
    
}
