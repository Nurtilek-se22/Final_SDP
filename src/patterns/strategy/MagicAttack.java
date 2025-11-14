package patterns.strategy;

public class MagicAttack implements AttackStrategy {
    @Override
    public void attack() {
        System.out.println("The magic spell has done damage!");
    }
}