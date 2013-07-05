/**
 * 
 */
package co.com.rentavoz.almacen.test.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.Test;

import co.com.rentavoz.logica.jpa.fachadas.VentaFacade;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.core
 * @class VentaTest
 * @date 24/06/2013
 *
 */
@Stateless
public class VentaTest {

	@EJB
	private VentaFacade facade;
	
	@Test
	public void test(){
	
		 
		 try {
			 EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
			facade = (VentaFacade)container.getContext().lookup("java:global/classes/VentaFacade");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
			
			System.out.println(facade);
		
	}
}
