package bible.one;

public class Main {

    public static int yearsToReachAll(double population, double initial, int trainedPerCycle, int cycleYears) {
        double count = initial;
        int years = 0;

        while (count < population) {
            count = count + count * trainedPerCycle;
            years += cycleYears;
        }
        return years;
    }

    public static double requiredTrainingRate(double population, double initial, int cycleYears, int targetYears) {
        int cycles = targetYears / cycleYears;
        return Math.pow(population / initial, 1.0 / cycles) - 1;
    }

    public static void main(String[] args) {
        double population = 7700000000.0;
        double initial = 13;
        int trainedPerCycle = 2;
        int cycleYears = 3;

        int yearsNeeded = yearsToReachAll(population, initial, trainedPerCycle, cycleYears);

        int targetYears = 50;
        double rateRequired = requiredTrainingRate(population, initial, cycleYears, targetYears);

        
        System.out.println("#1) Years needed with 13 disciples training 2 every 3 years:");
        System.out.println("   → " + yearsNeeded + " years");

        System.out.println("#2) If we want to finish in 50 years:");
        System.out.println("   → Each disciple must train about " + String.format("%.2f", rateRequired) + " disciples every 3 years");
    }
}


