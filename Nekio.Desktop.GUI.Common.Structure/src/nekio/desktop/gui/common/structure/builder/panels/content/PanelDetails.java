package nekio.desktop.gui.common.structure.builder.panels.content;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import nekio.library.common.structure.model.DetailContent;
import nekio.library.common.structure.model.Dictionary;

public class PanelDetails extends IPanel{
    private List<DetailContent> lstDetail;
    private Map<String, List<PanelDetail>> lstDetailPanels;
    
    public PanelDetails(List<DetailContent> lstDetail){
        this.lstDetail = lstDetail;
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        this.setLayout(new GridLayout(lstDetail.size(), 1));

        lstDetailPanels = new HashMap<String, List<PanelDetail>>();
        List<PanelDetail> lstPanels = new ArrayList<PanelDetail>();
        for(DetailContent detail : lstDetail){
            String detailDescription = detail.getDetailDescription();
            
            // PENDIENTE: Hay que agregar el titulo del detalle en algun lado
            JLabel lblDetailsDescription = new JLabel(detailDescription);
            
            for(Dictionary dictionary : detail.getDetailFields()){
                lstPanels.add(new PanelDetail(dictionary));
            }
            
            lstDetailPanels.put(detailDescription, lstPanels);
        }
        
        for(PanelDetail detailPanel : lstPanels){
            this.add(detailPanel);
        }
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}
    
    public void lockContent(){
        for(Map.Entry<String, List<PanelDetail>> detailPanels : lstDetailPanels.entrySet()){
            for(PanelDetail detailPanel : detailPanels.getValue()){
                detailPanel.lockContent();
            }
        }
    }
    
    public void unlockContent(){
        for(Map.Entry<String, List<PanelDetail>> detailPanels : lstDetailPanels.entrySet()){
            for(PanelDetail detailPanel : detailPanels.getValue()){
                detailPanel.unlockContent();
            }
        }
    }

    public Map<String, List<PanelDetail>> getLstDetailPanels() {
        return lstDetailPanels;
    }

    public List<DetailContent> getLstDetail() {
        return lstDetail;
    }
    
    public List<DetailContent> getDisplayedValues() {
        List<DetailContent> displayedValues = new ArrayList<DetailContent>();
        
        DetailContent detailContent = null;
        List<Dictionary> detailFields = null;
        
        for(Map.Entry<String, List<PanelDetail>> detailPanels : lstDetailPanels.entrySet()){
            detailContent = new DetailContent();
            detailFields = new ArrayList<Dictionary>();
            
            for(PanelDetail detailPanel : detailPanels.getValue()){
                detailFields.add(detailPanel.getDisplayedValues());
            }
            
            detailContent.setDetailDescription(detailPanels.getKey());
            detailContent.setDetailFields(detailFields);
            
            displayedValues.add(detailContent);
        }  
        
        return displayedValues;
    }
    
}
