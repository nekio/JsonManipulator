package nekio.desktop.gui.common.structure.builder.components;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import nekio.desktop.gui.common.structure.builder.constants.Globals;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;

public class Catalog extends IPanel{
    private String elementName;
    private String elementDescription;
    
    private JLabel lblElementDescription;
    private JComboBox listElement;
    private Map<Integer, String> contentValues;
    
    public Catalog(String elementName, JComboBox listElement){
        this.elementName = elementName;
        this.listElement = listElement;
        
        super.activate();
    }
    
    @Override
    protected void addComponents() {
        lblElementDescription = new JLabel(Globals.formatText(elementName));
        
        this.add(lblElementDescription, BorderLayout.WEST);
        this.add(listElement, BorderLayout.CENTER);
        this.add(new JLabel(Globals.PAD), BorderLayout.SOUTH);
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}

    public void setElementDescription(String elementDescription) {
        this.elementDescription = elementDescription;
        
        lblElementDescription.setText(Globals.formatText(elementDescription));
    }
    
    public String getElementName() {
        return elementName;
    }
    
    public String getElementDescription() {
        return elementDescription;
    }
    
    public JComboBox getListElement() {        
        return listElement;
    }   

    public Map<Integer, String> getContentValues() {
        if(contentValues == null){
            contentValues = new HashMap<Integer, String>();
        }
        
        if(contentValues.size() == 0){
            int key = 0;
            String value = null;

            Object element = null;
            String[] pairs = null;
            for(int i = 0; i < listElement.getItemCount(); i ++){
                element = listElement.getItemAt(i);
                pairs = element.toString().split("-");

                key = Integer.parseInt(pairs[0].trim());
                value = pairs[1];

                contentValues.put(key, value);
            }
        }  
        
        return contentValues;
    }
}
