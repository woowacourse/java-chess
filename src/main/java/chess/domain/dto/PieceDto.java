package chess.domain.dto;

public class PieceDto {

    private final int gameId;
    private final String position;
    private final String piece;
    private final String color;

    public PieceDto(int gameId, String position, String piece, String color) {
        this.gameId = gameId;
        this.position = position;
        this.piece = piece;
        this.color = color;
    }

    public int getGameId() {
        return gameId;
    }

    public String getPiece() {
        return piece;
    }

    public String getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }
}
