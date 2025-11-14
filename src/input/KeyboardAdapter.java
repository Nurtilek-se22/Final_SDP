package input;

import main.KeyHandler;

public class KeyboardAdapter implements InputAdapter {
    private KeyHandler keyH;
    public KeyboardAdapter(KeyHandler keyH) { this.keyH = keyH; }
    public boolean isUp() { return keyH.upPressed; }

    @Override
    public boolean isDown() {
        return false;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public boolean isAttack() {
        return false;
    }
}
