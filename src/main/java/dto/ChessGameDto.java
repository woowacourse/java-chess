package dto;

public class ChessGameDto {
    private final int id;
    private final String whiteName;
    private final String blackName;
    private final boolean turn_is_black;

    public ChessGameDto(int id, String whiteName, String blackName, int turn_is_black) {
        this.id = id;
        this.whiteName = whiteName;
        this.blackName = blackName;
        this.turn_is_black = convertIntToBoolean(turn_is_black);
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
        return turn_is_black;
    }
}
