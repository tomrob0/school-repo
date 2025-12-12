package bible.two;
public class Main {
    private static final int MAX_AGE = 72;
    private static final int TRAINING_START_AGE = 18;
    private static final int REPRODUCTION_AGE = 30;
    private static final int TRAINING_CYCLE_YEARS = 3;
    private static final int TRAINED_PER_CYCLE = 2;
    private static final double INITIAL_DISCIPLES = 13;
    private static final double INITIAL_POPULATION = 7_700_000_000.0;
    
    public static int simulateDiscipleshipWithDemographics() {
        double disciples = INITIAL_DISCIPLES;
        double population = INITIAL_POPULATION;
        int years = 0;
        
        // Track disciples by age groups to handle deaths and new trainers
        double activeDisciples = disciples; // Disciples who can train (age 18-72)
        
        while (disciples < population) {
            years += TRAINING_CYCLE_YEARS;
            
            // Calculate population growth
            // Every 30 years, population increases (couples have 1 baby)
            double birthRate = 1.0 / REPRODUCTION_AGE; // Per year birth rate
            double populationGrowth = population * birthRate * TRAINING_CYCLE_YEARS;
            population += populationGrowth;
            
            // Calculate death rate (people die at 72)
            double deathRate = 1.0 / MAX_AGE;
            double populationDeaths = population * deathRate * TRAINING_CYCLE_YEARS;
            population -= populationDeaths;
            
            // New disciples trained by active disciples
            double newDisciples = activeDisciples * TRAINED_PER_CYCLE;
            disciples += newDisciples;
            
            // Update active disciples (those aged 18-72)
            // Assume disciples follow same death pattern
            double discipleDeaths = disciples * deathRate * TRAINING_CYCLE_YEARS;
            disciples -= discipleDeaths;
            
            // New disciples who reach training age (18)
            // Some of the new disciples will reach training age
            activeDisciples = disciples * ((MAX_AGE - TRAINING_START_AGE) / (double)MAX_AGE);
            
            // Prevent negative values
            if (disciples < 0) disciples = 0;
            if (population < 0) population = 0;
        }
        
        return years;
    }
    
    public static int simulateSimplifiedModel() {
        double disciples = INITIAL_DISCIPLES;
        double population = INITIAL_POPULATION;
        int years = 0;
        
        // Simplified: Population grows, disciples grow faster
        while (disciples < population) {
            years += TRAINING_CYCLE_YEARS;
            
            // Population growth: net growth considering births and deaths
            // Birth rate: 1 baby per couple at age 30 = ~1.67% per year
            // Death rate: 1/72 = ~1.39% per year
            // Net growth: ~0.28% per year
            double netGrowthRate = (1.0 / REPRODUCTION_AGE) - (1.0 / MAX_AGE);
            population *= (1 + netGrowthRate * TRAINING_CYCLE_YEARS);
            
            // Disciple growth: Each active disciple trains 2 new disciples every 3 years
            // Active disciples: those aged 18-72 (75% of disciples)
            double activeRatio = (MAX_AGE - TRAINING_START_AGE) / (double)MAX_AGE;
            double activeDisciples = disciples * activeRatio;
            
            // New disciples trained
            double newDisciples = activeDisciples * TRAINED_PER_CYCLE;
            disciples += newDisciples;
            
            // Account for disciple deaths
            disciples *= (1 - (1.0 / MAX_AGE) * TRAINING_CYCLE_YEARS);
        }
        
        return years;
    }
    
    public static void main(String[] args) {

        
        int yearsNeeded = simulateSimplifiedModel();
        
        System.out.println("\nRESULT:");
        System.out.println("  Years needed to make every human a disciple: " + yearsNeeded + " years");
       
    }
}
