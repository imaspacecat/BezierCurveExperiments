import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.annotation.Native;
import java.util.*;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

/**
 * @author vakho
 */
public class KeyLogger implements NativeKeyListener {
    private Map<String, String> gifs;
    private GamerRobot gamer  = new GamerRobot();

    public KeyLogger(Map gifs) throws AWTException {
        this.gifs = gifs;
    }


    private String input = "";
    String key;

    int colonCount = 0;
    boolean colonEnd = true;
    public void nativeKeyPressed(NativeKeyEvent e) {

        key = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.println("Key Pressed: " + key);

        key = key.equals("Semicolon") ? ";" : key;

        if(key.equals(";")){
            colonCount++;
            colonEnd = true;
        }

        if(colonCount % 2 == 1){
            if(key.matches("[A-z]")){
                input += key;
                System.out.println("input is: " + input);
            }
        } else if(colonEnd){
            // this needs another condition since it keeps looping this code
            // it should only run once after the second semicolon is typed
            System.out.println("final input is: " + input);
            input = input.toLowerCase();
            if(gifs.get(input) != null){
                gamer.delete(input.length() + 2);
                gamer.stringPress(gifs.get(input));
            }
            input = "";
            colonEnd = false;
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }
}
