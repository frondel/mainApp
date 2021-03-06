import javax.swing.*;
import java.awt.*;

public class MainMenu {

    JFrame frame;
    SQLConnections connection;
    private String activeUser;

    //This is the wrapper method for the construction of the UI.
    public void open(String username) {
        activeUser = username;
        connection = new SQLConnections();
        constructUi();
    }

    //This creates the UI.
    private void constructUi() {
        //Create the primary frame.
        frame = new JFrame("Overdrive");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Overdrive");
        frame.setBounds(100, 100, 1356, 928);
        frame.getContentPane().setLayout(null);

        //Create Overdrive Label
        JLabel overdriveLabel = new JLabel("Overdrive");
        overdriveLabel.setBounds(620, 5, 300, 60);
        overdriveLabel.setFont(new Font(overdriveLabel.getFont().getName(), Font.PLAIN, 32));
        frame.getContentPane().add(overdriveLabel);

        //Creates the main window panel.
        JTabbedPane mainWindowPanel = new JTabbedPane(JTabbedPane.TOP);
        mainWindowPanel.setBorder(null);
        mainWindowPanel.setBounds(10, 72, 1330, 795);
        frame.getContentPane().add(mainWindowPanel);

        //Creates the customer panel.
        JPanel customerPanel = new JPanel();
        mainWindowPanel.addTab("Customers", null, customerPanel, "Customer "
                + "Database");
        customerPanel.setLayout(null);

        //Initialize the customer tab and add its components.
        CustomerTab customerTab = new CustomerTab();
        JScrollPane custTablePanel = new JScrollPane();
        custTablePanel.setBounds(0, 11, 927, 817);
        customerPanel.add(custTablePanel);
        custTablePanel.setViewportView(customerTab.createCustomerTable());

        customerPanel.add(customerTab.createRefreshListButton());
        customerPanel.add(customerTab.createAddCustomerButton());
        customerPanel.add(customerTab.createDeleteCustomerButton());
        customerPanel.add(customerTab.createUpdateCustomerButton());

        //Create the panel for work orders.
        JPanel workOrderPanel = new JPanel();
        mainWindowPanel.addTab("Work Orders", null, workOrderPanel,
                "All Work Orders");
        workOrderPanel.setLayout(null);

        //Initialize the work order tab and add its components.
        WorkOrderTab workOrderTab = new WorkOrderTab();
        workOrderPanel.add(workOrderTab.createWorkOrderTable());
        workOrderPanel.add(workOrderTab.createRefreshListButton());
        workOrderPanel.add(workOrderTab.createAddWorkOrderButton());
        workOrderPanel.add(workOrderTab.createDeleteWorkOrderButton());
        workOrderPanel.add(workOrderTab.createUpdateWorkOrderButton());

        //Create the panel for inventory.
        JPanel inventoryPanel = new JPanel();
        mainWindowPanel.addTab("Inventory", null, inventoryPanel, "Store "
                + "Inventory");
        inventoryPanel.setLayout(null);

        //Initialize the inventory tab and add its components.
        InventoryTab inventoryTab = new InventoryTab();
        JScrollPane inventoryTablePanel = new JScrollPane();
        inventoryTablePanel.setBounds(0, 11, 927, 817);
        inventoryPanel.add(inventoryTablePanel);
        inventoryTablePanel.setViewportView(inventoryTab.createInventoryTable());

        inventoryPanel.add(inventoryTab.createRefreshListButton());
        inventoryPanel.add(inventoryTab.createAddInventoryItemButton());
        inventoryPanel.add(inventoryTab.createDeleteInventoryItemButton());
        inventoryPanel.add(inventoryTab.createUpdateInventoryItemButton());
        inventoryPanel.add(inventoryTab.createOrderInventoryItemButton());

        //Create the panel for symptoms.
        JPanel symptomsPanel = new JPanel();
        mainWindowPanel.addTab("Symptoms", null, symptomsPanel, "Symptoms "
                + "Checker");
        symptomsPanel.setLayout(null);

        //Initialize the symptoms tab and its components.
        Symptoms symptoms = new Symptoms();
        symptomsPanel.add(symptoms.createSymptomsLabel());
        symptomsPanel.add(symptoms.createInputBox1());
        symptomsPanel.add(symptoms.createInputBox2());
        symptomsPanel.add(symptoms.createInputBox3());
        symptomsPanel.add(symptoms.createMileageLabel());
        symptomsPanel.add(symptoms.createMileageInput());
        symptomsPanel.add(symptoms.createSubmitButton());

        //Create the panel for manage.
        //If active user is a manager
        if (connection.isAdmin(activeUser)) {
            JPanel managePanel = new JPanel();
            mainWindowPanel.addTab("Manage", null, managePanel, null);
            managePanel.setLayout(null);

            //Initialize the manage tab and its components.
            ManageTab manageTab = new ManageTab();
            JScrollPane employeeTablePanel = new JScrollPane();
            employeeTablePanel.setBounds(0, 11, 927, 817);
            managePanel.add(employeeTablePanel);
            employeeTablePanel.setViewportView(manageTab.createEmployeeTable());

            managePanel.add(manageTab.createRefreshListButton());
            managePanel.add(manageTab.createAddEmployeeButton());
            managePanel.add(manageTab.createDeleteEmployeeButton());
            managePanel.add(manageTab.createUpdateEmployeeButton());
            managePanel.add(manageTab.createExportPayrollButton());

        }

        //Create the panel for the employee portal.
        JPanel employeePortalPanel = new JPanel();
        mainWindowPanel.addTab("Employee Portal", null, employeePortalPanel, null);
        employeePortalPanel.setLayout(null);
        
        //Initialize the employee portal tab and its components.
        //Initialize the symptoms tab and its components.
        EmployeePortalTab employeePortalTab = new EmployeePortalTab();
        employeePortalPanel.add(employeePortalTab.createEmployeePortalTable());
        employeePortalPanel.add(employeePortalTab.createRefreshButton());
        employeePortalPanel.add(employeePortalTab.createUpdateInfoButton());
        employeePortalPanel.add(employeePortalTab.createChangePasswordButton());

        //Display the frame.
        frame.setVisible(true);
    }
}