package persistence;

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
import models.Bullet;
import models.Game;
import models.GroupBoss;
import models.Hero;

public class SaveGameXML {

	public static void SaveXmlGroup(Game game){
		ArrayList<Boss> listBoss = game.getListBoss();
		ArrayList<Bullet> listBullets = game.getHero().getGroupBullet().getListBullets();
		try{
		File file = new File("data/SaveGame.xml");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
		Document doc = new Document();
		Element theRoot = new Element("Game");
		doc.setRootElement(theRoot);
		Element groupBoss = new Element("groupBoss");
		Element groupBullet = new Element("groupBullet");
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
		for (Boss boss: listBoss) {
			
			Element dataBoss = new Element("dataBoss");
			Element posBossX = new Element("posBossX");
			Element posBossY = new Element("posBossY");
			Element sizeBoss = new Element("sizeBoss");
			
			posBossX.addContent(String.valueOf(boss.x));
			posBossY.addContent(String.valueOf(boss.y));
			sizeBoss.addContent(String.valueOf(boss.height));
				
			dataBoss.addContent(posBossX);
			dataBoss.addContent(posBossY);
			dataBoss.addContent(sizeBoss);
			groupBoss.addContent(dataBoss);
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
		theRoot.addContent(groupBoss);
		theRoot.addContent(hero);
		theRoot.addContent(timeGame);
		theRoot.addContent(groupBullet);
		
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
		ArrayList<Boss> bosses = new ArrayList<Boss>();
		for (int i = 0; i < root.getChildren("groupBoss").get(0).getChildren().size(); i++) {
				int posBossX = Integer.valueOf(root.getChildren("groupBoss").get(0).getChildren().get(i).getChildText("posBossX"));
				int posBossY = Integer.valueOf(root.getChildren("groupBoss").get(0).getChildren().get(i).getChildText("posBossY"));
				int sizeBoss = Integer.valueOf(root.getChildren("groupBoss").get(0).getChildren().get(i).getChildText("sizeBoss"));
				bosses.add(new Boss(posBossX, posBossY, sizeBoss));
		}
		groupBoss.setBosses(bosses);
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
		game = new Game(groupBoss, new Hero(posHeroX, posHeroY, sizeHero));
		game.getHero().getGroupBullet().setListBullets(listBullets);
		String [] values = root.getChildText("timeGame").split(":");
		byte second = Byte.parseByte(getValue(values[3]));
		byte minutes = Byte.parseByte(getValue(values[2]));
		byte hour = Byte.parseByte(getValue(values[1]));
		LocalDateTime dateTime = LocalDateTime.of(1, 1, 1, hour, minutes, second);
		game.setCronometerGame(dateTime);
			
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