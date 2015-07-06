package items.mobs;

/**
 * Created by CH on 05/07/2015.
 */
public enum ItemTier {
    NORMAL,
    UNIQUE,
    RARE,
    LEGENDARY;


    ItemTier(){
    }
    private static final int[] healthExtra = new int[]{1, 3, 7, 12, 18, 26, 37, 50, 67, 88, 112, 144, 182, 210, 240, 272, 306, 342, 380, 420, 462, 506, 552, 600, 650, 702, 756, 812, 870};
    private static final int[] damageValues = new int[]{3, 5, 8, 11, 14, 18, 24, 30, 36, 44, 52, 62, 74, 86, 98, 110, 124, 138, 154, 170, 186, 194, 212, 230, 250, 270, 292, 314, 336};
    private static final double[] itemBonus = new double[]{
            100d / 100, 140d / 100, 180d / 100, 220d / 100,
            100d / 100, 135d / 100, 175d / 100, 215d / 100,
            100d / 100, 130d / 100, 170d / 100, 210d / 100,
            95d / 100, 125d / 100, 165d / 100, 205d / 100,
            90d / 100, 120d / 100, 160d / 100, 200d / 100,
            85d / 100, 115d / 100, 155d / 100, 195d / 100,
            80d / 100, 110d / 100, 150d / 100, 190d / 100,
            80d / 100, 105d / 100, 145d / 100, 185d / 100,
            80d / 100, 100d / 100, 140d / 100, 180d / 100,
            80d / 100, 100d / 100, 135d / 100, 175d / 100,
            80d / 100, 100d / 100, 130d / 100, 170d / 100,
            80d / 100, 100d / 100, 125d / 100, 165d / 100,
            80d / 100, 100d / 100, 120d / 100, 160d / 100,
            80d / 100, 100d / 100, 120d / 100, 155d / 100,
            80d / 100, 100d / 100, 120d / 100, 150d / 100,
            80d / 100, 100d / 100, 120d / 100, 145d / 100,
            80d / 100, 100d / 100, 120d / 100, 140d / 100,
            80d / 100, 100d / 100, 120d / 100, 140d / 100,
            80d / 100, 100d / 100, 120d / 100, 140d / 100,
            80d / 100, 100d / 100, 120d / 100, 140d / 100,
            80d / 100, 100d / 100, 120d / 100, 140d / 100
    };

    private double getAssassinDMG(int level){
        int index = level / 5;
        int cur = damageValues[index];
        int next = damageValues[index + 1];
        int dif = next - cur;
        double add = ((double) (level % 5) / 5) * dif;
        return getBonus(level) * (add + cur) * 1.7;
    }

    public int getHealth(int level){
        int index = level / 5;
        int cur = healthExtra[index];
        int next = healthExtra[index + 1];
        int dif = next - cur;
        double add = ((double) (level % 5) / 5) * dif;
        return (int)(Math.pow(0.993,100-level) * 7 * getBonus(level) * (add + cur));
    }

    private double getBonus(int level) {
        return itemBonus[4 * (level/5) + ordinal()];
    }


    public int getMinDMG(int level){
        return (int) (getAssassinDMG(level) * 0.8);
    }

    public int getMaxDMG(int level){
        return (int) (getAssassinDMG(level) * 1.2);
    }



}

