package nekio.desktop.gui.common.structure.builder.panels;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;

public class PanelBrowser extends IPanel{
    private String path;
    
    public PanelBrowser(String path){
        this.path = path;
        super.activate();
        
        initialize();
    }

    @Override
    protected void addComponents() {
        // TO-DO
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}
    
}
