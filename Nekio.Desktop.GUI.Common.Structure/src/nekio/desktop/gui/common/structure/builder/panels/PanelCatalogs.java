package nekio.desktop.gui.common.structure.builder.panels;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import nekio.desktop.gui.common.structure.builder.components.Catalog;

public class PanelCatalogs extends IPanel{
    private Map<String, JComboBox> cmbCatalogs;
    private List<Catalog> lstCatalogs;
    
    public PanelCatalogs(Map<String, JComboBox> cmbCatalogs){
        this.cmbCatalogs = cmbCatalogs;
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        this.setLayout(new GridLayout(cmbCatalogs.size(), 1));
        
        lstCatalogs = new ArrayList<Catalog>();
        for(Map.Entry<String, JComboBox> catalog : cmbCatalogs.entrySet()){
            lstCatalogs.add(new Catalog(catalog.getKey(), catalog.getValue()));
        }
        
        for(Catalog catalog : lstCatalogs){
            this.add(catalog);
        }
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}

    public List<Catalog> getLstCatalogs() {
        return lstCatalogs;
    }
    
}
