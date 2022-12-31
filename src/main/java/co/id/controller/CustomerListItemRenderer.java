package co.id.controller;

import co.id.model.Customer;
import co.id.repository.CustomerRepository;
import org.zkoss.bind.BindUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class CustomerListItemRenderer implements ListitemRenderer<Customer> {

    private CustomerRepository customerRepository;

    public CustomerListItemRenderer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void render(Listitem listitem, Customer customer, int i) throws Exception {
        Listcell listcell;

        final Button buttonSave = new Button();
        buttonSave.setLabel("Save");

        final Button buttonEdit = new Button();
        buttonEdit.setLabel("Edit");

        final Button buttonDelete = new Button();
        buttonDelete.setLabel("Delete");

        final Button buttonCancel = new Button();
        buttonCancel.setLabel("Cancel");

        final Label labelFristName = new Label();
        final Label labelLastName = new Label();
        final Label labelCity = new Label();
        final Label labelDOB = new Label();
        final Label labelAddress = new Label();
        final Label labelGender = new Label();
        final Label labelHobby = new Label();

        final Textbox textboxFristName = new Textbox();
        final Textbox textboxLastName = new Textbox();
        final Textbox textboxCity = new Textbox();
        final Datebox dateboxDOB = new Datebox();
        final Textbox textboxAddress = new Textbox();
        final Combobox comboboxGender = new Combobox();
        final Combobox comboboxHobby = new Combobox();

        listcell = new Listcell();
            textboxFristName.setParent(listcell);
            labelFristName.setParent(listcell);
        listcell.setParent(listitem);

        listcell = new Listcell();
            textboxLastName.setParent(listcell);
            labelLastName.setParent(listcell);
        listcell.setParent(listitem);

        listcell = new Listcell();
            textboxCity.setParent(listcell);
            labelCity.setParent(listcell);
        listcell.setParent(listitem);

        listcell = new Listcell();
            dateboxDOB.setParent(listcell);
            labelDOB.setParent(listcell);
        listcell.setParent(listitem);

        listcell = new Listcell();
            textboxAddress.setParent(listcell);
            labelAddress.setParent(listcell);
        listcell.setParent(listitem);

        listcell = new Listcell();
            comboboxGender.setParent(listcell);
            labelGender.setParent(listcell);
        listcell.setParent(listitem);

        listcell = new Listcell();
            comboboxHobby.setParent(listcell);
            labelHobby.setParent(listcell);
        listcell.setParent(listitem);

        listcell = new Listcell();
            buttonEdit.setParent(listcell);
            buttonSave.setParent(listcell);
            buttonCancel.setParent(listcell);
            buttonDelete.setParent(listcell);
        listcell.setParent(listitem);

        if(customer.getId() == 0L){
            buttonEdit.setVisible(false);
            buttonDelete.setVisible(false);

            comboboxGender.setReadonly(true);
            comboboxHobby.setReadonly(true);

            ListModelList listModelListGender = new ListModelList(Arrays.asList(new String[] {"Laki - laki", "Perempuan"}));
            comboboxGender.setModel(listModelListGender);

            ListModelList listModelListHobby = new ListModelList(Arrays.asList(new String[] {"Music", "Read Book", "Other"}));
            comboboxHobby.setModel(listModelListHobby);
        }else{
            Date date = customer.getDob();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String dob = dateFormat.format(date);

            comboboxGender.setReadonly(true);
            comboboxHobby.setReadonly(true);

            ListModelList listModelListGender = new ListModelList(Arrays.asList(new String[] {"Laki - laki", "Perempuan"}));
            comboboxGender.setModel(listModelListGender);

            ListModelList listModelListHobby = new ListModelList(Arrays.asList(new String[] {"Music", "Read Book", "Other"}));
            comboboxHobby.setModel(listModelListHobby);

            buttonSave.setVisible(false);
            buttonCancel.setVisible(false);

            labelFristName.setValue(customer.getFristname());
            labelLastName.setValue(customer.getLastname());
            labelCity.setValue(customer.getCity());
            labelDOB.setValue(dob);
            labelAddress.setValue(customer.getAddress());
            labelGender.setValue(customer.getGender());
            labelHobby.setValue(customer.getHobby());

            textboxFristName.setVisible(false);
            textboxLastName.setVisible(false);
            textboxCity.setVisible(false);
            dateboxDOB.setVisible(false);
            textboxAddress.setVisible(false);
            comboboxGender.setVisible(false);
            comboboxHobby.setVisible(false);
        }

        buttonSave.addEventListener(Events.ON_CLICK, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                if(customer.getId() == 0L){
                    customer.setFristname(textboxFristName.getValue());
                    customer.setLastname(textboxLastName.getValue());
                    customer.setCity(textboxCity.getValue());
                    customer.setDob(dateboxDOB.getValue());
                    customer.setAddress(textboxAddress.getValue());
                    customer.setGender(comboboxGender.getValue());
                    customer.setHobby(comboboxHobby.getValue());

                    customerRepository.save(customer);

                    BindUtils.postGlobalCommand(null, null, "refreshAfterSaveOrUpdate", null);
                }else{
                    customer.setFristname(textboxFristName.getValue());
                    customer.setLastname(textboxLastName.getValue());
                    customer.setCity(textboxCity.getValue());
                    customer.setDob(dateboxDOB.getValue());
                    customer.setAddress(textboxAddress.getValue());
                    customer.setGender(comboboxGender.getValue());
                    customer.setHobby(comboboxHobby.getValue());

                    customerRepository.save(customer);

                    BindUtils.postGlobalCommand(null, null, "refreshAfterSaveOrUpdate", null);
                }
            }
        });

        buttonEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                buttonEdit.setVisible(false);
                buttonDelete.setVisible(false);
                buttonSave.setVisible(true);
                buttonCancel.setVisible(true);

                textboxFristName.setVisible(true);
                textboxLastName.setVisible(true);
                textboxCity.setVisible(true);
                dateboxDOB.setVisible(true);
                textboxAddress.setVisible(true);
                comboboxGender.setVisible(true);
                comboboxHobby.setVisible(true);

                labelFristName.setVisible(false);
                labelLastName.setVisible(false);
                labelCity.setVisible(false);
                labelDOB.setVisible(false);
                labelAddress.setVisible(false);
                labelGender.setVisible(false);
                labelHobby.setVisible(false);

                textboxFristName.setValue(customer.getFristname());
                textboxLastName.setValue(customer.getLastname());
                textboxCity.setValue(customer.getCity());
                dateboxDOB.setValue(customer.getDob());
                textboxAddress.setValue(customer.getAddress());
                comboboxGender.setValue(customer.getGender());
                comboboxHobby.setValue(customer.getHobby());
            }
        });

        buttonDelete.addEventListener(Events.ON_CLICK, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                Messagebox.show("Do you really want to remove item?", "Confirm", Messagebox.OK | Messagebox.CANCEL, Messagebox.EXCLAMATION, new EventListener() {
                    public void onEvent(Event event) throws Exception {
                        if (((Integer) event.getData()).intValue() == Messagebox.OK) {

                            customerRepository.delete(customer);

                            BindUtils.postGlobalCommand(null, null, "refreshAfterSaveOrUpdate", null);
                        }else{
                            return;
                        }
                    }
                });
            }
        });

        buttonCancel.addEventListener(Events.ON_CLICK, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                BindUtils.postGlobalCommand(null, null, "refreshAfterSaveOrUpdate", null);
            }
        });
    }
}