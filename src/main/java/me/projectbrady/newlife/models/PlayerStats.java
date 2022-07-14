package me.projectbrady.newlife.models;

public class PlayerStats {

    private String uuid;
    private int deaths;
    private int kills;
    private double balance;

    public PlayerStats(String uuid, int deaths, int kills, double balance) {
        this.uuid = uuid;
        this.deaths = deaths;
        this.kills = kills;
        this.balance = balance;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
