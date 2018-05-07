package persistence;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;
//
//import model.ETypeSale;
//import model.Sale;
//import model.SaleGroup;
//import model.Stand;
//
//public class XmlManager {
//	
//	/**
//	 * Este metodo lee una archivo Xml de la raiz del programa, esta contiene todos los objetos User y los devuelve
//	 * en una arrayList de user
//	 * @return
//	 */
//	public static ArrayList<Stand> readXml(){
//		ArrayList<Stand> stands = new ArrayList<Stand>();
//		SAXBuilder builder = new SAXBuilder();
//		File file = new File("data/StandsXML.xml");
//		try {
//			Document doc = builder.build(file);
//			Element root = doc.getRootElement();
//			//recorre los los stand dispuestos en el archivo
//			for (int i = 0; i < root.getChildren("Stand").size(); i++) {
//				SaleGroup saleGroup = new SaleGroup();
//				System.out.println("cant: " + root.getChildren("Stand").size());
//				int idStand = Integer.valueOf(root.getChildren("Stand").get(i).getChildText("idStand"));
//				String standName = root.getChildren("Stand").get(i).getChildText("name");
//				System.out.println("name " + root.getChildren("Stand").get(i).getChildText("name"));
//				//For se encargar de recorrer las ventas de cada Stand
//				for (int j = 0; j < root.getChildren("Stand").get(i).getChildren("SaleGroup").get(i).getChildren("Sale").size(); j++) {
//					System.out.println("sales: " + root.getChildren("Stand").get(i).getChildren("SaleGroup").get(i).getChildren("Sale").size());
//					System.out.println("j: " + j);
//					String [] date = new String [3];
//					int idSale = Integer.valueOf(root.getChildren("Stand").get(i).getChildren("SaleGroup").get(i).
//							getChildren("Sale").get(j).getChildText("idSale"));
//					double valueSale = Double.valueOf(root.getChildren("Stand").get(i).getChildren("SaleGroup").get(i).
//							getChildren("Sale").get(j).getChildText("valueSale"));
//					int idStandSale = Integer.valueOf(root.getChildren("Stand").get(i).getChildren("SaleGroup").get(i).
//							getChildren("Sale").get(j).getChildText("idStand"));
//					String dateSale;
//					dateSale = root.getChildren("Stand").get(i).getChildren("SaleGroup").get(i).getChildren("Sale").get(j).getChildText("dateSale");
//					date = dateSale.split("-");
//					
//					Sale sale = new Sale(valueSale,(short) idSale, (short)idStandSale, LocalDate.of(Integer.valueOf(date[0])
//							, Integer.valueOf(date[1]), Integer.valueOf(date[2])),ETypeSale.EDUCATION_MATERIAL);
//					saleGroup.addSale(sale);
//				}
//				
//				Stand stand = new Stand((short)idStand, standName);
//				stand.setSaleGroup(saleGroup);
//				
//				stands.add(stand);
//				
//			}
//		} catch (JDOMException | IOException e) {
//			
//			
//		}
//		
//		return stands;
//	}
//	/**
//	 * Este metodo guarda en el archivo Xml un user que entra por parametro
//	 * @param user user a guardar en el Xml
//	 */
//	/*public static void SaveXmlPlayer(Stand stand){
//		ArrayList<Stand> arrayStands = new ArrayList<Stand>();
//		File file = new File("data/Stands.xml");
//		if(file.exists()){
//		     arrayStands = XmlManager.readXml();
//			arrayUsers.add(user);
//			SaveXmlGroup(arrayUsers);
//		}else{
//			arrayUsers.add(user);
//			SaveXmlGroup(arrayUsers);
//		}
//	}*/
//	/**
//	 * Este metodo recibe un arrayList de stand y lo guarda en un Xml
//	 * @param stands Array a guardar
//	 */
//	public static void SaveXmlGroup(ArrayList<Stand> stands){
//		
//		try{
//		File file = new File("data/StandsXML.xml");
//		
//			FileOutputStream fileOutputStream = new FileOutputStream(file);
//		
//		Document doc = new Document();
//		Element theRoot = new Element("StandsGroup");
//		doc.setRootElement(theRoot);
//		
//		
//		for (Stand stand: stands) {
//			Element standSave = new Element("Stand");
//			Element idStand = new Element("idStand");
//			Element nameStand = new Element("name");
//			
//			idStand.addContent(""+ stand.getIdStand());
//			nameStand.addContent(""+stand.getName());
//			
//			Element listSale = new Element("SaleGroup");
//			ArrayList<Sale> salesAux = stand.getSaleGroup().getListSales();
//			for (int i = 0; i < salesAux.size(); i++) {
//				Element Sale = new Element("Sale");
//				Element idSale = new Element("idSale");
//				Element valueSale = new Element("valueSale");
//				Element saleIdStand = new Element("idStand");
//				Element saleDate = new Element("dateSale");
//				Element typeSale = new Element("eTypeSale");
//				
//				idSale.addContent(""+ salesAux.get(i).getIdSale());
//				valueSale.addContent(""+salesAux.get(i).getValueSale());
//				saleIdStand.addContent(""+salesAux.get(i).getIdStand());
//				saleDate.addContent(""+salesAux.get(i).getDateSale().toString());
//				typeSale.addContent(salesAux.get(i).geteTypeSale().toString());
//				
//				Sale.addContent(idSale);
//				Sale.addContent(valueSale);
//				Sale.addContent(saleIdStand);
//				Sale.addContent(saleDate);
//				Sale.addContent(typeSale);
//				listSale.addContent(Sale);
//				
//			}
//			standSave.addContent(listSale);
//			standSave.addContent(idStand);
//			standSave.addContent(nameStand);
//	
//			theRoot.addContent(standSave);
//		}
//		
//		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
//		xmlOutput.output(doc, fileOutputStream);
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public ETypeSale validateTypeSale(String type){
//		ETypeSale eTypeSale = null;
//		switch (ETypeSale.valueOf(type)) {
//		case EAT:
//			eTypeSale = ETypeSale.EAT;
//			break;
//		case EDUCATION_MATERIAL:
//			eTypeSale = ETypeSale.EDUCATION_MATERIAL;
//			break;
//		case HANDICRAFTS:
//			eTypeSale = ETypeSale.HANDICRAFTS;
//			break;
//		case OTHERS:
//			eTypeSale = ETypeSale.OTHERS;
//			break;
//		}
//		return eTypeSale;
//	}
//	
//	
//}
