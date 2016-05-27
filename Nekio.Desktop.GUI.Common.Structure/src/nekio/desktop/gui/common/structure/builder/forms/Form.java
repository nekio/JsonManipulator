package nekio.desktop.gui.common.structure.builder.forms;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import nekio.desktop.gui.common.structure.builder.StructureTranslator;
import nekio.desktop.gui.common.structure.builder.abstracts.IFrame;
import nekio.desktop.gui.common.structure.builder.panels.MainPanel;
import nekio.desktop.gui.common.structure.builder.panels.actions.PanelCRUD;
import nekio.desktop.gui.common.structure.builder.panels.actions.PanelNavigator;
import nekio.library.common.structure.model.Dictionary;

public class Form extends IFrame{
    private MainPanel mainPanel;
    private PanelNavigator navigator;
    private PanelCRUD crud;
    
    private StructureTranslator translator;
    private List<Dictionary> catalogs;
    private String path;
    
    public Form(StructureTranslator translator, List<Dictionary> catalogs, String path){
        this.translator = translator;
        this.catalogs = catalogs;
        this.path = path;
        
        activate();
    }
    
    @Override
    protected void activate(){
        this.setTitle(getPath());
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(700, 800));
        this.setLocationRelativeTo(null);
        
        addComponents();
        addListeners();
        
        this.setVisible(true);
    }
    
    @Override
    protected void addComponents() {
        mainPanel = new MainPanel(translator);
        navigator = new PanelNavigator(this);
        crud = new PanelCRUD(this, navigator);
        
        this.add(navigator, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(crud, BorderLayout.SOUTH);
    }
    
    @Override
    protected void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                exit();
            }
        });
    }
    
    @Override
    public void initialize(){
        mainPanel.loadCatalogs(catalogs);
    }
    
    private void exit() {
        this.setVisible(false);
        this.dispose();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        
        this.setTitle(path);
    }
}
