package chess.dto;

public class EmptyResponse implements Response {

    private static final String EMPTY = "";

    @Override
    public String getInformation() {
        return EMPTY;
    }

    @Override
    public String getMetaInformation() {
        return EMPTY;
    }
}
