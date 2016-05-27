package nekio.desktop.gui.common.structure.builder.panels;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nekio.desktop.gui.common.structure.builder.StructureTranslator;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import nekio.desktop.gui.common.structure.builder.components.Catalog;
import nekio.desktop.gui.common.structure.builder.components.FieldCatalogLinker;
import nekio.library.common.structure.model.Content;
import nekio.library.common.structure.model.Dictionary;

public final class MainPanel extends IPanel{
    private final StructureTranslator translator;
    private List<FieldCatalogLinker> fieldLinker;
    
    private PanelModule pnlModule;
    private PanelFields pnlFields;
    private PanelCatalogs pnlCatalogs;
    
    public MainPanel(StructureTranslator translator){
        this.translator = translator;
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents(){
        this.pnlModule = new PanelModule(translator.getLblModule());
        this.pnlFields = new PanelFields(translator.getCmpFields());       
        this.pnlCatalogs = new PanelCatalogs(translator.getCmbCatalogs());
        
        this.add(this.pnlModule, BorderLayout.NORTH);
        this.add(this.pnlFields, BorderLayout.CENTER);
        this.add(this.pnlCatalogs, BorderLayout.SOUTH);
    }
    
    @Override
    protected void addListeners() {}
    
    @Override
    protected void initialize(){}
    
    public void reinitialize(){
        this.pnlFields.reinitialize();
    }
    
    public void loadCatalogs(List<Dictionary> catalogs){
        List<Catalog> lstCatalogs = this.pnlCatalogs.getLstCatalogs();
        
        for(Catalog combo : lstCatalogs){            
            for(Dictionary dictionary : catalogs) {
                String dictionaryTitle = dictionary.getTitle().replaceAll(nekio.library.common.structure.constants.Globals.EXTENSION, "");
                
                if(dictionaryTitle.equals(combo.getElementName())){
                    Map content = dictionary.getContent();
                    
                    combo.setElementDescription(dictionary.getDescription());
                    for(Object value : content.entrySet()){
                        HashMap.Entry<Integer, String> castValue = (HashMap.Entry<Integer, String>)value;
                        combo.getListElement().addItem(castValue.getKey() + " - " + castValue.getValue());
                    }
                }
            }
        }
    }
    
    public void loadContent(Content content){
        this.pnlFields.loadContent(content);
        this.pnlFields.hideCatalogs(pnlCatalogs.getLstCatalogs());
        
        hidePnlCatalogs();
    }
    
    public void lockContent(){
        pnlFields.getPnlScalars().lockContent();
        pnlFields.getPnlGenerals().lockContent();
        pnlFields.getPnlDetails().lockContent();
    }
    
    public void unlockContent(){
        pnlFields.getPnlScalars().unlockContent();
        pnlFields.getPnlGenerals().unlockContent();
        pnlFields.getPnlDetails().unlockContent();
    }
    
    private void hidePnlCatalogs(){
        this.remove(this.pnlCatalogs);
    }

    public PanelModule getPnlModule() {
        return pnlModule;
    }

    public PanelFields getPnlFields() {
        return pnlFields;
    }

    public PanelCatalogs getPnlCatalogs() {
        return pnlCatalogs;
    }

}
