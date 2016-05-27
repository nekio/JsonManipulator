package nekio.desktop.gui.common.structure.builder;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nekio.desktop.gui.common.structure.builder.constants.Globals;
import nekio.library.common.structure.enums.DataType;
import nekio.library.common.structure.model.StructureDefinition;

public class StructureTranslator {
    private StructureDefinition structure;
    
    private JLabel lblModule;
    private Map<String, JComboBox> cmbCatalogs;
    private Map<String, JComponent> cmpFields;
    
    public StructureTranslator(){
        this.lblModule = new JLabel();
        this.cmbCatalogs = new HashMap<String, JComboBox>();
        this.cmpFields = new HashMap<String, JComponent>();
    }
    
    public void defineStructure(StructureDefinition structure){
        this.structure = structure;
        this.lblModule.setText(Globals.formatText(structure.getModule()));
        
        for(String catalog: structure.getCatalogs()){
            this.cmbCatalogs.put(catalog, new JComboBox());
        }
        
        for(Map.Entry field: structure.getFields().entrySet()){
            String fieldName = field.getKey().toString();
            DataType dataType = (DataType)field.getValue();
            
            switch(dataType){
                case SimpleText:
                    cmpFields.put(fieldName, new JTextField());
                    break;
                case LargeText:
                    cmpFields.put(fieldName, new JTextArea());
                    break;
                case Boolean:
                    cmpFields.put(fieldName, new JCheckBox());
                    break;
                case Integer:
                    cmpFields.put(fieldName, new JTextField());
                    break;
                case Decimal:
                    cmpFields.put(fieldName, new JTextField());
                    break;
                case Date:
                    cmpFields.put(fieldName, new JTextField());
                    break;
                case Hour:
                    cmpFields.put(fieldName, new JTextField());
                    break;
                default:
                    System.out.println("Oops!");
            }
        }
        
    }

    public JLabel getLblModule() {
        return lblModule;
    }

    public Map<String, JComboBox> getCmbCatalogs() {
        return cmbCatalogs;
    }

    public Map<String, JComponent> getCmpFields() {
        return cmpFields;
    }

    public StructureDefinition getStructure() {
        return structure;
    }
}
