import com.alee.laf.WebLookAndFeel;
import mesh.gui.MainFrame;

import javax.swing.*;

/**
 * Created by MM on 2014-11-22.
 */
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                // Install WebLaF as application L&F
                WebLookAndFeel.install();

                MainFrame mainFrame = new MainFrame();

            }
        } );
    }
}
