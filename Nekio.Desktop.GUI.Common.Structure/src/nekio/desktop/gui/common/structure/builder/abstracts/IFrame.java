package nekio.desktop.gui.common.structure.builder.abstracts;

/**
 *
 * @author Nekio <nekio@outlook.com>
 */

import javax.swing.JFrame;

public abstract class IFrame extends JFrame{
    protected abstract void addComponents();
    protected abstract void addListeners();
    protected abstract void activate();
    protected abstract void initialize();
}
