public class EvolutionSimulator {
    public static void main(String[] args) {
        System.out.println("Hello");

        Genotype g1 = new Genotype();
        Genotype g2 = new Genotype();
        Genotype g3 = new Genotype(g1.getGenes(), g2.getGenes());
        System.out.println(g1);
        System.out.println(g2);
        System.out.println(g3);
        //for(int i = 0; i < 10; i++) System.out.println(g3.getRandomGene());


        WorldMap mapa = new WorldMap(5, 5);
        mapa.place(new Animal(new Vector2D(2, 3), g1, 1, (IPositionChangeObserver) mapa));
        mapa.place(new Animal(new Vector2D(1, 1), g1, 2, (IPositionChangeObserver) mapa));
        mapa.place(new Animal(new Vector2D(1, 1), g1, 3, (IPositionChangeObserver) mapa));
        mapa.addGrass();
        System.out.println(mapa.objectAt(new Vector2D(1, 1)).get(0));
        //System.out.println(mapa);


        DataManager dataManager = new DataManager();




        DrawAnimalsOld zoo = new DrawAnimalsOld(5, 5);

        SimulationEngine simulationEngine = new SimulationEngine(mapa);
        //SimulationEngine simulationEngine = new SimulationEngine(mapa);
        //simulationEngine.run(zoo);



        MainWindow m = new MainWindow(simulationEngine);


    }
}
