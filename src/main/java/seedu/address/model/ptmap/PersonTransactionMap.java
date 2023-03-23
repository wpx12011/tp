package seedu.address.model.ptmap;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.exceptions.DuplicateTransactionException;

/**
 * Stores and manages the relationship between Person and Transactions.
 * Note that a transaction can only be mapped to at most one Person, while a Person can have many Transactions
 */
public class PersonTransactionMap {
    private final ObservableMap<Person, IndividualTransactionList> internalList = FXCollections.observableHashMap();

    private final ObservableMap<Person, IndividualTransactionList> internalUnmodifiableList =
            FXCollections.unmodifiableObservableMap(internalList);
    //private final Map<Person, IndividualTransactionList> personToTxnMap = new HashMap<>();

    public boolean containsPerson(Person toCheck) {
        requireNonNull(toCheck);
        internalList.values().stream().flatMap(Collections::stream).collect(Collectors.toList());
        return internalList.containsKey(toCheck);
    }

    public boolean containsTransaction(Transaction transaction) {
        requireNonNull(transaction);
        return internalList.containsValue();
    public void addPerson(Person person) {
        requireNonNull(person);
        if (containsPerson(person)) {
            throw new DuplicatePersonException();
        }
        IndividualTransactionList individualList = new IndividualTransactionList();
        internalList.put(person, individualList);
    }
    public void addTransaction(Transaction transaction) {
        requireNonNull(transaction);
        if (containsTransaction(transaction)) {
            throw new DuplicateTransactionException();
        }

        }
    }
}
