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

public class PanelScalars extends IPanel{
    private Map<String, String> mapScalars;
    private List<Field> lstScalars;
    
    public PanelScalars(Map<String, String> mapScalars){
        this.mapScalars = mapScalars;
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        JPanel pnlScalarTitle = new JPanel(new BorderLayout());
        JPanel pnlScalarFields = new JPanel();
        
        // Title
        pnlScalarTitle.add(new JLabel(Globals.formatText("Escalares")));
        pnlScalarTitle.setBackground(Color.LIGHT_GRAY);
        this.add(pnlScalarTitle, BorderLayout.NORTH);
        
        // Fields
        pnlScalarFields.setLayout(new GridLayout(mapScalars.size(), 1));
        
        lstScalars = new ArrayList<Field>();
        for(Map.Entry<String, String> scalar : mapScalars.entrySet()){
            lstScalars.add(new Field(scalar.getKey(), scalar.getValue(), new JTextField()));
        }
        
        for(Field field : lstScalars){
            pnlScalarFields.add(field);
        }
        
        this.add(pnlScalarFields, BorderLayout.CENTER);
    }
    
    @Override
    protected void addListeners() {}

    @Override
    protected void initialize() {}
    
    public void reinitialize() {
        for(Field field : lstScalars){
            field.setElementValue("");
        }
    }
    
    public void lockContent(){
        for(Field field : lstScalars){
            field.setActivation(false);
        }
    }
    
    public void unlockContent(){
        for(Field field : lstScalars){
            field.setActivation(true);
        }
    }
    
    public Map<String, String> getMapScalars() {
        return mapScalars;
    }
    
    public List<Field> getLstScalars() {
        return lstScalars;
    }
    
    public Map<String, String> getDisplayedValues() {
        Map<String, String> exposedValues = new HashMap<String, String>();
        
        for(Field field : lstScalars){
            exposedValues.put(field.getElementName(), field.getDisplayedValue());
        }
        
        return exposedValues;
    }

}
