package co.id.controller;

import co.id.model.Customer;
import co.id.repository.CustomerPagingRepository;
import co.id.repository.CustomerRepository;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

import java.util.List;

public class CustomerListVM {

    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * Wire component
     *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    @Wire("#textboxFilter")
    private Textbox textboxFilter;

    @Wire("#listBoxCustomer")
    private Listbox listBoxCustomer;

    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * Service yang dibutuhkan sesuai bisnis proses
     *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    private Customer customer;
    private List<Customer> customers;
    @WireVariable
    private CustomerRepository customerRepository;
    @WireVariable
    private CustomerPagingRepository customerPagingRepository;
    private ListitemRenderer<Customer> listitemRenderer;

    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * Inisialize Methode MVVM yang pertama kali dijalankan
     *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    @AfterCompose
    public void setupComponents(@ContextParam(ContextType.VIEW) Component component,
        @ExecutionArgParam("object") Object object,
        @ExecutionArgParam("customer") Customer customer) {

        Selectors.wireComponents(component, this, false);

        /*doPrepareList();
        refreshPageList(startPageNumber);*/

        /*customers = new ArrayList<Customer>();
        customers.add(new Customer(1L,"tes","","",new Date(),"","",""));*/

        customers = customerPagingRepository.findAll();
        listitemRenderer = new CustomerListItemRenderer(customerRepository);
    }

    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * Function CRUD Event
     *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    @Command
    public void doNew(){
        final ListModelList<Customer> listModelListCustomer = (ListModelList) listBoxCustomer.getModel();
        listModelListCustomer.add(0, new Customer());
    }

    @GlobalCommand
    @NotifyChange("customers")
    public void refreshAfterSaveOrUpdate(){
        customers = customerPagingRepository.findAll();
    }

    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * Getter Setter
     *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ListitemRenderer<Customer> getListitemRenderer() {
        return listitemRenderer;
    }

    public void setListitemRenderer(ListitemRenderer<Customer> listitemRenderer) {
        this.listitemRenderer = listitemRenderer;
    }
}