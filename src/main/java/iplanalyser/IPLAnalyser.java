package iplanalyser;

import com.google.gson.Gson;
import javafx.beans.value.ObservableBooleanValue;

import java.util.*;

public class IPLAnalyser {

    List<IPLPlayerDAO> playerCSVList = null;
    Map<String,IPLPlayerDAO> playerCSVMap = new HashMap<>();
    private Map<String, IPLPlayerDAO> batsmanData;


    public IPLAnalyser(){
        this.playerCSVList = new ArrayList<IPLPlayerDAO>();
        this.playerCSVMap = new HashMap<String, IPLPlayerDAO>();
    }

    public int loadIPLRunsData(String csvFilePath) throws IplCricketAnalyserException {
        playerCSVMap = new IPLPlayersLoader().loadIPLPlayersData(IPLRunsCSV.class,csvFilePath);
        return playerCSVMap.size();
    }
    public int loadIPLWktsData(String csvFilePath) throws IplCricketAnalyserException {
        playerCSVMap = new IPLPlayersLoader().loadIPLPlayersData(IPLWktsCSV.class,csvFilePath);
        return playerCSVMap.size();

    }
    public int loadIPLLeagueData(String csvPath1, String csvPath2) throws IplCricketAnalyserException {
        playerCSVMap = new IPLPlayersLoader().loadIPLLeagueData(csvPath1,csvPath2);
        return playerCSVMap.size();
    }

    private void sort(Comparator<IPLPlayerDAO> playerCSVComparator) {
        for (int i=0;i<playerCSVList.size()-1;i++) {
            for (int j = 0; j < playerCSVList.size()-1; j++) {
                IPLPlayerDAO census1 = playerCSVList.get(j);
                IPLPlayerDAO census2 = playerCSVList.get(j + 1);
                if (playerCSVComparator.compare(census1, census2) > 0) {
                    playerCSVList.set(j, census2);
                    playerCSVList.set(j + 1, census1);
                }
            }
        }
    }
    private void dataCheck() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
    }

    public String getSortedAverage() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> censusCSVComparator = Comparator.comparing(player -> player.average,Comparator.reverseOrder());
        this.sort(censusCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }


    public String getPlayerWiseSortedStrikeRate() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> censusCSVComparator = Comparator.comparing(player -> player.strikingRate,Comparator.reverseOrder());
        this.sort(censusCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;

    }

    public String getSortedPlayersDataForMaximumFour() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> censusCSVComparator = Comparator.comparing(player -> player.four,Comparator.reverseOrder());
        this.sort(censusCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayersDataForMaximumSix() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> censusCSVComparator = Comparator.comparing(player -> player.six,Comparator.reverseOrder());
        this.sort(censusCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayersDataForStrikeRateOnSixAndFour() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.six+player.four,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player .strikingRate,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;

    }

    public String getSortedPlayerDataForBestAverageOnStrikeRate() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.average,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(ipl -> ipl.strikingRate,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayerDataForMaximumRunsWithBestAverage() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.runs,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(ipl -> ipl.average,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }


    public String getSortedPlayerStrikeRateOnBlowing() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.strikingRate,Comparator.reverseOrder());
        this.sort(iplCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String geteSortedPlayerBestEconomyOnBlowing() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.economyRate,Comparator.reverseOrder());
        this.sort(iplCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayerDataForStrikeRateOn5wAnd4w() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.fiveWickets+player.fourWickets,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player.strikingRate,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayerDataForBestAverageOnBowlingStrikeRate() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.average,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player.strikingRate,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayerDataForWicketsOnBowlingAverage() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.wickets,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player.average,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }


    public String getSortedOnPlayersDataForRunsAndWickets() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.wickets,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player.runs,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;

    }

    public String getSortedPlayerDataForMaximumHundredsWithBestAverage() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.hundred,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player.average,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayerDataOnAverageWithout100s50s() throws IplCricketAnalyserException {
        this.dataCheck();
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> ((player.hundred == 0)&&(player.fifty ==0)),Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player.average,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }


    public Map<String, IPLPlayerDAO> loadIPLRunsCensusData(String csvFilePath) throws IplCricketAnalyserException {
        batsmanData = new IPLPlayersLoader().loadIPLPlayersData(IPLRunsCSV.class,csvFilePath);
        return batsmanData;
    }

}
