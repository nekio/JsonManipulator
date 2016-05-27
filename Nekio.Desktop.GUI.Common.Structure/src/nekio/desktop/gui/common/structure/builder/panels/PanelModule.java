package nekio.desktop.gui.common.structure.builder.panels;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import javax.swing.JLabel;

public class PanelModule extends IPanel{
    private JLabel lblModule;
    
    public PanelModule(JLabel lblModule){
        this.lblModule = lblModule;
        super.activate();
        
        initialize();
    }

    @Override
    protected void addComponents() {
        this.add(lblModule);
    }

    @Override
    protected void addListeners() {}
    
    @Override
    protected void initialize() {
    }

    public JLabel getLblModule() {
        return lblModule;
    }
}
