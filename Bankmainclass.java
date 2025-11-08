package corejava;
import java.util.Scanner;

class countdigits {
    int count = 0;

    int getcount(int n) {
        while (n > 0) {
            count++;
            n = n / 10;
        }
        return count;
    }
}

class checkpinno {
    boolean verify(int pinno) {
        switch (pinno) {
            case 1111:
            case 2222:
            case 3333:
                return true;
            default:
                return false;
        }
    }
}

class withdraw {
    void wdraw(int amt) {
        System.out.println("Amount withdrawn: " + amt);
        System.out.println("Balance amount: " + (1000 - amt));
        System.out.println("Transaction completed successfully\n");
    }
}

public class Bankmainclass {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int balance = 1000;

        while (true) { // loop for pin retry
            System.out.println("Enter pinno:");
            int pinno = s.nextInt();

            countdigits cd = new countdigits();
            int c = cd.getcount(pinno);

            if (c == 4) {
                checkpinno cpn = new checkpinno();
                boolean validPin = cpn.verify(pinno);

                if (validPin) {
                    System.out.println("✅ Correct pinno");

                    // Loop for amount input
                    while (true) {
                        System.out.println("Enter the amount to withdraw:");
                        int amt = s.nextInt();

                        if (amt > 0 && amt % 100 == 0) {
                            if (amt <= balance) {
                                withdraw wds = new withdraw();
                                wds.wdraw(amt);
                                balance -= amt;

                                System.out.println("Do you want another transaction? (yes/no)");
                                String choice = s.next();

                                if (choice.equalsIgnoreCase("no")) {
                                    System.out.println("Thank you for using our ATM!");
                                    s.close();
                                    return;
                                } else {
                                    break; // ask for pin again for new transaction
                                }

                            } else {
                                System.out.println("❌ Insufficient funds. Try again.\n");
                            }
                        } else {
                            System.out.println("❌ Invalid amount. Try again.\n");
                        }
                    }
                } else {
                    System.out.println("❌ Incorrect pinno. Try again.\n");
                }
            } else {
                System.out.println("❌ Invalid pinno length (must be 4 digits). Try again.\n");
            }
        }
    }
}
