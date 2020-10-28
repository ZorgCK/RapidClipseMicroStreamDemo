
package one.microstream.microstreamdemo.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rapidclipse.framework.server.resources.ApplicationResource;
import com.rapidclipse.framework.server.resources.CaptionUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import one.microstream.microstreamdemo.storage.Customer;
import one.microstream.microstreamdemo.storage.DB;


@Route("")
@HtmlImport("frontend://styles/shared-styles.html")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainLayout extends VerticalLayout implements PageConfigurator
{
	private Set<Customer> customers = new HashSet();
	
	public MainLayout()
	{
		super();
		this.initUI();
	}
	
	@Override
	public void configurePage(final InitialPageSettings settings)
	{
		settings.addLink("shortcut icon", "frontend/images/favicon.ico");
		settings.addFavIcon("icon", "frontend/images/favicon256.png", "256x256");
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnImportData}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnImportData_onClick(final ClickEvent<Button> event)
	{
		try
		{
			final Type listType = new TypeToken<Set<Customer>>()
			{}.getType();
			this.customers.clear();
			this.customers = new Gson().fromJson(this.getMockupFileContent(), listType);

			this.grid.setItems(this.customers);
		}
		catch(final IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnStoreData}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnStoreData_onClick(final ClickEvent<Button> event)
	{
		DB.root.getCustomers().addAll(this.customers);
		DB.storageManager.store(DB.root.getCustomers());
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnShowDBContent}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnShowDBContent_onClick(final ClickEvent<Button> event)
	{
		this.grid.setItems(DB.root.getCustomers());
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnEditSelected}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnEditSelected_onClick(final ClickEvent<Button> event)
	{
		final Customer value = this.grid.asSingleSelect().getValue();
		value.setFirstname("John");
		value.setLastname("Doe");
		
		DB.storageManager.store(value);
		
		this.grid.getDataProvider().refreshAll();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnDeleteSelected}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnDeleteSelected_onClick(final ClickEvent<Button> event)
	{
		final Customer value = this.grid.asSingleSelect().getValue();
		
		DB.root.getCustomers().remove(value);
		DB.storageManager.store(DB.root.getCustomers());
		
		this.grid.getDataProvider().refreshAll();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnQueryFemales}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnQueryFemales_onClick(final ClickEvent<Button> event)
	{
		final List<Customer> females =
			DB.root.getCustomers().stream().filter(c -> c.getGender().equalsIgnoreCase("female"))
				.collect(Collectors.toList());
		
		this.grid.setItems(females);
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnClearEntries}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnClearEntries_onClick(final ClickEvent<Button> event)
	{
		DB.root.getCustomers().clear();
		DB.storageManager.store(DB.root.getCustomers());
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.horizontalLayout  = new HorizontalLayout();
		this.btnImportData     = new Button();
		this.btnStoreData      = new Button();
		this.btnShowDBContent  = new Button();
		this.btnEditSelected   = new Button();
		this.btnDeleteSelected = new Button();
		this.btnQueryFemales   = new Button();
		this.btnClearEntries   = new Button();
		this.grid              = new Grid<>(Customer.class, false);
		
		this.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
		this.btnImportData.setText("Import Data");
		this.btnStoreData.setText("Store imported");
		this.btnShowDBContent.setText("Show all");
		this.btnEditSelected.setText("Edit selected");
		this.btnDeleteSelected.setText("Delete selected");
		this.btnQueryFemales.setText("Show females");
		this.btnClearEntries.setText("Clear All Entries");
		this.grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);
		this.grid.addColumn(Customer::getId).setKey("id").setHeader(CaptionUtils.resolveCaption(Customer.class, "id"))
			.setSortable(true);
		this.grid.addColumn(Customer::getLastname).setKey("lastname")
			.setHeader(CaptionUtils.resolveCaption(Customer.class, "lastname")).setSortable(true);
		this.grid.addColumn(Customer::getFirstname).setKey("firstname")
			.setHeader(CaptionUtils.resolveCaption(Customer.class, "firstname")).setSortable(true);
		this.grid.addColumn(Customer::getGender).setKey("gender")
			.setHeader(CaptionUtils.resolveCaption(Customer.class, "gender")).setSortable(true);
		this.grid.addColumn(Customer::getMail).setKey("mail")
			.setHeader(CaptionUtils.resolveCaption(Customer.class, "mail"))
			.setSortable(true);
		this.grid.addColumn(Customer::getIpAddress).setKey("ipAddress")
			.setHeader(CaptionUtils.resolveCaption(Customer.class, "ipAddress")).setSortable(true);
		this.grid.setSelectionMode(Grid.SelectionMode.SINGLE);
		
		this.btnImportData.setSizeUndefined();
		this.btnStoreData.setSizeUndefined();
		this.btnShowDBContent.setSizeUndefined();
		this.btnEditSelected.setSizeUndefined();
		this.btnDeleteSelected.setSizeUndefined();
		this.btnQueryFemales.setSizeUndefined();
		this.btnClearEntries.setSizeUndefined();
		this.horizontalLayout.add(this.btnImportData, this.btnStoreData, this.btnShowDBContent, this.btnEditSelected,
			this.btnDeleteSelected, this.btnQueryFemales,
			this.btnClearEntries);
		this.horizontalLayout.setWidthFull();
		this.horizontalLayout.setHeight(null);
		this.grid.setSizeFull();
		this.add(this.horizontalLayout, this.grid);
		this.setFlexGrow(1.0, this.grid);
		this.setSizeFull();
		
		this.btnImportData.addClickListener(this::btnImportData_onClick);
		this.btnStoreData.addClickListener(this::btnStoreData_onClick);
		this.btnShowDBContent.addClickListener(this::btnShowDBContent_onClick);
		this.btnEditSelected.addClickListener(this::btnEditSelected_onClick);
		this.btnDeleteSelected.addClickListener(this::btnDeleteSelected_onClick);
		this.btnQueryFemales.addClickListener(this::btnQueryFemales_onClick);
		this.btnClearEntries.addClickListener(this::btnClearEntries_onClick);
	} // </generated-code>
	
	// <generated-code name="variables">
	private Button           btnImportData, btnStoreData, btnShowDBContent, btnEditSelected, btnDeleteSelected,
		btnQueryFemales, btnClearEntries;
	private HorizontalLayout horizontalLayout;
	private Grid<Customer>   grid;
	// </generated-code>
	
	public String getMockupFileContent() throws IOException
	{
		final InputStream is =
			ApplicationResource.createInputStream(
				this.getClass(), "/mockup/MOCK_DATA.json");

		final BufferedReader streamReader       = new BufferedReader(new InputStreamReader(is));
		final StringBuilder  responseStrBuilder = new StringBuilder();
		String               inputString;
		while((inputString = streamReader.readLine()) != null)
		{
			responseStrBuilder.append(inputString);
		}
		
		streamReader.close();
		return responseStrBuilder.toString();
	}
	
	public static String escapeStringAsFilename(final String inputName)
	{
		final String encoded = inputName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
		final int    end     = Math.min(encoded.length(), 124);
		return encoded.substring(0, end);
	}
}
