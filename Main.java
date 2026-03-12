package backend;

import java.util.*;
import java.io.*;

public class Main {

    // =====================================================
    // MODEL CLASS (Transaction → List ADT Node)
    // =====================================================
    static class Transaction {

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

        public String toString(){
            return account+" | "+card+" | "+amount+" | "+status;
        }
    }

    // =====================================================
    // FRAUD DETECTION ALGORITHM
    // =====================================================
    static class FraudDetection {

        static String analyze(double amount,int frequency){

            if(amount > 90000 || frequency > 5)
                return "FRAUD";

            else if(amount > 50000)
                return "SUSPICIOUS";

            else
                return "SAFE";
        }
    }

    // =====================================================
    // FILE HANDLER (DATA STORAGE)
    // =====================================================
    static class FileHandler {

        static void save(Transaction t){

            try{
                BufferedWriter bw =
                        new BufferedWriter(
                        new FileWriter("transactions.txt",true));

                bw.write(t.toString());
                bw.newLine();
                bw.close();

            }catch(Exception e){
                System.out.println("File Error");
            }
        }
    }

    // =====================================================
    // LIST ADT → Linked List
    // =====================================================
    static LinkedList<Transaction> list = new LinkedList<>();


    // =====================================================
    // SEARCHING ALGORITHMS
    // =====================================================

    // Linear Search O(n)
    static Transaction linearSearch(String acc){
        for(Transaction t:list){
            if(t.account.equals(acc))
                return t;
        }
        return null;
    }

    // Binary Search O(log n)
    static Transaction binarySearch(String acc){

        int low=0,high=list.size()-1;

        while(low<=high){
            int mid=(low+high)/2;
            int cmp=list.get(mid).account.compareTo(acc);

            if(cmp==0) return list.get(mid);
            else if(cmp<0) low=mid+1;
            else high=mid-1;
        }
        return null;
    }

    // =====================================================
    // SORTING ALGORITHMS
    // =====================================================

    // Bubble Sort
    static void bubbleSort(){
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.size()-i-1;j++){
                if(list.get(j).amount > list.get(j+1).amount){
                    Collections.swap(list,j,j+1);
                }
            }
        }
    }

    // Insertion Sort
    static void insertionSort(){
        for(int i=1;i<list.size();i++){
            Transaction key=list.get(i);
            int j=i-1;

            while(j>=0 && list.get(j).amount>key.amount){
                list.set(j+1,list.get(j));
                j--;
            }
            list.set(j+1,key);
        }
    }

    // Selection Sort
    static void selectionSort(){
        for(int i=0;i<list.size();i++){
            int min=i;
            for(int j=i+1;j<list.size();j++){
                if(list.get(j).amount < list.get(min).amount)
                    min=j;
            }
            Collections.swap(list,i,min);
        }
    }

    // =====================================================
    // MAIN PROGRAM
    // =====================================================
    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        // Queue ADT
        Queue<Transaction> queue=new LinkedList<>();

        // Stack ADT
        Stack<Transaction> fraudStack=new Stack<>();

        // Hashing
        HashMap<String,Transaction> map=new HashMap<>();

        // Priority Queue (Binary Heap)
        PriorityQueue<Transaction> pq=
                new PriorityQueue<>(
                        (a,b)->Double.compare(b.amount,a.amount));

        System.out.println("=== Secure Financial Fraud Detection System ===");

        System.out.print("Enter number of transactions: ");
        int n=sc.nextInt();

        for(int i=0;i<n;i++){

            System.out.print("Account: ");
            String acc=sc.next();

            System.out.print("Card: ");
            String card=sc.next();

            System.out.print("Amount: ");
            double amt=sc.nextDouble();

            System.out.print("Frequency: ");
            int freq=sc.nextInt();

            String status=FraudDetection.analyze(amt,freq);

            Transaction t=new Transaction(acc,card,amt,status);

            // List ADT
            list.add(t);

            // Queue
            queue.add(t);

            // HashMap
            map.put(acc,t);

            // Priority Queue
            pq.add(t);

            // Stack (Fraud alerts)
            if(status.equals("FRAUD"))
                fraudStack.push(t);

            // File Save
            FileHandler.save(t);
        }

        // ================= QUEUE PROCESSING =================
        System.out.println("\nQueue Processing:");
        while(!queue.isEmpty())
            System.out.println(queue.remove());

        // ================= SORTING =================
        bubbleSort();

        System.out.println("\nSorted Transactions:");
        for(Transaction t:list)
            System.out.println(t);

        // ================= SEARCHING =================
        System.out.print("\nSearch Account: ");
        String key=sc.next();

        Transaction res=linearSearch(key);

        if(res!=null)
            System.out.println("Found: "+res);
        else
            System.out.println("Not Found");

        // ================= STACK =================
        System.out.println("\nFraud Alerts:");
        while(!fraudStack.isEmpty())
            System.out.println(fraudStack.pop());

        // ================= PRIORITY QUEUE =================
        System.out.println("\nHighest Transactions:");
        while(!pq.isEmpty())
            System.out.println(pq.remove());
    }
}