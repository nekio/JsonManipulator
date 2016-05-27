package nekio.desktop.gui.common.structure.builder.constants;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import javax.swing.JOptionPane;

public class Globals {
    public static final String PAD = "    ";
    
    public static String formatText(String text){
        return PAD + text + PAD;
    }
    
    public static void showMessage(String text) {
        JOptionPane.showMessageDialog(null, text, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
