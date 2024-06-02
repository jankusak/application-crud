package cz.skillmeaacademy;

import cz.skillmeaacademy.db.DBConntactService;
import cz.skillmeaacademy.db.HikariCPDataSource;
import cz.skillmeaacademy.service.CRUDManager;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        new CRUDManager().printOptions();
    }
}