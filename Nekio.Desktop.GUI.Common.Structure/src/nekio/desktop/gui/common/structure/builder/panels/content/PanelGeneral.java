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
import nekio.desktop.gui.common.structure.builder.components.Catalog;
import nekio.desktop.gui.common.structure.builder.components.Field;
import nekio.desktop.gui.common.structure.builder.constants.Globals;
import nekio.library.common.structure.model.Dictionary;

public class PanelGeneral extends IPanel{
    private Dictionary dictionary;
    private List<Field> lstGenerals;
    
    public PanelGeneral(Dictionary dictionary){
        this.dictionary = dictionary;
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        JPanel pnlGeneralTitle = new JPanel(new BorderLayout());
        JPanel pnlGeneralFields = new JPanel();
        
        // Title
        pnlGeneralTitle.add(new JLabel(Globals.formatText(getDictionary().getTitle())));
        pnlGeneralTitle.setBackground(Color.LIGHT_GRAY);
        this.add(pnlGeneralTitle, BorderLayout.NORTH);
        
        // Fields
        Map mapContent = getDictionary().getContent();
        pnlGeneralFields.setLayout(new GridLayout(mapContent.size(), 1));

        lstGenerals = new ArrayList<Field>();
        for(Object mapItem : mapContent.entrySet()){
            Map.Entry<String,String> mapGeneralField = (Map.Entry<String,String>)mapItem;
            
            String elementName = mapGeneralField.getKey();
            String elementValue = mapGeneralField.getValue();
            
            lstGenerals.add(new Field(elementName, elementValue, new JTextField()));
        }
        
        for(Field field : lstGenerals){
            pnlGeneralFields.add(field);
        }
        
        this.add(pnlGeneralFields, BorderLayout.CENTER);
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}
    
    public void reinitialize() {
        for(Field field : lstGenerals){
            field.setElementValue("");
        }
    }
    
    public void hideCatalogs(List<Catalog> catalogs){
        for(Catalog catalog : catalogs){
            String catalogName = catalog.getElementName();
            
            for(Field field : lstGenerals){
                if(catalogName.equals(field.getElementName())){
                    field.replace(catalog.getListElement(), catalog.getElementDescription(), field.getElementValue());
                }
            }
        }
    }
    
    public void lockContent(){
        for(Field field : lstGenerals){
            field.setActivation(false);
        }
    }
    
    public void unlockContent(){
        for(Field field : lstGenerals){
            field.setActivation(true);
        }
    }

    public List<Field> getLstGenerals() {
        return lstGenerals;
    }
    
    public String getGeneralTitle(){
        return getDictionary().getTitle();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
    
    public Dictionary getDisplayedValues() {
        Dictionary exposedValues = new Dictionary();
        
        Map<String, String> mapValues = new HashMap<String, String>();
        for(Field field : lstGenerals){
            mapValues.put(field.getElementName(), field.getDisplayedValue());
        }
        
        exposedValues.setTitle(dictionary.getTitle());
        exposedValues.setDescription(dictionary.getDescription());
        exposedValues.setContent(mapValues);
        
        return exposedValues;
    }
    
}