package chess.dto;

import java.util.HashMap;

public class EmptyGameResponse extends GameResponse {

    private static final String EMPTY = "";

    public EmptyGameResponse() {
        super(new HashMap<>(), EMPTY);
    }
}
