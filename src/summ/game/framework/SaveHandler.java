package summ.game.framework;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class SaveHandler {
	
	public static void saveGame(SaveState save){
	 
		   try{
	 
			FileOutputStream fout = new FileOutputStream("saveFile.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(save);
			oos.close();
			System.out.println("saved.");
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
	}
	
	public static SaveState loadGame(){
		 
		   SaveState save;
	 
		   try{
	 
			   FileInputStream fin = new FileInputStream("saveFile.sav");
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   save = (SaveState) ois.readObject();
			   ois.close();
	 
			   return save;
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
			   return null;
		   } 
	} 

}
