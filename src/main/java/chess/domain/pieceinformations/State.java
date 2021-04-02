package chess.domain.pieceinformations;

import java.util.Arrays;

public enum State {
    ALIVE, DEAD;

    public static State getInstance(String value){
        if ( "true".equals(value)){
            return ALIVE;
        }
        if("false".equals(value)){
            return DEAD;
        }
        throw new IllegalArgumentException("해당하는 상태가 없네요~");
    }
}
