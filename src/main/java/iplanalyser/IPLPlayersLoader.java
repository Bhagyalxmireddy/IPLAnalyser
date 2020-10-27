package iplanalyser;

import csvbulider.CSVBuliderException;
import csvbulider.CSVBuliderFactory;
import csvbulider.ICSVBulider;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLPlayersLoader {

    public <E> Map<String, IPLPlayerDAO> loadIPLPlayersData(Class<E> iplCSVClass, String csvFilePath) throws IplCricketAnalyserException {
        Map<String,IPLPlayerDAO> playerCSVMap = new HashMap<String, IPLPlayerDAO>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBulider csvBulider = CSVBuliderFactory.createCSVBulider();
            Iterator<E> csvIterator = csvBulider.getCSVFileIterator(reader, iplCSVClass);
            Iterable<E> csvIterable = () -> csvIterator;
            if(iplCSVClass.getName().equals("iplanalyser.IPLRunsCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLRunsCSV.class::cast)
                        .forEach(iplCSV -> playerCSVMap.put(iplCSV.player, new IPLPlayerDAO(iplCSV)));
            }else  if(iplCSVClass.getName().equals("iplanalyser.IPLWktsCSV")){
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLWktsCSV.class::cast)
                        .forEach(iplCSV -> playerCSVMap.put(iplCSV.player, new IPLPlayerDAO(iplCSV)));
            }
            return playerCSVMap;
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
    public Map<String, IPLPlayerDAO> loadIPLLeagueData(String csvPath1, String csvPath2) throws IplCricketAnalyserException {
        Map<String, IPLPlayerDAO> map1 = loadIPLPlayersData(IPLRunsCSV.class, csvPath1);
        Map<String, IPLPlayerDAO> map2 = loadIPLPlayersData(IPLWktsCSV.class, csvPath2);
        Map<String, IPLPlayerDAO> playerCSVMap = new HashMap<>();
        for (Map.Entry<String,IPLPlayerDAO> m1:map1.entrySet()){
            for (Map.Entry<String,IPLPlayerDAO> m2:map2.entrySet()){
                if (m1.getValue().player.equals(m2.getValue().player)){
                    playerCSVMap.put(m2.getKey(),m2.getValue());
                }
            }
        }
        return playerCSVMap;
    }
}
