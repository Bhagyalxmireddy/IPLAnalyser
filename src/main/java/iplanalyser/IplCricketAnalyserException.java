package iplanalyser;

public class IplCricketAnalyserException extends Exception {

    enum ExceptionType{
        IPL_FILE_PROBLEM,RUNTIME_EXCEPTION;
    }
    ExceptionType type;

    public IplCricketAnalyserException(String message,ExceptionType type){
        super(message);
        this.type = type;
    }

    public IplCricketAnalyserException(String message,ExceptionType type,Throwable cause){
        super(message,cause);
        this.type = type;
    }

}
