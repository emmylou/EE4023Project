/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Local;

/**
 *
 * @author Fierce
 */
@Local
public interface NewPurchaseOrderBeanLocal {
     public void createPurchaseOrder(Long cID, int pID, int qty);
}
