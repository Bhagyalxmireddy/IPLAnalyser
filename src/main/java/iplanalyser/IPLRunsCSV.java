package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLRunsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "SR", required = true)
    public double strikingRate;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    @CsvBindByName(column = "100", required = true)
    public int hundred;

    @CsvBindByName(column = "50", required = true)
    public int fifty;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "player='" + player + '\'' +
                ", average='" + average + '\'' +
                ", matches='" + matches + '\'' +
                ",runs='" + runs + '\'' +
                ",strikingRate='" + strikingRate + '\'' +
                ",four='" + four + '\'' +
                ",six='" + six + '\'' +
                ",hundred='" + hundred + '\'' +
                ",fifty='" + fifty + '\'' +
                '}';
    }
}
