package nekio.desktop.gui.common.structure.builder.panels;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import nekio.desktop.gui.common.structure.builder.panels.content.PanelScalars;
import nekio.desktop.gui.common.structure.builder.panels.content.PanelGeneral;
import nekio.desktop.gui.common.structure.builder.panels.content.PanelGenerals;
import nekio.desktop.gui.common.structure.builder.panels.content.PanelDetails;
import nekio.desktop.gui.common.structure.builder.panels.content.PanelDetail;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import nekio.desktop.gui.common.structure.builder.components.Catalog;
import nekio.desktop.gui.common.structure.builder.components.Field;
import nekio.library.common.structure.model.Content;
import nekio.library.common.structure.model.DetailContent;
import nekio.library.common.structure.model.Dictionary;

public class PanelFields extends IPanel{
    private Map<String, JComponent> mapFields;
    private List<Field> lstFields;
    
    private JPanel pnlFields;
    private PanelScalars pnlScalars;
    private PanelGenerals pnlGenerals;
    private PanelDetails pnlDetails;
    
    public PanelFields(Map<String, JComponent> mapFields){
        this.mapFields = mapFields;
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        pnlFields = new JPanel();
        pnlFields.setLayout(new GridLayout(mapFields.size(), 1));

        lstFields = new ArrayList<Field>();
        for(Map.Entry<String, JComponent> catalog : mapFields.entrySet()){
            lstFields.add(new Field(catalog.getKey(), catalog.getValue()));
        }
        
        for(Field field : lstFields){
            pnlFields.add(field);
        }
        
        this.add(pnlFields);
    }
    
    public void loadContent(Content content){
        reload();
        
        loadScalarContent(content.getSingleFields());
        loadGeneralContent(content.getPairFields());
        loadDetailContent(content.getDetailFields());
    }
    
    private void reload(){
        pnlFields.removeAll();
        pnlFields.setLayout(new BorderLayout());
    }
    
    private void loadScalarContent(Map<String, String> scalarFields){  
        pnlScalars = new PanelScalars(scalarFields);        
        pnlFields.add(getPnlScalars(), BorderLayout.NORTH);
    }
    
    private void loadGeneralContent(List<Dictionary> generalFields){  
        pnlGenerals = new PanelGenerals(generalFields);        
        pnlFields.add(pnlGenerals, BorderLayout.CENTER);
    }
    
    private void loadDetailContent(List<DetailContent> detailFields){
        pnlDetails = new PanelDetails(detailFields);
        pnlFields.add(pnlDetails, BorderLayout.SOUTH);
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}
    
    public void reinitialize() {
        if(pnlScalars != null){
            pnlScalars.reinitialize();
        }
        
        if(pnlGenerals != null){
            for(PanelGeneral pnlGeneral : pnlGenerals.getLstGeneralPanels()){
                pnlGeneral.reinitialize();
            }
        }
        
        if(pnlDetails != null){
            for(Map.Entry<String, List<PanelDetail>> detailPanels : pnlDetails.getLstDetailPanels().entrySet()){
                for(PanelDetail pnlDetail : detailPanels.getValue()){
                    pnlDetail.reinitialize();
                }
            }
        }
    }
    
    public void hideCatalogs(List<Catalog> catalogs){
        pnlGenerals.hideCatalogs(catalogs);
    }

    public List<Field> getLstFields() {
        return lstFields;
    }

    public PanelScalars getPnlScalars() {
        return pnlScalars;
    }
    
    public PanelGenerals getPnlGenerals() {
        return pnlGenerals;
    }

    public PanelDetails getPnlDetails() {
        return pnlDetails;
    }
    
}
