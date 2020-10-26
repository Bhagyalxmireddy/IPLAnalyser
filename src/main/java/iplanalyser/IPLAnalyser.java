package iplanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IPLAnalyser {

    public int loadIPLRunsData(String iplFactsSheetMostRuns) throws IplCricketAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(iplFactsSheetMostRuns));) {
            CsvToBeanBuilder<IPLRunsCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IPLRunsCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IPLRunsCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IPLRunsCSV> iplRunsCSVIterator = csvToBean.iterator();
            Iterable<IPLRunsCSV> csvIterable = () -> iplRunsCSVIterator;
            int numofEateries  = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
            return numofEateries;
        } catch (IOException e) {
            throw new IplCricketAnalyserException(e.getMessage(),
                    IplCricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);

        }catch (RuntimeException e){
            throw new IplCricketAnalyserException(e.getMessage(),
                    IplCricketAnalyserException.ExceptionType.RUNTIME_EXCEPTION);
        }
    }
}
