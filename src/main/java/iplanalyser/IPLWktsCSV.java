package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLWktsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int Innings;

    @CsvBindByName(column = "Ov", required = true)
    public double overs;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "BBI", required = true)
    public int BestBowling;

    @CsvBindByName(column = "SR", required = true)
    public double strikingRate;

    @CsvBindByName(column = "Econ", required = true)
    public double economyRate;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    @Override
    public String toString() {
        return "IPLWktsCSV{" +
                "player='" + player + '\'' +
                ", average='" + average + '\'' +
                ", matches='" + matches + '\'' +
                ", Innings='" + Innings + '\'' +
                ",overs='" + overs + '\'' +
                ",runs='" + runs + '\'' +
                ",BestBowling='" + BestBowling + '\'' +
                ",strikingRate='" + strikingRate + '\'' +
                ",economyRate='" + economyRate + '\'' +
                ",wickets='" + wickets + '\'' +
                ",fourWickets='" + fourWickets + '\'' +
                ",fiveWickets='" + fiveWickets + '\'' +
                '}';
    }

}
