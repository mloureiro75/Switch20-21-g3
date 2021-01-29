package switchtwentytwenty.project.domain.services;

import switchtwentytwenty.project.domain.DTOs.output.TransactionDataDTO;
import switchtwentytwenty.project.domain.model.accounts.Account;
import switchtwentytwenty.project.domain.model.categories.StandardCategory;
import switchtwentytwenty.project.domain.sandbox.Transaction;
import switchtwentytwenty.project.domain.utils.TransferenceDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionService {

    public boolean registerPaymentMyCashAccount(Account targetAccount, StandardCategory category, TransferenceDTO transferenceDTO) { // TODO: ALTERAR PARA GENERAL CATEGORY
        try {
            if (targetAccount.hasEnoughMoneyForTransaction(transferenceDTO.getTransferredValue())) {
                return targetAccount.registerTransaction(targetAccount, category, transferenceDTO);
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * A method that returns a list of movements registered in an account that occurred between two given dates.
     * @param anAccount given account
     * @param startDate first date
     * @param endDate last date
     * @return the list of transactions between said dates
     */
    public List<TransactionDataDTO> createListOfMovementsBetweenDates(Account anAccount, Date startDate, Date endDate) {
        List<Transaction> listOfMovements = anAccount.getListOfMovements();
        List<TransactionDataDTO> listOfMovementsBetweenDates = new ArrayList<>();

        for (Transaction transaction : listOfMovements)
            if (checkIfMovementBetweenDates(transaction, startDate, endDate)) {
                TransactionDataDTO transactionDTO = new TransactionDataDTO(transaction.getTransactionData());
                listOfMovementsBetweenDates.add(transactionDTO);
            }

        return listOfMovementsBetweenDates;
    }

    /**
     * A method that returns true if a given transaction occurred between two given dates
     * @param aTransaction given transaction
     * @param startDate first date
     * @param endDate last date
     * @return true if between given dates, else false
     */
    private boolean checkIfMovementBetweenDates(Transaction aTransaction, Date startDate, Date endDate) {

        // Switch dates if endDate is earlier than startDate
        if (startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        boolean isBetweenDates = false;
        Date transactionDate = aTransaction.getTransactionDate();

        if (transactionDate.after(startDate) && transactionDate.before(endDate)) {
            isBetweenDates = true;
        }
        return isBetweenDates;
    }

}
