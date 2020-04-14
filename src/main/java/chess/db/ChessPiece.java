package chess.db;

public class ChessPiece {
    private String gameId;
    private String position;
    private String piece;

    public ChessPiece() {
    }

    public ChessPiece(String gameId, String position, String piece) {
        this.gameId = gameId;
        this.position = position;
        this.piece = piece;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }
}
