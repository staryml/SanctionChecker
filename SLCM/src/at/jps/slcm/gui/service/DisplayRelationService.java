package at.jps.slcm.gui.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

import at.jps.slcm.gui.model.DisplayRelation;

public class DisplayRelationService {

    TableModel tableModel;

    public void setModel(TableModel tableModel2) {
        this.tableModel = tableModel2;
    }

    public synchronized List<DisplayRelation> displayAllFields() {

        List<DisplayRelation> fields = new ArrayList<DisplayRelation>();
        if (tableModel != null) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {

                DisplayRelation dnd = new DisplayRelation();
                fields.add(dnd);

                dnd.setId((long) i);
                dnd.setRelation((String) tableModel.getValueAt(i, 0));
                dnd.setEntity((String) tableModel.getValueAt(i, 1));
                dnd.setType((String) tableModel.getValueAt(i, 2));

            }
        }
        return fields;
    }

}
