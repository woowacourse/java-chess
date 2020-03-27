package chess;

import java.util.NoSuchElementException;

public class NoWinnerException extends NoSuchElementException {
    public NoWinnerException(String message){
        super(message);
    }
}
