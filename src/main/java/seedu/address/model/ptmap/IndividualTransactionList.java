package seedu.address.model.ptmap;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.UpdatedTransactionComparator;
import seedu.address.model.transaction.exceptions.DuplicateTransactionException;
import seedu.address.model.transaction.exceptions.TransactionNotFoundException;

public class IndividualTransactionList implements Iterable<Transaction> {
    private final ObservableList<Transaction> internalList = FXCollections.observableArrayList();
    private final ObservableList<Transaction> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    /**
     * Returns true if the list contains an equivalent Transaction as the given argument.
     */
    public boolean contains(Transaction toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTransaction);
    }

    /**
     * Adds a transaction to the list.
     * The transaction must not already exist in the list.
     */
    public void add(Transaction toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTransactionException();
        }
        internalList.add(toAdd);
        internalList.sorted(new UpdatedTransactionComparator());
    }

    /**
     * Replaces the transaction {@code target} in the list with {@code editedTransaction}.
     * {@code target} must exist in the list.
     * The transaction identity of {@code editedTransaction} must not be the same as another existing transaction
     * in the list.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireAllNonNull(target, editedTransaction);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TransactionNotFoundException();
        }

        if (!target.isSameTransaction(editedTransaction) && contains(editedTransaction)) {
            throw new DuplicateTransactionException();
        }

        internalList.set(index, editedTransaction);
        internalList.sorted(new UpdatedTransactionComparator());
    }

    /**
     * Removes the equivalent transaction from the list.
     * The transaction must exist in the list.
     */
    public void remove(Transaction toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TransactionNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Transaction> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Transaction> iterator() {
        return internalList.iterator();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IndividualTransactionList // instanceof handles nulls
                && internalList.equals(((IndividualTransactionList) other).internalList));
    }
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code transactions} contains only unique transactions.
     */
    private boolean transactionsAreUnique(List<Transaction> transactions) {
        for (int i = 0; i < transactions.size() - 1; i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                if (transactions.get(i).isSameTransaction(transactions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
