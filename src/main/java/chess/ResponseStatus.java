package chess;

public enum ResponseStatus {
    OK(200),
    BAD_REQUEST(400);

    private final int stateCode;

    ResponseStatus(final int stateCode) {
        this.stateCode = stateCode;
    }

    public int getStateCode() {
        return stateCode;
    }

}
