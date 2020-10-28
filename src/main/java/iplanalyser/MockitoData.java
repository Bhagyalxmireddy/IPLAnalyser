package iplanalyser;

import java.util.Map;

public class MockitoData {
    public final IPLAnalyser iplAnalyser;

    public MockitoData(IPLAnalyser iplAnalyser) {
    this.iplAnalyser = iplAnalyser;
    }

    public Integer loadIPlRunsCensusData(Map<String, IPLPlayerDAO> sampleMap) {
        return sampleMap.size();
    }
}
