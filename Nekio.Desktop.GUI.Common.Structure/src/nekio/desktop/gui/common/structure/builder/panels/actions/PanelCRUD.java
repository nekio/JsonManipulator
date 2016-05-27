package nekio.desktop.gui.common.structure.builder.panels.actions;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import nekio.desktop.gui.common.structure.builder.Worker;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import nekio.desktop.gui.common.structure.builder.forms.Form;

public class PanelCRUD extends IPanel{
    private Form form;
    private PanelNavigator navigator;
    
    private JButton btnNewContent;
    private JButton btnEditContent;
    private JButton btnDeleteContent;
    
    private JButton btnCreateContent;
    private JButton btnCancelEditContent;
    
    public PanelCRUD(Form form, PanelNavigator navigator){
        this.form = form;
        this.navigator = navigator;
        
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        this.setLayout(new FlowLayout());
        
        btnNewContent = new JButton("New");
        this.add(btnNewContent);
        
        btnEditContent = new JButton("Edit");
        this.add(btnEditContent);
        
        btnDeleteContent = new JButton("Delete");
        this.add(btnDeleteContent);
        
        btnCreateContent = new JButton("Create");
        this.add(btnCreateContent);        
        
        btnCancelEditContent = new JButton("Cancel");
        this.add(btnCancelEditContent);
    }

    @Override
    protected void addListeners() {
        btnNewContent.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                newContent();
            }
        });
        
        btnEditContent.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                editContent();
            }
        });
        
        btnDeleteContent.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                deleteFile();
            }
        });
        
        btnCreateContent.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                createContent();
            }
        });        
        
        btnCancelEditContent.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                cancelEditContent();
            }
        });
    }

    @Override
    protected void initialize() {
        passiveMode();
    }
    
    private void newContent(){
        Worker.newViewContent(form);
        activeMode();
    }
    
    private void editContent(){
        Worker.unlockViewContent(form);
        activeMode();
    }
    
    private void deleteFile(){
        String currentFile = navigator.getCurrentFile();
        
        new File(currentFile).delete();
        navigator.loadJsonFile(navigator.getCurrentFileIndex() + 1);
    }
    
    private void createContent(){
        Worker.writeViewContent(form);
        passiveMode();
    }
    
    private void cancelEditContent(){
        navigator.loadJsonFile(navigator.getCurrentFileIndex());
        passiveMode();
    }
    
    private void passiveMode(){
        navigator.unlock();
        
        btnNewContent.setEnabled(true);
        btnEditContent.setEnabled(true);
        btnCreateContent.setEnabled(false);
        btnCancelEditContent.setEnabled(false);
    }
    
    private void activeMode(){
        navigator.lock();
        
        btnNewContent.setEnabled(false);
        btnEditContent.setEnabled(false);
        btnCreateContent.setEnabled(true);
        btnCancelEditContent.setEnabled(true);
    }
}
