import java.util.Scanner;
import java.text.NumberFormat;

class Main {
    public static final byte MONTHS_IN_YEAR = 12;
    public static final byte PERCENT = 100;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int principal = (int)readNumber(scanner, "Principal ($1k - $1M): ", 1000, 1_000_000);
        float annualInterest = (float)readNumber(scanner, "Annual Interest Rate: ", 0, 30);
        byte years = (byte)readNumber(scanner, "Period (years): ", 0, 30);

        printMortgage(principal, annualInterest, years);
        printPayments(principal, annualInterest, years);
    }

    public static double readNumber(
        Scanner scanner,
        String prompt,
        int minInput,
        int maxInput){

            double numberRead = 0.0;

            while(true){
                System.out.print(prompt);
                numberRead = scanner.nextDouble();
                if (numberRead > minInput && numberRead < maxInput)
                    break;
                System.out.println("Enter a value between " + minInput + " and " + maxInput);
            }

            return numberRead;
    }

    public static double calculateMortgage(
        float annualInterest,
        byte years,
        int principal){

            float monthlyInterest = annualInterest / MONTHS_IN_YEAR / PERCENT;
            short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

            double monthlyPay = principal
                * (( monthlyInterest  * Math.pow((1 + monthlyInterest), numberOfPayments)) 
                / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1));

            return monthlyPay;
    }

    public static void printMortgage(
        int principal,
        float annualInterest,
        byte years){

            double monthlyPay = calculateMortgage(annualInterest, years, principal);
            String mortgageFormatted = NumberFormat.getCurrencyInstance().format(monthlyPay);
            System.out.println("\nMortgage\n--------\nMonthly Payment: " + mortgageFormatted + "\n");
    }

    public static double calculateBalance(
        int principal,
        float monthlyInterest,
        short numberOfPayments,
        short totalPaymentsPaid){

            double balance = principal
                    * (Math.pow((1 + monthlyInterest), numberOfPayments) 
                        - (Math.pow((1 + monthlyInterest),totalPaymentsPaid)))
                    / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1);
            
            return balance;
    }

    public static void printPayments(
        int principal,
        float annualInterest,
        byte years){

            float monthlyInterest = annualInterest / MONTHS_IN_YEAR / PERCENT;
            short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

            System.out.println("Payment Schedule\n----------------");

            for(short totalPaymentsPaid = 1; totalPaymentsPaid <= numberOfPayments; totalPaymentsPaid++){
                double balance = calculateBalance(principal, monthlyInterest, numberOfPayments, totalPaymentsPaid);
                String balanceFormatted = NumberFormat.getCurrencyInstance().format(balance);
                System.out.println(balanceFormatted);
            }
    }
}