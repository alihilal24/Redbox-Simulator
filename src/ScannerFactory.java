import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
Ali Hilal, CS 2336, Project 4, Redbox
 */

/**
 * ScannerFactory
 */
public abstract class ScannerFactory {

    private static Scanner _scannerObject;

    //Creates scanner for Inventory.txt file
    public static Scanner InventoryGetScannerInstance()
    {
        if(_scannerObject == null)
        {
            File inventoryFile = new File("Inventory.txt");
            try {
                _scannerObject = new Scanner(inventoryFile);
            } catch (FileNotFoundException e) {
                System.out.println("Inventory file not found, program quitting....");
                System.exit(0);
            }
        }

        return _scannerObject;
    }
    //Creates scanner for Transaction.txt file
    public static Scanner TransactionGetScannerInstance()
    {
        if(_scannerObject == null)
        {
            File transactionFile = new File("Transaction.txt");
            try {
                _scannerObject = new Scanner(transactionFile);
            } catch (FileNotFoundException e) {
                System.out.println("Transaction file not found, program quitting....");
                System.exit(0);
            }
        }

        return _scannerObject;
    }

    public static void CloseScannerInstance()
    {
        _scannerObject.close();
        _scannerObject = null;
    }
}