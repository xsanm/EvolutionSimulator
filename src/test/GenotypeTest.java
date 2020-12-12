import objects.Genotype;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GenotypeTest {

    private final int TESTS = 5000;

    @Test
    public void testConstructor1() {
        for (int i = 0; i < TESTS; i++) {
            Genotype g1 = new Genotype();
            int cnt[] = new int[8];
            int genes[] = g1.getGenes();
            for (int j = 0; j < 8; j++) cnt[j] = 0;
            for (int j = 0; j < 31; j++) {
                cnt[genes[j]]++;
                assertTrue(genes[j] <= genes[j + 1]);
            }
            cnt[genes[31]]++;
            for (int j = 0; j < 8; j++) assertTrue(cnt[j] > 0);
        }
    }

    @Test
    public void testConstructor2() {
        for (int i = 0; i < 10; i++) {
            int arr[] = {
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7
            };

            Genotype g1 = new Genotype();
            int cnt[] = new int[8];
            int genes[] = g1.getGenes();
            for (int j = 0; j < 8; j++) cnt[j] = 0;
            for (int j = 0; j < 31; j++) {
                cnt[genes[j]]++;
                assertTrue(genes[j] <= genes[j + 1]);
            }
            cnt[genes[31]]++;
            for (int j = 0; j < 8; j++) assertTrue(cnt[j] > 0);
        }
    }

    @Test
    public void testConstructor3() {
        for (int i = 0; i < TESTS; i++) {
            Genotype gA = new Genotype();
            Genotype gB = new Genotype();
            Genotype g1 = new Genotype(gA.getGenes(), gB.getGenes());

            int cnt[] = new int[8];
            int genes[] = g1.getGenes();
            for (int j = 0; j < 8; j++) cnt[j] = 0;
            for (int j = 0; j < 31; j++) {
                cnt[genes[j]]++;
                assertTrue(genes[j] <= genes[j + 1]);
            }
            cnt[genes[31]]++;
            for (int j = 0; j < 8; j++) {
                //if(cnt[j] > 0 == false) System.out.println(g1);
                assertTrue(cnt[j] > 0);
            }
        }
    }
}
