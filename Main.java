import java.util.Scanner;
import java.text.NumberFormat;

class Main {
    public static void main(String[] args){
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        Integer principal = 0;
        while (true) {
            System.out.print("Principal ($1k - $1M): ");
            principal = scanner.nextInt();
            if (principal < 1_000_000 && principal > 1000)
                break;
            System.out.println("Please enter a number between 1k and 1M");
        }

        float annualInterest = 0;
        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterest = scanner.nextFloat();
            if (annualInterest > 0 && annualInterest <= 30)
                break;
            System.out.println("Please enter a number between 0 and 30");
        }

        float monthlyInterest = annualInterest / MONTHS_IN_YEAR / PERCENT;

        byte years = 1;
        while (true) {
            System.out.print("Period (years): ");
            years = scanner.nextByte();
            if (years > 0 && years <= 30)
                break;
            System.out.println("Please enter a whole number between 0 and 30");
        }

        int numberOfPayments = years * MONTHS_IN_YEAR;

        Double monthlyPay = principal
                * (( monthlyInterest  * Math.pow((1 + monthlyInterest), numberOfPayments)) 
                / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1));

        NumberFormat mortgage = NumberFormat.getCurrencyInstance();
        String mortgageFormatted = mortgage.format(monthlyPay);

        System.out.print("Mortgage: " + mortgageFormatted);
        
        scanner.close();
    }
}