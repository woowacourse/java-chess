package dto;

public class ChessGameDto {
    private final int id;
    private final String whiteName;
    private final String blackName;
    private final boolean turnIsBlack;

    public ChessGameDto(int id, String whiteName, String blackName, int turnIsBlack) {
        this.id = id;
        this.whiteName = whiteName;
        this.blackName = blackName;
        this.turnIsBlack = convertIntToBoolean(turnIsBlack);
    }

    private static boolean convertIntToBoolean(int value) {
        return value == 1;
    }

    public int getId() {
        return id;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public String getBlackName() {
        return blackName;
    }

    public boolean isTurnBlack() {
        return turnIsBlack;
    }
}
