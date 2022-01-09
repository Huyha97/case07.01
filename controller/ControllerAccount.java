package controller;

import io.ReadAndWriteAccount;
import io.ReadAndWriteStaff;
import model.Account;
import model.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ControllerAccount {
    Scanner scanner = new Scanner(System.in);
    List<Account> accountlist = new ArrayList<>();

    public ControllerAccount() {
        accountlist = ReadAndWriteAccount.readAccount();
    }

    public Account signUpAccount() {
        String userName;
        while (true) {
            System.out.println("input UserName:");
            userName = scanner.nextLine();
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.]{8,20}$");
            if (pattern.matcher(userName).find()) {
                break;
            } else {
                System.err.println(
                                "===========================================================================================\n" +
                                "|| YOUR USERNAME MUST HAS:                                                                 ||\n" +
                                "|| Username must be 8 to 20 characters in length                                           ||\n" +
                                "|| Username can only contain alphanumeric characters, numbers, underscore (_) and dot (.)  ||\n" +
                                "|| Username cannot start with underscore and dot                                           ||\n" +
                                "============================================================================================\n");
            }
        }
        String passWord;
        while (true) {
            System.out.println("Input PassWord:");
            passWord = scanner.nextLine();
            Pattern pattern = Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
            if (pattern.matcher(passWord).find()) {
                break;
            } else {
                System.err.println(
                                "==========================================================================================\n" +
                                "||    YOUR PASSWORD MUST HAS:                                                            ||\n" +
                                "|| At least 8 chars                                                                      ||\n" +
                                "|| Contains at least one digit                                                           ||\n" +
                                "|| Contains at least one lower alpha char and one upper alpha char                       ||\n" +
                                "||Contains at least one char within a set of special chars (@#%$^ etc.)                  ||\n" +
                                "|| Does not contain space, tab, etc.                                                     ||\n" +
                                "==========================================================================================");
            }
        }
        return new Account(userName, passWord);

    }

    public Account signInAdmin() {
        System.out.println("Input UserName: ");
        String userName = scanner.nextLine();
        System.out.println("Input PassWord: ");
        String passWord = scanner.nextLine();
        return new Account(userName, passWord);
    }

    public void addAccount(Account account) {
        for (Account ac : accountlist) {
            if (ac.getUserName().equals(account.getUserName())) {
                return;
            }
        }
        accountlist.add(account);
        ReadAndWriteAccount.writeAccount(accountlist);
    }

    public List<Account> findAll() {
        return accountlist;
    }

    public boolean signIn(Account account) {
        for (Account acc : accountlist) {
            if (acc.getUserName().equals(account.getUserName()) && acc.getPassWord().equals(account.getPassWord())) {
                return true;
            }
        }
        return false;
    }

    public boolean loginAdmin(Account account) {
        if (account.getUserName().equals("admin") && account.getPassWord().equals("admin")) {
            return true;
        }
        return false;
    }

    public void displayAccount() {
        if (accountlist.isEmpty()) {
            System.err.println("Empty!!!");
        }
        for (Account acc : accountlist) {
            if (accountlist.size() > 0) {
                System.out.println(acc.toString());
            }
        }
    }

    public int findIndexAccountDelete() {
        String userName = scanner.nextLine();
        for (int i = 0; i < accountlist.size(); i++) {
            if (accountlist.get(i).getUserName().equals(userName)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteAccount() {
        System.out.println("which account do you wan to delete?");
        int index = findIndexAccountDelete();
        if (index >= 0) {
            accountlist.remove(index);
            System.out.println("DELETE SUCCESS!!");
        } else {
            System.err.println("====================================");
            System.err.println("|  THIS ACCOUNT DOES NOT EXIT !!!   |");
            System.err.println("====================================");
            deleteAccount();
        }
        ReadAndWriteAccount.writeAccount(accountlist);
    }

    public void edit(int index, Account account) {
        accountlist.set(index, account);
        ReadAndWriteAccount.writeAccount(accountlist);
    }

    public int findIndexAccount() {
        String userName = scanner.nextLine();
        for (int i = 0; i < accountlist.size(); i++) {
            if (accountlist.get(i).getUserName().equals(userName)) {
                return i;
            }
        }
        return -1;
    }

    public Account updateAccount() {
        System.out.println("Input new UserName :");
        String userName = scanner.nextLine();
        System.out.println("Input new PassWord :");
        String passWord = scanner.nextLine();
        System.out.println("UPDATE SUCCESS!!");
        return new Account(userName, passWord);
    }

}