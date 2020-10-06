
package one.microstream.microstreamdemo.ui;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import com.vaadin.flow.component.page.LoadingIndicatorConfiguration;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

import one.microstream.microstreamdemo.storage.Customer;
import one.microstream.microstreamdemo.storage.DB;


@Route("")
@HtmlImport("frontend://styles/shared-styles.html")
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

		final LoadingIndicatorConfiguration conf = settings.getLoadingIndicatorConfiguration();
		
		/*
		 * Delay for showing the indicator and setting the 'first' class name.
		 */
		conf.setFirstDelay(300); // 300ms is the default
		
		/* Delay for setting the 'second' class name */
		conf.setSecondDelay(1500); // 1500ms is the default
		
		/* Delay for setting the 'third' class name */
		conf.setThirdDelay(5000); // 5000ms is the default
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #button}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button_onClick(final ClickEvent<Button> event)
	{
		final String mockupFileContent = MainLayout.getMockupFileContent().get();
		
		final Type listType = new TypeToken<Set<Customer>>()
		{}.getType();
		this.customers.clear();
		this.customers = new Gson().fromJson(mockupFileContent, listType);
		
		this.grid.setItems(this.customers);
	}
	
	public static Optional<String> getMockupFileContent()
	{
		final Path          filepath =
			Paths.get("C:/MOCK_DATA.json");
		final StringBuilder sb       = new StringBuilder();
		try(Stream<String> stream = Files.lines(filepath, StandardCharsets.UTF_8))
		{
			stream.forEach(s -> sb.append(s).append("\n"));
			return Optional.of(sb.toString());
		}
		catch(final IOException e)
		{
			return Optional.empty();
		}
	}
	
	public static String escapeStringAsFilename(final String inputName)
	{
		final String encoded = inputName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
		final int    end     = Math.min(encoded.length(), 124);
		return encoded.substring(0, end);
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #button2}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button2_onClick(final ClickEvent<Button> event)
	{
		DB.root.getCustomers().addAll(this.customers);
		DB.storageManager.store(DB.root.getCustomers());
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #button5}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button5_onClick(final ClickEvent<Button> event)
	{
		this.grid.setItems(DB.root.getCustomers());
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #button3}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button3_onClick(final ClickEvent<Button> event)
	{
		final Customer value = this.grid.asSingleSelect().getValue();
		value.setFirstname("John");
		value.setLastname("Doe");
		
		DB.storageManager.store(value);
		
		this.grid.getDataProvider().refreshAll();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #button4}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button4_onClick(final ClickEvent<Button> event)
	{
		final Customer value = this.grid.asSingleSelect().getValue();
		
		DB.root.getCustomers().remove(value);
		
		DB.storageManager.store(DB.root.getCustomers());
		
		this.grid.getDataProvider().refreshAll();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #button6}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button6_onClick(final ClickEvent<Button> event)
	{
		final List<Customer> females =
			DB.root.getCustomers().stream().filter(c -> c.getGender().equalsIgnoreCase("female"))
				.collect(Collectors.toList());
		
		this.grid.setItems(females);
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #button7}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button7_onClick(final ClickEvent<Button> event)
	{
		DB.root.getCustomers().clear();
		DB.storageManager.store(DB.root.getCustomers());
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.horizontalLayout = new HorizontalLayout();
		this.button           = new Button();
		this.button2          = new Button();
		this.button5          = new Button();
		this.button3          = new Button();
		this.button4          = new Button();
		this.button6          = new Button();
		this.button7          = new Button();
		this.grid             = new Grid<>(Customer.class, false);
		
		this.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
		this.button.setText("Import Data");
		this.button2.setText("Store imported");
		this.button5.setText("Show all");
		this.button3.setText("Edit selected");
		this.button4.setText("Delete selected");
		this.button6.setText("Show females");
		this.button7.setText("Clear All Entries");
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
		
		this.button.setSizeUndefined();
		this.button2.setSizeUndefined();
		this.button5.setSizeUndefined();
		this.button3.setSizeUndefined();
		this.button4.setSizeUndefined();
		this.button6.setSizeUndefined();
		this.button7.setSizeUndefined();
		this.horizontalLayout.add(this.button, this.button2, this.button5, this.button3, this.button4, this.button6,
			this.button7);
		this.horizontalLayout.setWidthFull();
		this.horizontalLayout.setHeight(null);
		this.grid.setSizeFull();
		this.add(this.horizontalLayout, this.grid);
		this.setFlexGrow(1.0, this.grid);
		this.setSizeFull();
		
		this.button.addClickListener(this::button_onClick);
		this.button2.addClickListener(this::button2_onClick);
		this.button5.addClickListener(this::button5_onClick);
		this.button3.addClickListener(this::button3_onClick);
		this.button4.addClickListener(this::button4_onClick);
		this.button6.addClickListener(this::button6_onClick);
		this.button7.addClickListener(this::button7_onClick);
	} // </generated-code>

	// <generated-code name="variables">
	private Button           button, button2, button5, button3, button4, button6, button7;
	private HorizontalLayout horizontalLayout;
	private Grid<Customer>   grid;
	// </generated-code>
	
}
