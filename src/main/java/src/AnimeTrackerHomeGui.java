package src;

import constants.CommonConstants;
import db.MyJDBC;
import object.Anime;
import utils.RoundedBorder;
import utils.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnimeTrackerHomeGui extends JFrame {

    public AnimeTrackerHomeGui() {

        super("Animé Tracker");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(714, 714);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(
                CommonConstants.BACKGROUND_COLOR
        );

        addGUIComponents();
    }

    private void addGUIComponents() {

        // Bannière
        ImageIcon topImage =
                new ImageIcon("assets/background_image.png");

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(topImage);
        imageLabel.setBounds(0, 0, 714, 177);

        add(imageLabel);


        // Récupérer les animés depuis MySQL
        List<Anime> animeList =
                MyJDBC.getAllAnime();


        // Panel qui contient toutes les cartes
        JPanel animeListPanel = new JPanel();

        animeListPanel.setLayout(
                new BoxLayout(
                        animeListPanel,
                        BoxLayout.Y_AXIS
                )
        );

        animeListPanel.setBackground(
                CommonConstants.BACKGROUND_COLOR
        );


        // Créer une carte pour chaque animé
        for (Anime anime : animeList) {

            JPanel animePanel =
                    createAnimePanel(anime);

            animeListPanel.add(animePanel);

            // espace entre les cartes
            animeListPanel.add(
                    Box.createVerticalStrut(15)
            );
        }


        // Scroll
        JScrollPane scrollPane =
                new JScrollPane(animeListPanel);

        scrollPane.setBounds(
                32,
                210,
                650,
                450
        );

        scrollPane.setBorder(null);

        add(scrollPane);
    }


    // Crée une carte pour UN animé
    private JPanel createAnimePanel(Anime anime) {

        JPanel animePanel = new JPanel();

        animePanel.setLayout(null);

        animePanel.setPreferredSize(
                new Dimension(620, 160)
        );


        animePanel.setBackground(Color.WHITE);

        animePanel.setBorder(
                new RoundedBorder(
                        CommonConstants.BORDER_COLOR,
                        1,
                        20
                )
        );


        // Titre
        JLabel animeTitle =
                new JLabel(anime.getTitle());

        animeTitle.setForeground(
                CommonConstants.LIGHT_BLACK_COLOR
        );

        animeTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        35
                )
        );

        animeTitle.setBounds(
                15,
                20,
                450,
                60
        );


        // Saison
        JLabel season =
                new JLabel(
                        "Saison " + anime.getSeason()
                );

        season.setForeground(
                CommonConstants.ORANGE_COLOR
        );

        season.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        season.setBounds(
                15,
                50,
                200,
                60
        );


        // Episodes
        JLabel episodeRemainingIndication =
                new JLabel(
                        "Il reste 5 épisodes à regarder"
                );

        episodeRemainingIndication.setForeground(
                Color.GRAY
        );

        episodeRemainingIndication.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        episodeRemainingIndication.setBounds(
                15,
                70,
                250,
                60
        );


        // Bouton
        RoundedButton watchButton =
                new RoundedButton(
                        "Regarder",
                        20
                );

        watchButton.setBounds(
                490,
                100,
                110,
                50
        );

        watchButton.setBackground(
                CommonConstants.LIGHT_BLACK_COLOR
        );

        watchButton.setForeground(
                Color.WHITE
        );


        animePanel.add(watchButton);
        animePanel.add(animeTitle);
        animePanel.add(season);
        animePanel.add(episodeRemainingIndication);


        // IMPORTANT
        return animePanel;
    }
}