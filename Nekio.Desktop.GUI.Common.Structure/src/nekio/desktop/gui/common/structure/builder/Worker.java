package nekio.desktop.gui.common.structure.builder;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nekio.desktop.gui.common.structure.builder.constants.Globals;
import nekio.desktop.gui.common.structure.builder.forms.Form;
import nekio.desktop.gui.common.structure.builder.panels.MainPanel;
import nekio.desktop.gui.common.structure.builder.panels.content.PanelGeneral;
import nekio.library.common.structure.helpers.ArraysHelper;
import nekio.library.common.structure.helpers.JsonHelper;
import nekio.library.common.structure.helpers.PathHelper;
import nekio.library.common.structure.model.Content;
import nekio.library.common.structure.model.DetailContent;
import nekio.library.common.structure.model.Dictionary;
import nekio.library.common.structure.model.StructureDefinition;
import nekio.library.common.structure.paths.ContentNamer;
import nekio.library.common.structure.paths.Paths;
import org.json.JSONObject;

public class Worker {
    public static List<String> getJsonFiles(){
        PathHelper pathHelper = new PathHelper();
        
        List<String> jsonFiles = new ArrayList<String>();
        for(String path : pathHelper.getAvailablePaths(Paths.getContent(), pathHelper.getPaths())){
            
            List<String> availableFiles = pathHelper.getAvailableFiles(path);
            for(String availableFile : availableFiles){
                String jsonFile = path + availableFile;
                jsonFiles.add(jsonFile);
            }
            
        }
        
        return jsonFiles;
    }
    
    public static Form startModule(String mainPath){
        Form form = null;
        
        Paths.setMain(mainPath);
        
        try{
            ArraysHelper helper = new ArraysHelper();
            List<Dictionary> catalogs = helper.getCatalogs(Paths.getCatalogs());
            StructureDefinition structure = helper.getStructure(Paths.getDefinition());

            StructureTranslator translator = new StructureTranslator();
            translator.defineStructure(structure);

            form = new Form(translator, catalogs, Paths.getMain());
        }catch(Exception e){
            Globals.showMessage("Worker.startModule()\n" + e.toString());
        }
        
        return form;
    }
    
    public static void loadContent(Form form, String jsonFilePath){
        try{
            form.setPath(jsonFilePath);
            
            MainPanel mainPanel = form.getMainPanel();
            if(mainPanel != null){
                mainPanel.loadContent(readData(jsonFilePath));
            }
        }catch(Exception e){
            Globals.showMessage("Worker.loadContent()\n" + e.toString());
        }
    }
    
    public static Content readData(String jsonFilePath){
        Content content = null;
        
        try{
            ArraysHelper helper = new ArraysHelper();
            content = helper.getContent(jsonFilePath); 
        }catch(Exception e){
            Globals.showMessage("Worker.readData()\n" + e.toString());
        }
        
        return content;
    }
    
    // Create json file in specific path
    public static void writeData(String folder, String filename, Content content){
        try{
            JSONObject json = JsonHelper.getJsonFromContent(content);
            ContentNamer.createFile(json, folder, filename);
        }catch(Exception e){
            Globals.showMessage("Worker.writeData()\n" + e.toString());
        }
    }
    
    // Create json file in dynamic path (by current date)
    public static void writeData(Content content){
        try{
            JSONObject json = JsonHelper.getJsonFromContent(content);
            ContentNamer.createFile(json);
        }catch(Exception e){
            Globals.showMessage("Worker.writeData()\n" + e.toString());
        }
    }
    
    public static void lockViewContent(Form form){
        MainPanel mainPanel = form.getMainPanel();
        if(mainPanel != null){
            mainPanel.lockContent();
        }
    }
    
    public static void unlockViewContent(Form form){
        MainPanel mainPanel = form.getMainPanel();
        if(mainPanel != null){
            mainPanel.unlockContent();
        }
    }
    
    public static void newViewContent(Form form){
        MainPanel mainPanel = form.getMainPanel();
        if(mainPanel != null){
            mainPanel.reinitialize();
            mainPanel.unlockContent();
        }
    }
    
    public static void writeViewContent(Form form){
        writeData(getViewContent(form));
    }
    
    public static void writeViewContent(Content content){
        writeData(content);
    }
    
    public static Content getViewContent(Form form){
        Content content = null;
        
        MainPanel mainPanel = form.getMainPanel();
        if(mainPanel != null){
            content = new Content();

            Map<String, String> singleFields = new HashMap<String, String>();
            singleFields = mainPanel.getPnlFields().getPnlScalars().getDisplayedValues();

            List<Dictionary> pairFields = new ArrayList<Dictionary>();
            for(PanelGeneral pnlGeneral : mainPanel.getPnlFields().getPnlGenerals().getLstGeneralPanels()){
                pairFields.add(pnlGeneral.getDisplayedValues());
            }

            List<DetailContent> detailFields = new ArrayList<DetailContent>();
            for(DetailContent detail : mainPanel.getPnlFields().getPnlDetails().getDisplayedValues()){
                detailFields.add(detail);
            }

            content.setSingleFields(singleFields);
            content.setPairFields(pairFields);
            content.setDetailFields(detailFields);
        }
        
        return content;
    }
}
