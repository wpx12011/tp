package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<Transaction> getTransactionList();
}
