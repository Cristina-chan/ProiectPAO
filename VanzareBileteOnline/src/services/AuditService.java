package services;

import models.Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;

public class AuditService {

    private final String file = "Audit.csv";

    public void addAction(String name, Timestamp timestamp) {
        try (PrintStream out = new PrintStream(new FileOutputStream(file, true))) {
            out.println(name + "," + timestamp.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AuditService getInstance() {
        return AuditService.SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static AuditService INSTANCE = new AuditService();
    }
}
