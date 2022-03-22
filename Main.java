import java.util.Scanner;
import java.text.NumberFormat;

class Main {
    public static void main(String[] args){
        int principal = (int)readNumber("Principal ($1k - $1M): ", 1000, 1_000_000);
        float annualInterest = (float)readNumber("Annual Interest Rate: ", 0, 30);
        byte years = (byte)readNumber("Period (years): ", 0, 30);

        double monthlyPay = calculateMortgage(annualInterest, years, principal);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(monthlyPay);
        System.out.print("Mortgage: " + mortgageFormatted);
    }

    public static double readNumber(
        String prompt,
        Integer minInput,
        Integer maxInput){

            Scanner scanner = new Scanner(System.in);
            Double numberRead = 0.0;

            while(true) {
                System.out.print(prompt);
                numberRead = scanner.nextDouble();
                if (numberRead > minInput && numberRead < maxInput)
                    break;
                System.out.println("Enter a value between " + minInput + " and " + maxInput);
            }

            scanner.close();
            return numberRead;
    }

    public static double calculateMortgage(
        float annualInterest,
        byte years,
        Integer principal){
        
            final byte MONTHS_IN_YEAR = 12;
            final byte PERCENT = 100;

            float monthlyInterest = annualInterest / MONTHS_IN_YEAR / PERCENT;
            short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

            Double monthlyPay = principal
                * (( monthlyInterest  * Math.pow((1 + monthlyInterest), numberOfPayments)) 
                / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1));

            return monthlyPay;
    }
}