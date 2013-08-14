///**
// * 
// */
//package co.com.rentavoz.almacen.test.bean;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.ejb.EJB;
//
//import org.junit.Test;
//
//import co.com.rentavoz.logica.venta.VentaBean;
//
///**
// * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
// * @project co.com.rentavoz.model.core
// * @class TestVentaBean
// * @date 14/07/2013
// * 
// */
//public class TestVentaBean {
//
//	@EJB
//	private VentaBean ventaBean;
//
//	/**
//	 * Test method for
//	 * {@link co.com.rentavoz.logica.venta.VentaBean#registrarVenta(co.com.rentavoz.logica.venta.dto.VentaDTO)}
//	 * .
//	 */
//	@SuppressWarnings("deprecation")
//	@Test
//	public void calcularFechaCorte() {
//		System.out.println(ventaBean);
//		Calendar calendar = Calendar.getInstance();
//		Date now = new Date();
//		calendar.set(Calendar.MONTH, (now.getMonth()) + 1);
//		calendar.set(Calendar.DAY_OF_MONTH, 20);
//
//		System.out.println(calendar.getTime());
//	}
//
//}
