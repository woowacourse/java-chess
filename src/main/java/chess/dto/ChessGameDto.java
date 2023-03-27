package chess.dto;

public class ChessGameDto {
    private final int gameId;
    private final String name;

    public ChessGameDto(int gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }
}
