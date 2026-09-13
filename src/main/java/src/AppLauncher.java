package src;

import db.MyJDBC;

import javax.swing.*;
import java.time.LocalDate;

public class AppLauncher {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnimeTrackerHomeGui().setVisible(true);

                System.out.println(MyJDBC.addAnime("Parasite",1,14, false, "", LocalDate.of(2027, 4, 15),
                        "",""));
            }
        });
    }
}
