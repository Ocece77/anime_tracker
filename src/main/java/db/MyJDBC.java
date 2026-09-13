package db;

import constants.CommonConstants;
import object.Anime;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyJDBC {
  // add anime
    public static boolean addAnime(
            String title,
            int season,
            int totalEpisodes,
            boolean progressState,
            String link,
            LocalDate nextSeason,
            String backgroundImage,
            String stickers
    ) {

        String query = """
            INSERT INTO anime
            (title, season, total_episodes, progress_state,
             link, next_season, background_image, stickers)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (
                Connection connection = DriverManager.getConnection(
                        CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD
                );

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setString(1, title);
            statement.setInt(2, season);
            statement.setInt(3, totalEpisodes);
            statement.setBoolean(4, progressState);
            statement.setString(5, link);
            statement.setDate(6, java.sql.Date.valueOf(nextSeason));
            statement.setString(7, backgroundImage);
            statement.setString(8, stickers);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    // update anime

    public static boolean updateAnime( int idAnime,
                                       String title,
                                       int season,
                                       int totalEpisodes,
                                       boolean progressState,
                                       String link,
                                       LocalDate nextSeason,
                                       String backgroundImage,
                                       String stickers ){
        String query = "UPDATE " + CommonConstants.DB_ANIME_TABLE_NAME +
                " SET title = ?, " +
                "season = ?, " +
                "total_episodes = ?, " +
                "progress_state = ?, " +
                "link = ?, " +
                "next_season = ?, " +
                "background_image = ?, " +
                "stickers = ? " +
                "WHERE idanime = ?";
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, title);
            statement.setInt(2, season);
            statement.setInt(3, totalEpisodes);
            statement.setBoolean(4, progressState);
            statement.setString(5, link);
            statement.setDate(6, java.sql.Date.valueOf(nextSeason));
            statement.setString(7, backgroundImage);
            statement.setString(8, stickers);
            statement.setInt(9, idAnime);
            statement.executeUpdate();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    // Delete anime

    public static boolean deleteAnime( int idAnime){
        String query = "DELETE FROM "
                + CommonConstants.DB_ANIME_TABLE_NAME + " WHERE idanime= ?";
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idAnime);
            statement.executeUpdate();

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Get one anime
    public static Anime getAnimeById(int idAnime) {

        String query =
                "SELECT * FROM " +
                        CommonConstants.DB_ANIME_TABLE_NAME +
                        " WHERE idanime = ?";

        try (
                Connection connection = DriverManager.getConnection(
                        CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD
                );

                PreparedStatement statement =
                        connection.prepareStatement(query)
        ) {

            statement.setInt(1, idAnime);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Anime(
                        resultSet.getInt("idanime"),
                        resultSet.getString("title"),
                        resultSet.getInt("season"),
                        resultSet.getInt("total_episodes"),
                        resultSet.getBoolean("progress_state"),
                        resultSet.getString("link"),
                        resultSet.getDate("next_season").toLocalDate(),
                        resultSet.getString("background_image"),
                        resultSet.getString("stickers")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    // Get one anime
    public static List<Anime> getAllAnime() {

        List<Anime> animeList = new ArrayList<>();

        String query =
                "SELECT * FROM " +
                        CommonConstants.DB_ANIME_TABLE_NAME;

        try (
                Connection connection = DriverManager.getConnection(
                        CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD
                );

                PreparedStatement statement =
                        connection.prepareStatement(query);

                ResultSet resultSet =
                        statement.executeQuery();
        ) {

            while (resultSet.next()) {
                Date sqlDate = resultSet.getDate("next_season");

                LocalDate nextSeason = null;

                if (sqlDate != null) {
                    nextSeason = sqlDate.toLocalDate();
                }
                Anime anime = new Anime(
                        resultSet.getInt("idanime"),
                        resultSet.getString("title"),
                        resultSet.getInt("season"),
                        resultSet.getInt("total_episodes"),
                        resultSet.getBoolean("progress_state"),
                        resultSet.getString("link"),
                        nextSeason,
                        resultSet.getString("background_image"),
                        resultSet.getString("stickers")
                );

                animeList.add(anime);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animeList;
    }

    public static boolean checkAnime(String animeTitle) {

        String query =
                "SELECT * FROM " +
                        CommonConstants.DB_ANIME_TABLE_NAME +
                        "WHERE title = ?";

        try (
                Connection connection = DriverManager.getConnection(
                        CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD
                );

                PreparedStatement checkAnimeExists =
                        connection.prepareStatement(query)
        ) {

            checkAnimeExists.setString(1, animeTitle);

            ResultSet resultSet =checkAnimeExists.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}