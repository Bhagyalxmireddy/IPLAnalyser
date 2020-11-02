package iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {

    private static final String IPL_FACTS_SHEET_MOST_RUNS = "C:\\Users\\USER\\Downloads\\IPL_Analyser_Program\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String IPL_FACTS_SHEET_MOST_WKTS = "C:\\Users\\USER\\Downloads\\IPL_Analyser_Program\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";

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
    @Test
    public void givenIPLCSVWktsFileReturnsCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int numOfRecords = iplAnalyser.loadIPLWktsData(IPL_FACTS_SHEET_MOST_WKTS);
            Assert.assertEquals(99, numOfRecords);
        } catch (IplCricketAnalyserException e) { }
    }

    @Test
    public void givenIPLData_WhenSortedByTopBowlingAverages_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLWktsData(IPL_FACTS_SHEET_MOST_WKTS);
            String sortedIPLData = iplAnalyser.getSortedAverage();
            IPLWktsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByTopBowlingStrikeRate_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLWktsData(IPL_FACTS_SHEET_MOST_WKTS);
            String sortedIPLData = iplAnalyser.getSortedPlayerStrikeRateOnBlowing();
            IPLWktsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByBestEconomy_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLWktsData(IPL_FACTS_SHEET_MOST_WKTS);
            String sortedIPLData = iplAnalyser.geteSortedPlayerBestEconomyOnBlowing();
            IPLWktsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLWktsCSV[].class);
            Assert.assertEquals("Ben Cutting", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByWhoHadBestStrikeRateWithMaximum5wAnd4w_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLWktsData(IPL_FACTS_SHEET_MOST_WKTS);
            String sortedIPLData = iplAnalyser.getSortedPlayerDataForStrikeRateOn5wAnd4w();
            IPLWktsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLWktsCSV[].class);
            Assert.assertEquals("Lasith Malinga", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByWhoHadGreatBowlingAverageWithBestStrikeRate_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLWktsData(IPL_FACTS_SHEET_MOST_WKTS);
            String sortedIPLData = iplAnalyser.getSortedPlayerDataForBestAverageOnBowlingStrikeRate();
            IPLWktsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByMaximumWicketsWithBestBowlingAverage_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLWktsData(IPL_FACTS_SHEET_MOST_WKTS);
            String sortedIPLData = iplAnalyser.getSortedPlayerDataForWicketsOnBowlingAverage();
            IPLWktsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLWktsCSV[].class);
            Assert.assertEquals("Imran Tahir", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
   @Test
    public void givenIPlData_whenSortOnBattingAndBowlingAverages_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int numberOfRecords = iplAnalyser.loadIPLLeagueData(IPL_FACTS_SHEET_MOST_RUNS, IPL_FACTS_SHEET_MOST_WKTS);
            String sortedIPLData = iplAnalyser.getSortedAverage();
            IPLWktsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPlData_whenSortOnRunsAndWickets_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int numberOfRecords = iplAnalyser.loadIPLLeagueData(IPL_FACTS_SHEET_MOST_RUNS, IPL_FACTS_SHEET_MOST_WKTS);
            String sortedIPLData = iplAnalyser.getSortedOnPlayersDataForRunsAndWickets();
            IPLWktsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLWktsCSV[].class);
            Assert.assertEquals("Kagiso Rabada", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedOnWhoHitMaximumHundredsWithBestBattingAverage_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getSortedPlayerDataForMaximumHundredsWithBestAverage();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("David Warner", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
    @Test
    public void givenIPLData_WhenSortedByBestAverageWithout100sAnd50s_ShouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_FACTS_SHEET_MOST_RUNS);
            String sortedIPLData = iplAnalyser.getSortedPlayerDataOnAverageWithout100s50s();
            IPLRunsCSV[] scoreCSV = new Gson().fromJson(sortedIPLData, IPLRunsCSV[].class);
            Assert.assertEquals("Marcus Stoinis", scoreCSV[0].player);
        } catch (IplCricketAnalyserException e) { }
    }
}
