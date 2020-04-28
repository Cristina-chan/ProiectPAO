package repositories;

import exceptions.InexistentFileException;
import models.BankAccount;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileBankAccountRepository implements BankAccountRepository {

    private final String file = "BankAccounts.csv";

    @Override
    public void addBankAccount(BankAccount bankAccount) {
        try (PrintStream out =  new PrintStream(new FileOutputStream(file, true))) {
            out.println(bankAccount.getAccountNumber() + "," + bankAccount.getBalance());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editBankAccount(BankAccount bankAccount) {
        Path path = Paths.get(file);

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                // accountNumber, balance
                String[] attr = fileContent.get(i).split(",");
                if (attr[0].equals(bankAccount.getAccountNumber())) {
                    fileContent.set(i, bankAccount.getAccountNumber() + "," + bankAccount.getBalance());

                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<BankAccount> findAccountByNumber(String accountNumber) {
        Path path = Paths.get(file);
        BankAccount bankAccount = null;

        try {
            if (!Files.exists(path)) {
                throw new InexistentFileException();
            }

            var list = Files.readAllLines(path);
            for (String e : list) {
                // accountNumber, balance
                String[] attr = e.split(",");
                if (attr[0].equals(accountNumber)) {
                    bankAccount = new BankAccount();
                    bankAccount.setAccountNumber(attr[0]);
                    bankAccount.setBalance(Double.parseDouble(attr[1]));

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(bankAccount);
    }
}
