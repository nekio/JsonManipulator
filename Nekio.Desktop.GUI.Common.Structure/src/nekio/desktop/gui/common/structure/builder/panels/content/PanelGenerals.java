package nekio.desktop.gui.common.structure.builder.panels.content;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import nekio.desktop.gui.common.structure.builder.components.Catalog;
import nekio.library.common.structure.model.Dictionary;

public class PanelGenerals extends IPanel{
    private List<Dictionary> lstGenerals;
    private List<PanelGeneral> lstGeneralPanels;
    
    public PanelGenerals(List<Dictionary> lstGenerals){
        this.lstGenerals = lstGenerals;
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        this.setLayout(new GridLayout(lstGenerals.size(), 1));

        lstGeneralPanels = new ArrayList<PanelGeneral>();
        for(Dictionary dictionary : lstGenerals){            
            lstGeneralPanels.add(new PanelGeneral(dictionary));
        }
        
        for(PanelGeneral generalPanel : lstGeneralPanels){
            this.add(generalPanel);
        }
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}
    
    public void hideCatalogs(List<Catalog> catalogs){
        for(PanelGeneral panel : lstGeneralPanels){
            panel.hideCatalogs(catalogs);
        }
    }
    
    public void lockContent(){
        for(PanelGeneral panel : lstGeneralPanels){
            panel.lockContent();
        }
    }
    
    public void unlockContent(){
        for(PanelGeneral panel : lstGeneralPanels){
            panel.unlockContent();
        }
    }

    public List<PanelGeneral> getLstGeneralPanels() {
        return lstGeneralPanels;
    }
    
}