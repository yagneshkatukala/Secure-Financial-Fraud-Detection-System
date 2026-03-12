package backend;

public class Transaction {

    String account;
    String card;
    double amount;
    String status;

    public Transaction(String account,String card,double amount,String status){
        this.account = account;
        this.card = card;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString(){
        return account+" | "+card+" | "+amount+" | "+status;
    }
}