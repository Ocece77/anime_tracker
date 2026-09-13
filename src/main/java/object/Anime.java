package object;

import java.time.LocalDate;

public class Anime {
    private int idAnime;
    private String title;
    private int season;
    private int totalEpisodes;
    private boolean progressState;
    private String link;
    private LocalDate nextSeason;
    private String backgroundImage;
    private String stickers;

    public Anime(int idAnime , String title,
    int season,
    int totalEpisodes,
    boolean progressState,
    String link,
    LocalDate nextSeason,
    String backgroundImage,
    String stickers){
        this.idAnime=idAnime;
        this.title =title;
        this.season =season;
        this.totalEpisodes =totalEpisodes;
        this.progressState =progressState;
        this.link =link;
        this.nextSeason =nextSeason;
        this.backgroundImage=backgroundImage;
        this.stickers =stickers ;
    }

    public void showInformation(){
        System.out.println(idAnime);
        System.out.println(title);
        System.out.println(season);
        System.out.println(totalEpisodes);
        System.out.println(progressState);
        System.out.println(link);
        System.out.println(nextSeason);
        System.out.println(backgroundImage);
        System.out.println(stickers);

    }

    // getters
    public int getIdAnime() {
        return idAnime;
    }
    public String getTitle() {
        return title;
    }

    public String getSeason() {
        return Integer.toString(season);
    }

    public String getTotalEpisodes() {
        return Integer.toString(totalEpisodes);
    }

    public boolean isProgressState() {
        return progressState;
    }

    public String getLink() {
        return link;
    }

    public LocalDate getNextSeason() {
        return nextSeason;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getStickers() {
        return stickers;
    }
}
