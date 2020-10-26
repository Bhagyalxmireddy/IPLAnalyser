package iplanalyser;

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
}
