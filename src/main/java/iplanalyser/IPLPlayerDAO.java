package iplanalyser;

public class IPLPlayerDAO {

    public int hundred;
    public int fifty;
    public int wickets;
    public double economyRate;
    public int runs;
    public int four;
    public int six;
    public double strikingRate;
    public  String player;
    public double average;
    public int fourWickets;
    public int fiveWickets;

    public IPLPlayerDAO(IPLRunsCSV iplrunsCSV) {
        player = iplrunsCSV.player;
        average = iplrunsCSV.average;
        strikingRate = iplrunsCSV.strikingRate;
        six = iplrunsCSV.six;
        four = iplrunsCSV.four;
        runs = iplrunsCSV.runs;
        hundred = iplrunsCSV.hundred;
        fifty = iplrunsCSV.fifty;
    }

    public IPLPlayerDAO(IPLWktsCSV iplwktsCSV) {
        player = iplwktsCSV.player;
        average = iplwktsCSV.average;
        strikingRate = iplwktsCSV.strikingRate;
        economyRate = iplwktsCSV.economyRate;
        fourWickets = iplwktsCSV.fourWickets;
        fiveWickets = iplwktsCSV.fiveWickets;
        wickets = iplwktsCSV.wickets;
    }

    public IPLPlayerDAO(String player, int runs, double average, double strikingRate, int four, int six ) {
        this.player = player;
        this.runs = runs;
        this.average = average;
        this.strikingRate = strikingRate;
        this.four = four;
        this.six = six;
    }
}
