package components;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class FilterListComponent extends JPanel implements ListSelectionListener {

    private final JLabel etiquetteChoice;
    private final JList<String> filterList;

    public FilterListComponent() {

        setLayout(new BorderLayout());

        etiquetteChoice = new JLabel("Les plus récent");

        String[] choices = {
                "Les plus récent",
                "Terminés",
                "En cours",
                "Les plus anciens"
        };

        filterList = new JList<>(choices);

        filterList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        filterList.addListSelectionListener(this);

        add(etiquetteChoice, BorderLayout.WEST);
        add(filterList, BorderLayout.WEST);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (!e.getValueIsAdjusting()) {

            String selected =
                    filterList.getSelectedValue();

            if (selected != null) {
                etiquetteChoice.setText(selected);
            }
        }
    }
}