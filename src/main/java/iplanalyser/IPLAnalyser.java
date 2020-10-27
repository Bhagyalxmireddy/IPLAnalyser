package iplanalyser;

import com.google.gson.Gson;
import csvbulider.CSVBuliderException;
import csvbulider.CSVBuliderFactory;
import csvbulider.ICSVBulider;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class IPLAnalyser {

    List<IPLPlayerDAO> playerCSVList = null;
    Map<String,IPLPlayerDAO> playerCSVMap = new HashMap<>();

    public IPLAnalyser(){
        this.playerCSVList = new ArrayList<IPLPlayerDAO>();
        this.playerCSVMap = new HashMap<String, IPLPlayerDAO>();
    }

    public int loadIPLRunsData(String csvFilePath) throws IplCricketAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBulider csvBulider = CSVBuliderFactory.createCSVBulider();
            Iterator<IPLRunsCSV> csvIterator = csvBulider.getCSVFileIterator(reader, IPLRunsCSV.class);
            Iterable<IPLRunsCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IPLRunsCSV.class::cast)
                    .forEach(iplCSV -> playerCSVMap.put(iplCSV.player, new IPLPlayerDAO(iplCSV)));
            return playerCSVMap.size();
        } catch (IOException e) {
            throw new IplCricketAnalyserException(e.getMessage(),
                    IplCricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);

        }catch (RuntimeException e){
            throw new IplCricketAnalyserException(e.getMessage(),
                    IplCricketAnalyserException.ExceptionType.RUNTIME_EXCEPTION);
        } catch (CSVBuliderException e) {
            throw new IplCricketAnalyserException(e.getMessage(),
                    e.type.name());
        }
    }
    public int loadIPLWktsData(String csvFilePath) throws IplCricketAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBulider csvBulider = CSVBuliderFactory.createCSVBulider();
            Iterator<IPLWktsCSV> csvIterator = csvBulider.getCSVFileIterator(reader, IPLWktsCSV.class);
            Iterable<IPLWktsCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IPLWktsCSV.class::cast)
                    .forEach(iplCSV -> playerCSVMap.put(iplCSV.player, new IPLPlayerDAO(iplCSV)));
            return playerCSVMap.size();
        } catch (IOException e) {
            throw new IplCricketAnalyserException(e.getMessage(),
                    IplCricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);

        }catch (RuntimeException e){
            throw new IplCricketAnalyserException(e.getMessage(),
                    IplCricketAnalyserException.ExceptionType.RUNTIME_EXCEPTION);
        } catch (CSVBuliderException e) {
            throw new IplCricketAnalyserException(e.getMessage(),
                    e.type.name());
        }
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

    public String getSortedAverage() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> censusCSVComparator = Comparator.comparing(player -> player.average,Comparator.reverseOrder());
        this.sort(censusCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }


    public String getPlayerWiseSortedStrikeRate() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> censusCSVComparator = Comparator.comparing(player -> player.strikingRate,Comparator.reverseOrder());
        this.sort(censusCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;

    }

    public String getSortedPlayersDataForMaximumFour() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> censusCSVComparator = Comparator.comparing(player -> player.four,Comparator.reverseOrder());
        this.sort(censusCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayersDataForMaximumSix() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> censusCSVComparator = Comparator.comparing(player -> player.six,Comparator.reverseOrder());
        this.sort(censusCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayersDataForStrikeRateOnSixAndFour() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.six+player.four,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player .strikingRate,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;

    }

    public String getSortedPlayerDataForBestAverageOnStrikeRate() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.average,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(ipl -> ipl.strikingRate,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayerDataForMaximumRunsWithBestAverage() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.runs,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(ipl -> ipl.average,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }


    public String getSortedPlayerStrikeRateOnBlowing() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.strikingRate,Comparator.reverseOrder());
        this.sort(iplCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String geteSortedPlayerBestEconomyOnBlowing() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.economyRate,Comparator.reverseOrder());
        this.sort(iplCSVComparator);
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayerDataForStrikeRateOn5wAnd4w() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.fiveWickets+player.fourWickets,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player.strikingRate,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }

    public String getSortedPlayerDataForBestAverageOnBowlingStrikeRate() throws IplCricketAnalyserException {
        if(playerCSVMap == null || playerCSVMap.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        playerCSVList.addAll(playerCSVMap.values());
        Comparator<IPLPlayerDAO> iplCSVComparator = Comparator.comparing(player -> player.average,Comparator.reverseOrder());
        this.sort(iplCSVComparator.thenComparing(player -> player.strikingRate,Comparator.reverseOrder()));
        String sortedIPLPlayerJson = new Gson().toJson(this.playerCSVList);
        return sortedIPLPlayerJson;
    }
}
