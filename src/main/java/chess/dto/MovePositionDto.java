package chess.dto;

public class MovePositionDto {

    private final String chessGameName;
    private final String current;
    private final String destination;

    public MovePositionDto(String chessGameName, String current, String destination) {
        this.chessGameName = chessGameName;
        this.current = current;
        this.destination = destination;
    }

    public String getCurrent() {
        return current;
    }

    public String getDestination() {
        return destination;
    }

    public String getChessGameName() {
        return chessGameName;
    }
}
