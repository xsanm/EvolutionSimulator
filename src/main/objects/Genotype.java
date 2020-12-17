package objects;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Genotype {
    private final int LEN = 32;
    private final int[] genes = new int[32];

    //fully random
    public Genotype() {
        this.genes[0] = 0;
        this.genes[31] = 7;
        for (int i = 1; i < 31; i++) {
            this.genes[i] = generateRandom(0, 7);
        }
        Arrays.sort(this.genes);
        this.fixLackOfGenes();
    }

    public Genotype(int[] genesp) {
        this.genes[0] = 0;
        this.genes[31] = 7;
        for (int i = 1; i < 31; i++) {
            this.genes[i] = genesp[i];
        }
        Arrays.sort(this.genes);
    }

    public Genotype(int[] genesFromParentA, int[] genesFromParentB) {
        int div1 = generateRandom(1, 30);
        int div2;
        if (div1 < 15) {
            div2 = generateRandom(div1 + 1, 30);
        } else {
            div2 = generateRandom(1, div1 - 1);
        }

        int id1 = min(div1, div2);
        int id2 = max(div1, div2);

        //selecting 2 parts from parentA, one from parent B
        for (int i = 0; i < id1; i++) {
            this.genes[i] = genesFromParentA[i];
        }
        for (int i = id1; i < id2; i++) {
            this.genes[i] = genesFromParentB[i];
        }
        for (int i = id2; i < 32; i++) {
            this.genes[i] = genesFromParentA[i];
        }
        Arrays.sort(this.genes);

        this.fixLackOfGenes();
    }

    //fixing lack of genes
    private void fixLackOfGenes() {
        this.genes[0] = 0;
        this.genes[31] = 7;
        for (int i = 0; i < 31; i++) {
            if (this.genes[i + 1] - this.genes[i] > 1) {
                while (true) {
                    int id = generateRandom(1, 30);
                    if (this.genes[id] == this.genes[id + 1] || this.genes[id] == this.genes[id - 1]) {
                        this.genes[id] = this.genes[i + 1] - 1;
                        break;
                    }
                }
                Arrays.sort(this.genes);
                i = 0;
            }
        }
        Arrays.sort(this.genes);
    }

    private int generateRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public int getRandomGene() {
        return this.genes[generateRandom(0, 31)];
    }

    public Vector2D genToUnitVector(int gen) {
        Vector2D res = new Vector2D(0, 0);
        switch (gen) {
            case 0:
                res = new Vector2D(0, 1);
                break;
            case 1:
                res = new Vector2D(1, 1);
                break;
            case 2:
                res = new Vector2D(1, 0);
                break;
            case 3:
                res = new Vector2D(1, -1);
                break;
            case 4:
                res = new Vector2D(0, -1);
                break;
            case 5:
                res = new Vector2D(-1, -1);
                break;
            case 6:
                res = new Vector2D(-1, 0);
                break;
            case 7:
                res = new Vector2D(-1, 1);
                break;
        }
        return res;
    }

    @Override
    public String toString() {
        return Arrays.toString(genes);
    }

    public int[] getGenes() {
        return genes;
    }
}
