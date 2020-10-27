package iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {


    private static final String IPL_FACTS_SHEET_MOST_RUNS = "C:\\Users\\USER\\Downloads\\IPL_Analyser_Program\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPLCSVRunsFileReturnsCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int numOfRecords = iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            Assert.assertEquals(100, numOfRecords);
        }catch (IplCricketAnalyserException e){
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLRunsData_WhenSortedByTopBattingAverages_ShouldReturnCorrectResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getSortedAverage();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLRunsData_WhenSortedByTopStrikeRate_ShouldReturnCorrectResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getPlayerWiseSortedStrikeRate();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLRunsData_WhenSortedByMaximumFour_ShouldReturnCorrectResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getSortedPlayersDataForMaximumFour();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLRunsData_WhenSortedByMaximumSix_ShouldReturnCorrectResult(){
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getSortedPlayersDataForMaximumSix();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("Andre Russell", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByWhoHadBestStrikeRateWithSixAndFour_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getSortedPlayersDataForStrikeRateOnSixAndFour();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("Andre Russell", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByWhoHadBestAverageWithBestStrikeRate_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getSortedPlayerDataForBestAverageOnStrikeRate();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByWhoHitMaximumRunsWithBestAverage_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getSortedPlayerDataForMaximumRunsWithBestAverage();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("David Warner", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
}
