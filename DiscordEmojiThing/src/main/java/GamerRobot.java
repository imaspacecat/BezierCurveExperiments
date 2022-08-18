import java.awt.*;
import java.awt.event.KeyEvent;

public class GamerRobot extends Robot {

    public GamerRobot() throws AWTException {
    }

    public void stringPress(String string){
        for(int i = 0; i < string.length(); i++){
            keyType(KeyEvent.getExtendedKeyCodeForChar(string.charAt(i)));
        }
    }

    public void delete(int amount){
        for (int i = 0; i < amount; i++) {
            keyType(KeyEvent.VK_BACK_SPACE);
        }
    }

    private void keyType(int keyCode){
        keyPress(keyCode);
        keyRelease(keyCode);
    }

//    gamer.keyPress(KeyEvent.VK_SHIFT);
//    gamer.keyPress(KeyEvent.VK_SEMICOLON);


}
