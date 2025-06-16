package businesslogic;

public class Paladin extends AbstractPlayer {
    public Paladin(String name, int x , int y) {
        super(name, "Paladín", x, y);
    }

    @Override
    protected void initializeStats() {
        health = maxHealth = 110;
        damage = 25;
        maxMoves = 3;
        range = 2;
    }

    @Override
    public void useSpecial() {
        if (hasSpecialUsed) {
            GameLogic.getInstance().addGameMessage(name + " ya usó su habilidad especial.");
            return;
        }
        GameLogic logic = GameLogic.getInstance();
        for (Object obj : logic.getPlayers()) {
            PlayerCharacter p = (PlayerCharacter) obj;
            if (Math.abs(p.getX() - x) <= range && Math.abs(p.getY() - y) <= range && p.getHealth() > 0) {
                p.setHealth(Math.min(p.getMaxHealth(), p.getHealth() + 20));
                logic.addGameMessage(name + " protege a " + p.getName() + " (+20 HP).");
            }
        }
        hasSpecialUsed = true;
        logic.notifyRepaint();
    }

    @Override
    public void useSecondSpecial() {
        GameLogic.getInstance().addGameMessage(name + " no tiene habilidad secundaria.");
    }
}﻿
