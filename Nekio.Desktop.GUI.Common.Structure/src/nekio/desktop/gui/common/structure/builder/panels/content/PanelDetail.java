package nekio.desktop.gui.common.structure.builder.panels.content;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import nekio.desktop.gui.common.structure.builder.components.Field;
import nekio.desktop.gui.common.structure.builder.constants.Globals;
import nekio.library.common.structure.model.Dictionary;

public class PanelDetail extends IPanel{
    private Dictionary dictionary;
    private List<Field> lstDetails;
    
    public PanelDetail(Dictionary dictionary){
        this.dictionary = dictionary;
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        JPanel pnlGeneralTitle = new JPanel(new BorderLayout());
        JPanel pnlGeneralFields = new JPanel();
        
        // Title
        pnlGeneralTitle.add(new JLabel(Globals.formatText(dictionary.getTitle())));
        pnlGeneralTitle.setBackground(Color.LIGHT_GRAY);
        this.add(pnlGeneralTitle, BorderLayout.NORTH);
        
        // Fields
        pnlGeneralFields.setLayout(new GridLayout(dictionary.getContent().size(), 1));

        lstDetails  = new ArrayList<Field>();
        for(Object detail : dictionary.getContent().entrySet()){
            Map.Entry<String,String> mapDetail = (Map.Entry<String,String>)detail;
            
            lstDetails.add(new Field(mapDetail.getKey(), mapDetail.getValue(), new JTextField()));
        }
        
        for(Field field : lstDetails){
            pnlGeneralFields.add(field);
        }
        
        this.add(pnlGeneralFields, BorderLayout.CENTER);
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}
    
    public void reinitialize() {
        for(Field field : lstDetails){
            field.setElementValue("");
        }
    }
    
    public void lockContent(){
        for(Field field : lstDetails){
            field.setActivation(false);
        }
    }
    
    public void unlockContent(){
        for(Field field : lstDetails){
            field.setActivation(true);
        }
    }

    public List<Field> getLstDetails() {
        return lstDetails;
    }
    
    public String getDetailTitle(){
        return dictionary.getTitle();
    }
    
    public Dictionary getDisplayedValues() {
        Dictionary exposedValues = new Dictionary();
        
        Map<String, String> mapValues = new HashMap<String, String>();
        for(Field field : lstDetails){
            mapValues.put(field.getElementName(), field.getDisplayedValue());
        }
        
        exposedValues.setTitle(dictionary.getTitle());
        exposedValues.setDescription(dictionary.getDescription());
        exposedValues.setContent(mapValues);
        
        return exposedValues;
    }
}
