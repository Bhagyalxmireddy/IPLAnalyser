package iplanalyser;

import com.sun.javafx.collections.MappingChange;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPLMockitoTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    Map<String, IPLPlayerDAO> batsmanData;
    Map<String, IPLPlayerDAO> bowlersData;
    private static final String IPL_FACTS_SHEET_MOST_RUNS = "C:\\Users\\USER\\Downloads\\IPL_Analyser_Program\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";

    @Mock
    IPLAnalyser iplAnalyser;

    public Map<String, IPLPlayerDAO> passData(String Choice) {
        this.batsmanData.put("David Warner",new IPLPlayerDAO("David Warner", 125, 93.56, 153, 10,5));
        this.batsmanData.put("Chris Gayle",new IPLPlayerDAO("Chris Gayle", 145, 100.56, 103, 10,9));
        this.batsmanData.put("Rohit Sharma ",new IPLPlayerDAO("Rohit Sharma", 100, 73.56, 125, 5,2));

        this.bowlersData.put("Sachin",new IPLPlayerDAO("Sachin", 125, 93.56, 153, 10,5));
        this.bowlersData.put("Dhoni",new IPLPlayerDAO("Dhoni", 145, 100.56, 103, 10,9));
        this.bowlersData.put("Yuvraj",new IPLPlayerDAO("Yuvraj", 100, 73.56, 125, 5,2));
        MockitoAnnotations.initMocks(this);
        if (Choice == "batsmanData") {
            return batsmanData;
        }
        return bowlersData;
    }
    @Test
    public void givenBatsmanData_ShouldReturnCount() {
        try {
            MockitoData mockitoData = new MockitoData(iplAnalyser);
            this.iplAnalyser = mock(IPLAnalyser.class);
            Map<String, IPLPlayerDAO> sampleMap = passData("batsmanData");
            when(iplAnalyser.loadIPLRunsCensusData(IPL_FACTS_SHEET_MOST_RUNS)).thenReturn(mockitoData.loadIPlRunsCensusData(sampleMap));
            SampleAnalyser sampleAnalyser = new SampleAnalyser();
            sampleAnalyser.setLoader(iplAnalyser);
            int count = sampleAnalyser.findSize(IPL_FACTS_SHEET_MOST_RUNS);
            Assert.assertEquals(3, count);
        } catch (IplCricketAnalyserException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void givenbowlersData_ShouldReturnCount() {
        try {
            MockitoData mockitoData = new MockitoData(iplAnalyser);
            this.iplAnalyser = mock(IPLAnalyser.class);
            Map<String, IPLPlayerDAO> sampleMap = passData("bowlersData");
            when(iplAnalyser.loadIPLRunsCensusData(IPL_FACTS_SHEET_MOST_RUNS)).thenReturn(mockitoData.loadIPlRunsCensusData(sampleMap));
            SampleAnalyser sampleAnalyser = new SampleAnalyser();
            sampleAnalyser.setLoader(iplAnalyser);
            int count = sampleAnalyser.findSize(IPL_FACTS_SHEET_MOST_RUNS);
            Assert.assertEquals(3, count);
        } catch (IplCricketAnalyserException e) {
            e.printStackTrace();
        }

    }
}
