package patterns.strategy;

public class MeleeAttack implements AttackStrategy {
    @Override
    public void attack() {
        System.out.println(" Powerful melee sword attack!");
    }
}