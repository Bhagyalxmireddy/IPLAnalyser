package iplanalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import csvbulider.CSVBuliderException;
import csvbulider.CSVBuliderFactory;
import csvbulider.ICSVBulider;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class IPLAnalyser {

    List<IPLRunsCSV> playerCSVList = null;

    public int loadIPLRunsData(String csvFilePath) throws IplCricketAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBulider csvBulider = CSVBuliderFactory.createCSVBulider();
            playerCSVList = csvBulider.getCSVFileList(reader, IPLRunsCSV.class);
            return playerCSVList.size();
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

    private void sort(List<IPLRunsCSV> playerCSVList, Comparator<IPLRunsCSV> playerCSVComparator) {
        for (int i=0;i<playerCSVList.size()-1;i++) {
            for (int j = 0; j < playerCSVList.size()-1; j++) {
                IPLRunsCSV census1 = playerCSVList.get(j);
                IPLRunsCSV census2 = playerCSVList.get(j + 1);
                if (playerCSVComparator.compare(census1, census2) > 0) {
                    playerCSVList.set(j, census2);
                    playerCSVList.set(j + 1, census1);
                }
            }
        }
    }

    public String getSortedAverage() throws IplCricketAnalyserException {
        if(playerCSVList == null || playerCSVList.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        Comparator<IPLRunsCSV> censusCSVComparator = Comparator.comparing(census -> census.average,Comparator.reverseOrder());
        this.sort(playerCSVList,censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(playerCSVList);
        return sortedStateCensusJson;
    }


    public String getPlayerWiseSortedStrikeRate() throws IplCricketAnalyserException {
        if(playerCSVList == null || playerCSVList.size() == 0){
            throw new IplCricketAnalyserException("No census data",IplCricketAnalyserException.ExceptionType.NO_SCORE_DATA);
        }
        Comparator<IPLRunsCSV> censusCSVComparator = Comparator.comparing(census -> census.strikingRate,Comparator.reverseOrder());
        this.sort(playerCSVList,censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(playerCSVList);
        return sortedStateCensusJson;

    }
}
