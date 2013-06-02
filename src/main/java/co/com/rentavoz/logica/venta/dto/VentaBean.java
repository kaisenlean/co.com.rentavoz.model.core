/**
 *
 */
package co.com.rentavoz.logica.venta.dto;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.rentavoz.logica.jpa.fachadas.VentaFacade;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.core
 * @class VentaBean
 * @date 2/06/2013
 *
 */
@Stateless
public class VentaBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @EJB
    private VentaFacade ventaFacade;

    /**
     *
     */
    @PostConstruct
    public void init() {

        System.out.println(  ventaFacade.count());
    }
}
