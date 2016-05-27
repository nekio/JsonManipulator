package nekio.desktop.gui.common.structure.builder.components;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class FieldCatalogLinker {
    private String id;
    private Field field;
    private Catalog catalog;
    
    public FieldCatalogLinker(Field field, Catalog catalog){
        this.field = field;
        this.catalog = catalog;
        
        linkComponents();
    }

    public String getId() {
        return id;
    }
    
    private void linkComponents(){
        JComboBox listElement = catalog.getListElement();
        JComponent singleElement = field.getComponent();
        
        listElement.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent evt){
                String selection = listElement.getSelectedItem().toString();
                String value = selection.split("-")[1].trim();
                
                field.setElementValue(value);
            }
        });
        
        /*singleElement.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt){
                if(singleElement instanceof JTextComponent){
                    JTextComponent textElement = (JTextComponent)singleElement;
                    for(Map.Entry<Integer, String> item : catalog.getContentValues().entrySet()){
                        if(textElement.equals(item.getKey()) || textElement.equals(item.getValue())){
                            System.out.println("!");
                        }
                    }
                }
            }
        });*/
    }
}
