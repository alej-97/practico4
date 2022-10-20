package practico4.grafica.ventanas.util;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAdapterNumberOnly extends KeyAdapter {
	
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            e.consume();  // if it's not a number, ignore the event
        }
     }
}
