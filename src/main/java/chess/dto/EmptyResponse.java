package chess.dto;

import java.util.HashMap;

public class EmptyResponse extends Response {

    private static final String EMPTY = "";

    public EmptyResponse() {
        super(new HashMap<>(), EMPTY);
    }
}
