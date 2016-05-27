package nekio.desktop.gui.common.structure.builder.components;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import nekio.desktop.gui.common.structure.builder.constants.Globals;

public final class Field extends IPanel{
    private String elementName;
    private String elementValue;
    
    private JLabel lblElementName;
    private JComponent component;
    
    public Field(String elementName, JComponent component){
        this(elementName, null, component);
    }
    
    public Field(String elementName, String elementValue, JComponent component){
        this.component = component;
        
        super.activate();
        
        this.setElementName(elementName);
        this.setElementValue(elementValue);
    }
    
    @Override
    protected void addComponents() {
        lblElementName = new JLabel(Globals.formatText(elementName));
        
        this.add(lblElementName, BorderLayout.WEST);
        this.add(component, BorderLayout.CENTER);
        this.add(new JLabel(Globals.PAD), BorderLayout.SOUTH);
    }

    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}
    
    public void setActivation(boolean activate){
        lblElementName.setEnabled(activate);
        component.setEnabled(activate);
    }
    
    public void replace(JComponent component, String elementName, String elementValue){
        if(component != null){
            this.remove(this.component);
            this.component = null;
        }
        
        this.component = component;
        
        this.setElementName(elementName);
        this.setElementValue(elementValue);
        
        this.add(this.component, BorderLayout.CENTER);
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
        
        lblElementName.setText(Globals.formatText(elementName));
    }
    
    public void setElementValue(String elementValue) {
        this.elementValue = elementValue;
        
        if(component instanceof JTextComponent){
            JTextComponent textComponent = (JTextComponent)component;
            textComponent.setText(elementValue);
        }if(component instanceof JComboBox){
            JComboBox listComponent = (JComboBox)component;
            
            if(elementValue.isEmpty()){
                listComponent.setSelectedIndex(0);
            }else{
                for(int i = 0; i < listComponent.getItemCount(); i++){
                    String currentValue = listComponent.getItemAt(i).toString().split("-")[1].trim();
                    if(elementValue.equals(currentValue)){
                        listComponent.setSelectedIndex(i);
                    }
                }
            }
        }
    }
    
    public String getElementName() {
        return elementName;
    }
    
    public String getElementValue() {
        return elementValue;
    }
    
    public JComponent getComponent() {
        return component;
    }
    
    public String getDisplayedValue(){
        String displayedValue = null;
        
        if(component instanceof JTextComponent){
            JTextComponent textComponent = (JTextComponent)component;
            displayedValue = textComponent.getText();
        }if(component instanceof JComboBox){
            JComboBox listComponent = (JComboBox)component;
            displayedValue = listComponent.getSelectedItem().toString().split("-")[1].trim();
        }
        
        return displayedValue;
    }
}
