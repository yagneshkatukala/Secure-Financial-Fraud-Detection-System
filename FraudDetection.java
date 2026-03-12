package backend;

public class FraudDetection {

    public static String analyze(double amount,int frequency){

        if(amount > 90000 || frequency > 5)
            return "FRAUD";

        else if(amount > 50000)
            return "SUSPICIOUS";

        else
            return "SAFE";
    }
}