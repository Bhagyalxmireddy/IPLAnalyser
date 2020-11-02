package iplanalyser;

import java.util.Map;

public class SampleAnalyser {

    public IPLAnalyser iplAnalyser;

    public void setLoader(IPLAnalyser loader) {
        this.iplAnalyser = loader;
    }

    public int findSize(String csvFilePath) throws IplCricketAnalyserException {
        int mockitoData = this.iplAnalyser.loadIPLRunsCensusData(csvFilePath);
        return mockitoData;
    }
}
