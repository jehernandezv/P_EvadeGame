package test;

import models.Game;
import persistence.SaveGameXML;

public class TestPersistence {
	public static void main(String[] args) {
		Game game = SaveGameXML.readGameSaved();
		System.out.println("balas " + game.getHero().getGroupBullet().getListBullets().size());
		System.out.println("enemigos " + game.getListBoss().size());
	}

}
