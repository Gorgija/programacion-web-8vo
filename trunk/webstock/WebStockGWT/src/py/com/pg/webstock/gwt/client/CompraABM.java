package py.com.pg.webstock.gwt.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import py.com.pg.webstock.entities.Compra;
import py.com.pg.webstock.entities.Proveedor;
import py.com.pg.webstock.gwt.client.access.CompraPA;
import py.com.pg.webstock.gwt.client.images.Recursos;
import py.com.pg.webstock.gwt.client.service.CompraService;
import py.com.pg.webstock.gwt.client.service.CompraServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.Grid.GridCell;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * NO VER
 * 
 * @author Arturo
 * 
 */
public class CompraABM implements IsWidget {

	/**
	 * ver documetnacion de la clase
	 * 
	 */
	public static final CompraPA pa = GWT.create(CompraPA.class);

	// Aca le decimso al GWT qeu cree el servicio, y el hace la magia!
	// Atender qeu nos retorna un ASYNC pero le pasamos el no Asincronos
	CompraServiceAsync CompraService = GWT.create(CompraService.class);

	ListStore<Compra> store;

	Grid<Compra> g;

	ToolBar bb;

	GridRowEditing<Compra> editing;

	private static final DateTimeFormat fmt = DateTimeFormat
			.getFormat("EEEE, dd 'de' MMMM 'del' yyyy");

	@Override
	public Widget asWidget() {
		// Este contenedor es lo mas, muestra de arriba a abajo todo lo que le
		// agregamos
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		// Aca se almacenan todos los valores que seran mostrados en el grid
		store = new ListStore<Compra>(pa.key());
		// este es el modlo de las columnas, usamos el por defecto par ano armar
		// bardo
		ColumnModel<Compra> cm = new ColumnModel<Compra>(getColumnConfig());
		// Grilla en si, le pasamos el store donde estan los componentes y la
		// configuracion de las columnas
		g = new Grid<Compra>(store, cm);
		//

		// esto le dice que la columna NOMBRE se exapndira cuando hya mucho
		// espacio
		g.getView().setAutoExpandColumn(proveedor);

		cargarStores();
		configEditing();
		crearToolBar();

		// TODO ver como hacer dinamico
		g.setHeight(800);
		// agregamos el toolbar y el grid a la vista, lo agregara arriba
		Document.get().setTitle("Compras - WebStock");
		con.add(bb);
		con.add(g);
		return con;
	}

