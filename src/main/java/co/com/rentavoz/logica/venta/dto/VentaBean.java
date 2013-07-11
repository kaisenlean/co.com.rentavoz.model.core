/**
 *
 */
package co.com.rentavoz.logica.venta.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

import co.com.rentavoz.logica.jpa.entidades.Pago;
import co.com.rentavoz.logica.jpa.entidades.TerceroVenta;
import co.com.rentavoz.logica.jpa.entidades.Venta;
import co.com.rentavoz.logica.jpa.entidades.VentaLinea;
import co.com.rentavoz.logica.jpa.fachadas.CuentasFacade;
import co.com.rentavoz.logica.jpa.fachadas.TipoPagoFacade;
import co.com.rentavoz.logica.jpa.fachadas.VentaFacade;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.core
 * @class VentaBean
 * @date 2/06/2013
 * 
 */
@Singleton
public class VentaBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@EJB
	private VentaFacade ventaFacade;
	@EJB
	private TipoPagoFacade tipoPagoFacade;
	@EJB
	private CuentasFacade cuentasFacade;

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/06/2013
	 */
	@PostConstruct
	public void init() {
	}

	/**
	 *  
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/06/2013
	 * @param dto
	 * @return
	 */
	public VentaDTO registrarVenta(VentaDTO dto) {
		ArrayList<Pago> pagos = new ArrayList<Pago>();
		ArrayList<TerceroVenta> tercerosDeVenta = new ArrayList<TerceroVenta>();

		Venta venta = new Venta();
		venta.setIdVenta(ventaFacade.findSgteNumero());
		venta.setFecha(new Date());
		venta.setVenFecha(dto.getFecha());
		venta.setObservacion(dto.getObservacion());

		/* Bigdecimal para guardar el saldo la suma de las linesas */
		double saldo = 0.0;

		/* asignamos el valor del domicilio a la venta asi sea 0.0 */
		saldo += (dto.getDomicilio().doubleValue());

		int pos = 0;
		/* recorremos lineas para calcular saldo a pagar */
		for (VentaLinea vlTemp : dto.getLineas()) {
			// suma el precio de la linea al total
			saldo += (vlTemp.getVentLinPrecio().doubleValue());
			// suma el deposito
			saldo += (vlTemp.getVentLinDeposito().doubleValue());
			vlTemp.setVentaidVenta(venta);
			dto.getLineas().set(pos, vlTemp);
			pos++;
		}
		// asignamos las ventaslineas a la venta
		venta.setVentaLineaList(dto.getLineas());

		/* adicionamos los terceros a la venta(generalmente siempre sera 1) */
		tercerosDeVenta.add(new TerceroVenta(dto.getTercero(), venta));
		venta.setTerceroVentaList(tercerosDeVenta);

		Pago pagoTemp = new Pago();
		pagoTemp.setCuentasidCuentas(null);
		pagoTemp.setFecha(new Date());
		pagoTemp.setPagFechaProx(new Date());
		pagoTemp.setPagValor(BigDecimal.valueOf(saldo));
		pagoTemp.setVentaidVenta(venta);
		pagoTemp.setTipoPagoidTipoPago(tipoPagoFacade.find(Integer.parseInt(dto
				.getTipoPago())));
		if (dto.isPagoTotal()) {
			pagoTemp.setPagFechaProx(dto.getFechaProxPago());
		}
		if (dto.isPagoConsignacion()) {

			pagoTemp.setCuentasidCuentas(cuentasFacade.find(dto
					.getSelIdCuenta()));

		}
		dto.setPago(pagoTemp);
		/* adicionamos los pagos a la venta */
		pagos.add(dto.getPago());
		venta.setPagoList(pagos);

		venta.setVenDomicilio(dto.getDomicilio());
		venta.setVenSaldo(BigDecimal.valueOf(saldo));

		/* registramos la venta en la bd */
		try {

			ventaFacade.create(venta);

		} catch (Exception e) {
			System.err.println(e.toString());
		}

		dto.setBaseData(venta);
		return dto;
	}
}
