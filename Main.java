import java.util.Scanner;
import java.text.NumberFormat;

class Main {
    public static void main(String[] args){
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Principal: ");
        Integer principal = scanner.nextInt();

        System.out.print("Annual Interest Rate: ");
        float annualInterest = scanner.nextFloat();
        float monthlyInterest = annualInterest / MONTHS_IN_YEAR / PERCENT;

        System.out.print("Period (Years): ");
        byte years = scanner.nextByte();
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