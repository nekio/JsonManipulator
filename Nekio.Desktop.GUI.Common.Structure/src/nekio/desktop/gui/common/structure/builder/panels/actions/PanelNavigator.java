package nekio.desktop.gui.common.structure.builder.panels.actions;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import nekio.desktop.gui.common.structure.builder.Worker;
import nekio.desktop.gui.common.structure.builder.abstracts.IPanel;
import nekio.desktop.gui.common.structure.builder.constants.Globals;
import nekio.desktop.gui.common.structure.builder.forms.Form;

public class PanelNavigator extends IPanel{
    private Form form;
    private List<String> jsonFiles;
    
    private int min;
    private int max;
    private int currentFileIndex;
    
    private JButton btnFirst;
    private JButton btnPrevious;
    private JLabel lblIndex;
    private JButton btnNext;
    private JButton btnLast;
    
    public PanelNavigator(Form form){
        this.form = form;
        this.jsonFiles = Worker.getJsonFiles();
        
        this.min = 0;
        this.max = jsonFiles.size() - 1;
        this.currentFileIndex = 0;
        
        super.activate();
        
        initialize();
    }
    
    @Override
    protected void addComponents() {
        this.setLayout(new FlowLayout());
        
        btnFirst = new JButton("<<");
        this.add(btnFirst);
        
        btnPrevious = new JButton("<");
        this.add(btnPrevious);
        
        lblIndex = new JLabel("" + getCurrentFileIndex());
        this.add(lblIndex);
        
        btnNext = new JButton(">");
        this.add(btnNext);
        
        btnLast = new JButton(">>");
        this.add(btnLast);
    }

    @Override
    protected void addListeners() {
        btnFirst.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                first();
            }
        });
        
        btnPrevious.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                previous();
            }
        });
        
        btnNext.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                 next();
            }
        });
        
        btnLast.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                 last();
            }
        });
    }

    @Override
    protected void initialize() {}
    
    public void lock(){
        enableAll(false);
    }
    
    public void unlock(){
        loadIndex();
    }
    
    private void first(){
        currentFileIndex = min;
        loadIndex();
    }
    
    private void previous(){
        currentFileIndex--;
        loadIndex();
    }
    
    private void next(){
        currentFileIndex++;
        loadIndex();
    }
    
    private void last(){
        currentFileIndex = max;
        loadIndex();
    }
    
    private void loadIndex(){
        enableAll(true);
            
        if(getCurrentFileIndex() == min){
            btnFirst.setEnabled(false);
            btnPrevious.setEnabled(false);
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        }else if(getCurrentFileIndex() == max){
            btnFirst.setEnabled(true);
            btnPrevious.setEnabled(true);
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        }
        
        if(min + max < 1){
            enableAll(false);
        }
        
        lblIndex.setText("" + getCurrentFileIndex());
        loadJsonFile(getCurrentFileIndex());
    }
    
    private void enableAll(boolean enable){
        btnFirst.setEnabled(enable);
        btnPrevious.setEnabled(enable);
        btnNext.setEnabled(enable);
        btnLast.setEnabled(enable);
    }
    
    public void loadJsonFile(int index){
        try{
            Worker.loadContent(form, jsonFiles.get(index));
            Worker.lockViewContent(form);
        }catch(Exception e){
            Globals.showMessage("PanelNavigator.loadJsonFile()\n" + e.toString());
        }
    }

    public int getCurrentFileIndex() {
        return currentFileIndex;
    }

    public String getCurrentFile() {
        return form.getPath();
    }
}
