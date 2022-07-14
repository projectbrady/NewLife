package me.projectbrady.newlife.db;

import me.projectbrady.newlife.models.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

public class Database {

    private Connection connection;

    public Connection getConnection() throws SQLException{

       if(connection != null) {
           return connection;
       }

        String url = "";
        String user = "";
        String password = "";

        //connect to database
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            Bukkit.getLogger().info("[NewLife] Database Connected");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("[NewLife] Database Not Connected");
        }

        return this.connection;
    }

    public void initializeDatabase() throws SQLException{

            Statement statement = getConnection().createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS player_stats(uuid varchar(50) primary key, deaths int, kills int, balance double)";
            statement.execute(sql);

            statement.close();

            Bukkit.getLogger().info("[NewLife] Table - player_stats - created in database!");

    }

    public PlayerStats findPlayerStatsByUUID(String uuid) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_stats WHERE uuid = ?");
        statement.setString(1, uuid);
        ResultSet resultSet = statement.executeQuery();

        PlayerStats playerStats;
        if(resultSet.next()){
            playerStats = new PlayerStats(resultSet.getString("uuid"), resultSet.getInt("deaths"), resultSet.getInt("kills"),  resultSet.getDouble("balance"));
            statement.close();
            return playerStats;
        }
        statement.close();
        return null;
    }

    public void createPlayerStats(PlayerStats stats) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO player_stats(uuid, deaths, kills, balance) VALUES (?, ?, ?, ?)");

        statement.setString(1, stats.getUuid());
        statement.setInt(2, stats.getDeaths());
        statement.setInt(3, stats.getKills());
        statement.setDouble(4, stats.getBalance());

        statement.executeUpdate();
        statement.close();

    }

    public void updatePlayerStats(PlayerStats stats) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_stats SET deaths = ?, kills = ?, balance = ? WHERE uuid = ?");

        statement.setInt(1, stats.getDeaths());
        statement.setInt(2, stats.getKills());
        statement.setDouble(3, stats.getBalance());
        statement.setString(4, stats.getUuid());

        statement.executeUpdate();
        statement.close();


    }

}
