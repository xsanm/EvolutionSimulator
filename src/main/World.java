public class World {
    public static void main(String[] args) {
        System.out.println("Hello Evolution Simulator");
        Genotype g1 = new Genotype();
        Genotype g2 = new Genotype();
        Genotype g3 = new Genotype(g1.getGenes(), g2.getGenes());
        System.out.println(g1);
        System.out.println(g2);
        System.out.println(g3);
        //for(int i = 0; i < 10; i++) System.out.println(g3.getRandomGene());


        WorldMap mapa = new WorldMap(5, 5);
        mapa.place(new Animal(new Vector2D(2, 3), g1, 0.5));
        mapa.place(new Animal(new Vector2D(1, 1), g1, 0.5));
        mapa.place(new Animal(new Vector2D(1, 1), g1, 1.5));
        mapa.addGrass();
        System.out.println(mapa.objectAt(new Vector2D(1, 1)).get(0));
        System.out.println(mapa);
    }
}
