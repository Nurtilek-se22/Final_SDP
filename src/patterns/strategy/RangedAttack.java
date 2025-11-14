package patterns.strategy;

public class RangedAttack implements AttackStrategy {
    @Override
    public void attack() {
        System.out.println("The arrow was fired from afar!");
    }
}