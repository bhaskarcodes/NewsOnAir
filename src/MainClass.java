 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
public class MainClass 
{
    NewsOnAirMain obj;
    BufferedInputStream BIS;
    public Player player;
    
    
       String [] links = new String[4];

    public MainClass(NewsOnAirMain obj) throws IOException, InterruptedException {
        this.obj = obj;
    }
    
    public void setLinks(){
              BufferedInputStream in = null;
       Scanner sc = null;
        try {
            sc = new Scanner(new File("mp3.txt"));
        } catch (FileNotFoundException ex) {
            
        }
 links = new String[4];
        int i=0;
        while (sc.hasNextLine()) {
          String k = sc.nextLine();
          k=k.replace(" ", "%20");
          links[i] = k;
          i++;
        }
        for ( String s : links){
            System.out.println(s);
        }
        
    }

    String getCorrectLink(String find){
        for(String s : links){
            if(s.toLowerCase().contains(find.toLowerCase())){
                return s;
            }
        }
        return "";
    }
    
    
    
    public void stop(){
        if(player!=null){//theres a song playing
            player.close();
        
        }
    }
    
    public void play(String keyword){
        String link=getCorrectLink(keyword);
        try {
            
            BIS = new BufferedInputStream(new URL(link).openStream());
            player  = new Player(BIS);
                      
        } catch (FileNotFoundException | JavaLayerException ex) {
          //  Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        
        //CREATE a thread so that you can use other buttons while music plays 
        new Thread(){
            @Override
            public void run(){            
                    try {
                    player.play();
                    if(player.isComplete()){
                        obj.setLabel("Welcome to AIR");
                    }
                    }
                    
                 catch (JavaLayerException ex) {
                   // Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }.start();
    }
}