	private void cargarStores() {
		CompraService.getEntidades(new AsyncCallback<List<Compra>>() {
			@Override
			public void onSuccess(List<Compra> result) {
				store.addAll(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Info.display("Compras", "no se pudo cargar Compras");
			}
		});

	}

	ColumnConfig<Compra, Proveedor> proveedor;
	ColumnConfig<Compra, String> ruc;
	ColumnConfig<Compra, String> telefono;
	ColumnConfig<Compra, Double> saldo;
	ColumnConfig<Compra, Date> fecha;

	/**
	 * ColumnConfig es la configuracion de Columnas, debe ser una lista donde
	 * cada componente es una columna Cada columan recibe tres objetos, los
	 * cuales son: <br>
	 * 1. ValueProvider: que le dice que atributo de esa clase se mostrara en
	 * esa fila <br>
	 * 2. Width: int, ancho de la fila <br>
	 * 3. Header: String, cabecera <br>
	 * <b>Los pasos para mostrar una fila son los siguientes</b> <br>
	 * 1. Obtiene el registro del store con el PropertyAccess<br>
	 * 2. Renderiza con el cell de cada columna, con el metodo doRender, este
	 * metodo por defecto hace toString, pero se peude reescribir, vease la
	 * fecha que se sobreescrie <br>
	 * 3. Muestra
	 * 
	 * @return
	 */
	public List<ColumnConfig<Compra, ?>> getColumnConfig() {
		proveedor = new ColumnConfig<Compra, Proveedor>(pa.proveedor(), 200, "Nombre");
		
//		nombre = new ColumnConfig<Compra, String>(pa.nombre(), 150, "Nombre");
//		ruc = new ColumnConfig<Compra, String>(pa.ruc(), 150, "RUC");
//		telefono = new ColumnConfig<Compra, String>(pa.telefono(), 150,
//				"Telefono");
//		saldo = new ColumnConfig<Compra, Double>(pa.saldo(), 150, "Saldo");
//		fecha = new ColumnConfig<Compra, Date>(pa.fechaAlta(), 250,
//				"Fecha de alta");
//
//		fecha.setCell(new DateCell(fmt));

		List<ColumnConfig<Compra, ?>> aRet = new ArrayList<ColumnConfig<Compra, ?>>();
//		aRet.add(nombre);
//		aRet.add(ruc);
//		aRet.add(telefono);
//		aRet.add(saldo);
//		aRet.add(fecha);
		return aRet;

	}

	/**
	 * Ahora hacemos a las celdas editables esto es re magico y lo mas
	 * interesante de esta clase del orte
	 */
	public void configEditing() {
//		editing = new GridRowEditing<Compra>(g);
//
//		TextField tfNombre = new TextField();
//		tfNombre.setEmptyText("Ingrese el nombre");
//		editing.addEditor(nombre, tfNombre);
//
//		TextField tfRuc = new TextField();
//		tfRuc.setEmptyText("Ingrese el RUC");
//		editing.addEditor(ruc, tfRuc);
//
//		TextField tfTelefono = new TextField();
//		tfTelefono.setEmptyText("Ingrese el Telefono");
//		editing.addEditor(telefono, tfTelefono);
//
//		SpinnerFieldCell<Double> sfc = new SpinnerFieldCell<Double>(
//				new DoublePropertyEditor());
//
//		NumberField<Double> nfSaldo = new NumberField<Double>(sfc,
//				sfc.getPropertyEditor());
//		tfTelefono.setEmptyText("Ingrese el Telefono");
//		editing.addEditor(saldo, nfSaldo);
//
//		DateField dateField = new DateField(new DateTimePropertyEditor(fmt));
//		dateField.setClearValueOnParseError(false);
//		editing.addEditor(fecha, dateField);
//
//		editing.addCompleteEditHandler(new CompleteEditHandler<Compra>() {
//
//			@Override
//			public void onCompleteEdit(CompleteEditEvent<Compra> event) {
//				// guarda todos lso cambios en el store, asi podemos obtener el
//				// cilnete cambiado
//				store.commitChanges();
//				/**
//				 * esta linea me tomo 3 meses construir, lo que hace es: <Br>
//				 * 1. Obtiene la fila que se esta editando <br>
//				 * 2. La busca en el store, el store se actualiza
//				 * automaticamente y su orden es el orden donde se muestra
//				 */
//				editadoCompleto(store.get(event.getEditCell().getRow()));
//			}
//		});
	}

	public void crearToolBar() {
		if (bb != null)
			return;
		bb = new ToolBar();
		bb.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		bb.setPack(BoxLayoutPack.CENTER);
		// TODO cambiar
		TextButton tbAdd = new TextButton("Agregar");
		tbAdd.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Compra nuevo = new Compra();
				store.add(nuevo);
				int index = store.indexOf(nuevo);
				editing.startEditing(new GridCell(index, 0));
			}
		});
		tbAdd.setIcon(Recursos.Util.getInstance().iconAdd());
		tbAdd.setIconAlign(IconAlign.RIGHT);

		TextButton tbRemove = new TextButton("Borrar");
		tbRemove.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (g.getSelectionModel().getSelectedItem() == null)
					return;
				else
					eliminar(g.getSelectionModel().getSelectedItem());
			}
		});
		tbRemove.setIcon(Recursos.Util.getInstance().iconDelete());
		bb.add(tbAdd);
		bb.add(tbRemove);

	}

	public void editadoCompleto(final Compra c) {
		// nuevo
		if (c.getId() == 0)
			CompraService.add(c, new GuardarCallBack(c));
		else
			CompraService.update(c, new GuardarCallBack(c));
	}

	public void eliminar(final Compra entidad) {
		CompraService.remove(entidad, new AsyncCallback<Compra>() {
			@Override
			public void onFailure(Throwable caught) {
				Info.display("Compras", "Eliminacion fallada, reintente");
			}

			@Override
			public void onSuccess(Compra result) {
				Info.display("Compras", "Eliminacion correcta");
				store.remove(entidad);
				editing.cancelEditing();
			}
		});
	}

	public static class GuardarCallBack implements AsyncCallback<Compra> {
		Compra c;

		public GuardarCallBack(Compra c) {
			this.c = c;
		}

		@Override
		public void onSuccess(Compra result) {
			c.setId(result.getId());
			Info.display("Compras", "Guardado");
		}

		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
			Info.display("Compras", "Imposible guardar, reintente");
		}
	}
}