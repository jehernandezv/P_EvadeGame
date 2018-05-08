package persistence;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import models.Boss;
import models.Enemy;
import models.Bullet;
import models.Game;
import models.GroupBoss;
import models.Hero;

public class SaveGameXML {

	public static void SaveXmlGroup(Game game){
		ArrayList<Enemy> listEnemy = game.getListBoss();
		ArrayList<Bullet> listBullets = game.getHero().getGroupBullet().getListBullets();
		try{
		File file = new File("data/SaveGame.xml");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
		Document doc = new Document();
		Element theRoot = new Element("Game");
		doc.setRootElement(theRoot);
		Element groupEnemy = new Element("groupBoss");
		Element groupBullet = new Element("groupBullet");
		Element sizeGame = new Element("sizeGame");
		Element boss = new Element("boss");
		//Datos del boss
		Element posBossX = new Element("posBossX");
		Element posBossY = new Element("posBossY");
		Element sizeBoss = new Element("sizeBoss");
		Element healthBoss = new Element("healthBoss");
		
		posBossX.addContent(String.valueOf(game.getBoss().x));
		posBossY.addContent(String.valueOf(game.getBoss().y));
		sizeBoss.addContent(String.valueOf(game.getBoss().height));
		healthBoss.addContent(String.valueOf(game.getBoss().getHealth()));
		boss.addContent(posBossX);
		boss.addContent(posBossY);
		boss.addContent(sizeBoss);
		boss.addContent(healthBoss);
		
		
		//Tamaño de la pantalla
		Element heightGame = new Element("heigthGame");
		Element widthGame = new Element("widthGame");
		heightGame.addContent(String.valueOf(game.getAreaGame().getHeight()));
		widthGame.addContent(String.valueOf(game.getAreaGame().getWidth()));
		sizeGame.addContent(widthGame);
		sizeGame.addContent(heightGame);
		//Datos del heroe
		Element hero = new Element("hero");
		Element posHeroX = new Element("posHeroX");
		Element posHeroY = new Element("posHeroY");
		Element sizeHero = new Element("sizeHero");
		
		posHeroX.addContent(String.valueOf(game.getHero().x));
		posHeroY.addContent(String.valueOf(game.getHero().y));
		sizeHero.addContent(String.valueOf(game.getHero().height));
		
		hero.addContent(posHeroY);
		hero.addContent(posHeroX);
		hero.addContent(sizeHero);
		
		//tiempo de juego
		Element timeGame = new Element("timeGame");
		timeGame.addContent(game.getCronometer());
		
		//datos de los enemigos
		for (Enemy enemy: listEnemy) {
			Element dataEnemy = new Element("dataBoss");
			Element posEnemyX = new Element("posBossX");
			Element posEnemyY = new Element("posBossY");
			Element sizeEnemy = new Element("sizeBoss");
			Element health = new Element("health");
			
			posEnemyX.addContent(String.valueOf(enemy.x));
			posEnemyY.addContent(String.valueOf(enemy.y));
			sizeEnemy.addContent(String.valueOf(enemy.height));
			health.addContent(String.valueOf(enemy.getHealth()));
				
			dataEnemy.addContent(posEnemyX);
			dataEnemy.addContent(posEnemyY);
			dataEnemy.addContent(sizeEnemy);
			dataEnemy.addContent(health);
			groupEnemy.addContent(dataEnemy);
			}
		
		//Balas disparadas
		for(Bullet bullet: listBullets){
			
			Element dataBullet = new Element("dataBullet");
			Element posBulletX = new Element("posBulletX");
			Element posBulletY = new Element("posBulletY");
			Element sizeBullet = new Element("sizeBullet");
			Element directionBullet = new Element("directionBullet");
			
			
			posBulletX.addContent(String.valueOf(bullet.x));
			posBulletY.addContent(String.valueOf(bullet.y));
			sizeBullet.addContent(String.valueOf(bullet.height));
			directionBullet.addContent(String.valueOf(bullet.getDirection()));
			
			dataBullet.addContent(posBulletY);
			dataBullet.addContent(posBulletX);
			dataBullet.addContent(sizeBullet);
			dataBullet.addContent(directionBullet);
			groupBullet.addContent(dataBullet);
			
		}
		
		
		//Datos al padre del XML
		theRoot.addContent(groupEnemy);
		theRoot.addContent(hero);
		theRoot.addContent(timeGame);
		theRoot.addContent(groupBullet);
		theRoot.addContent(sizeGame);
		theRoot.addContent(boss);
		
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		xmlOutput.output(doc, fileOutputStream);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static Game readGameSaved(){
	GroupBoss groupBoss = new GroupBoss();	
	Game game = null;
	SAXBuilder builder = new SAXBuilder();
	File file = new File("data/SaveGame.xml");
	try {
		Document doc = builder.build(file);
		Element root = doc.getRootElement();
		ArrayList<Enemy> bosses = new ArrayList<Enemy>();
		for (int i = 0; i < root.getChildren("groupBoss").get(0).getChildren().size(); i++) {
				int posBossX = Integer.valueOf(root.getChildren("groupBoss").get(0).getChildren().get(i).getChildText("posBossX"));
				int posBossY = Integer.valueOf(root.getChildren("groupBoss").get(0).getChildren().get(i).getChildText("posBossY"));
				int sizeBoss = Integer.valueOf(root.getChildren("groupBoss").get(0).getChildren().get(i).getChildText("sizeBoss"));
				short health = Short.parseShort(root.getChildren("groupBoss").get(0).getChildren().get(i).getChildText("health"));
				Enemy boss = new Enemy(posBossX, posBossY, sizeBoss);
				boss.setHealth(health);
				bosses.add(boss);
		}
		groupBoss.setBosses(bosses);
		//Boss
		int posBossX = Integer.parseInt(root.getChildren("boss").get(0).getChildText("posBossX"));
		int posBossY = Integer.parseInt(root.getChildren("boss").get(0).getChildText("posBossY"));
		int sizeBoss = Integer.parseInt(root.getChildren("boss").get(0).getChildText("sizeBoss"));
		int healthBoss = Integer.parseInt(root.getChildren("boss").get(0).getChildText("healthBoss"));
		Boss boss = new Boss(posBossX, posBossY, sizeBoss);
		boss.setHealth((short) healthBoss);
		
		//Disparos
		ArrayList<Bullet> listBullets = new ArrayList<Bullet>();
		if(root.getChildren("groupBullet").get(0).cloneContent().size() > 0){
			for (int i = 0; i < root.getChildren("groupBullet").get(0).getChildren("dataBullet").size(); i++) {
				int posBulletX = Integer.valueOf(root.getChildren("groupBullet").get(0).getChildren().get(i).getChildText("posBulletX"));
				int posBulletY = Integer.valueOf(root.getChildren("groupBullet").get(0).getChildren().get(i).getChildText("posBulletY"));
				int sizeBullet = Integer.valueOf(root.getChildren("groupBullet").get(0).getChildren().get(i).getChildText("sizeBullet"));
				double directionBullet = Double.parseDouble(root.getChildren("groupBullet").get(0).getChildren().get(i).getChildText("directionBullet"));
				listBullets.add(new Bullet(directionBullet, (short) sizeBullet, posBulletX, posBulletY));
			}
		}
		
		int posHeroX = Integer.parseInt(root.getChildren("hero").get(0).getChildText("posHeroX"));
		int posHeroY = Integer.parseInt(root.getChildren("hero").get(0).getChildText("posHeroY"));
		int sizeHero = Integer.parseInt(root.getChildren("hero").get(0).getChildText("sizeHero"));
		game = new Game(groupBoss, new Hero(posHeroX, posHeroY, sizeHero),null,boss);
		game.getHero().getGroupBullet().setListBullets(listBullets);
		String [] values = root.getChildText("timeGame").split(":");
		byte second = Byte.parseByte(getValue(values[3]));
		byte minutes = Byte.parseByte(getValue(values[2]));
		byte hour = Byte.parseByte(getValue(values[1]));
		LocalDateTime dateTime = LocalDateTime.of(1, 1, 1, hour, minutes, second);
		game.setCronometerGame(dateTime);
		//Tamaño del juego
		double heigth = Double.parseDouble(root.getChildren("sizeGame").get(0).getChildText("heigthGame"));
		double width = Double.parseDouble(root.getChildren("sizeGame").get(0).getChildText("widthGame"));
		game.setAreaGame(new Rectangle((int)width,(int)heigth));	
		
	} catch (JDOMException | IOException e) {
		
	}
	return game;
}
	
	public static String getValue(String string){
		byte cont = 0 ;
		String value = "";
		boolean flag = false;
		while(!flag){
			if(string.charAt(cont) != 32){
				flag = true;
				value = String.valueOf(string.charAt(cont));
			}
			cont ++;
		}
		return value;
	}
	
}
