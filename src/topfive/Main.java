package topfive;
import java.util.*;

public class Main {

    // i used ai to create the random data and names used to find the top peformers
    public static void main(String[] args) {
        // Create list of 20 salesmen with random revenue
        String[] names = {
                "Alex", "Brooke", "Chris", "Drew", "Evan",
                "Faith", "Gina", "Hunter", "Ivan", "Jade",
                "Kyle", "Liam", "Morgan", "Nina", "Owen",
                "Piper", "Quinn", "Riley", "Shane", "Taylor"
        };

        Random rand = new Random();

        // Store salesman objects
        List<Salesman> salesmen = new ArrayList<>();

        for (String n : names) {
            double revenue = 5000 + rand.nextDouble() * 45000; // Between 5k–50k
            salesmen.add(new Salesman(n, revenue));
        }

        List<Salesman> top5 = TopFive(salesmen);
        System.out.println("Top 5 Salesmen by Revenue:");
        for (Salesman s : top5) {
            System.out.printf("%s - $%.2f%n", s.getName(), s.getRevenue());
        }
    }

    
    public static List<Salesman> TopFive(List<Salesman> salesmen) {

   
        Salesman[] top = new Salesman[5];

        for (Salesman s : salesmen) {
            insertIntoTopFive(top, s);
        }

     
        return Arrays.asList(top);
    }

  
    private static void insertIntoTopFive(Salesman[] top, Salesman candidate) {

        for (int i = 0; i < top.length; i++) {

            if (top[i] == null || candidate.getRevenue() > top[i].getRevenue()) {

           
                for (int j = top.length - 1; j > i; j--) {
                    top[j] = top[j - 1];
                }

                top[i] = candidate;
                break;
            }
        }
    }
}


class Salesman {
    private String name;
    private double revenue;

    public Salesman(String name, double revenue) {
        this.name = name;
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }
}