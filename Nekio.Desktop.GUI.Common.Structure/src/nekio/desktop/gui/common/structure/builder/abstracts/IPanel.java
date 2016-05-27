package nekio.desktop.gui.common.structure.builder.abstracts;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.BorderLayout;
import javax.swing.JPanel;

public abstract class IPanel extends JPanel{
    protected abstract void addComponents();
    protected abstract void addListeners();
    protected abstract void initialize();
    
    protected void activate(){
        this.setLayout(new BorderLayout());
        
        addComponents();
        addListeners();
    }
}
