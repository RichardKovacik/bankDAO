package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class IconListRenderer extends DefaultListCellRenderer {
    private Map<Object, Icon> icons;

    public IconListRenderer(Map<Object, Icon> icons) {
        this.icons = icons;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                   boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list,value, index, isSelected, cellHasFocus);
        Icon icon = icons.get(value);
        label.setIcon(icon);
        return label;
    }
}
