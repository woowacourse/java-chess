package chess;

public class PieceDto {
    private final String userId;
    private final String pieceName;
    private final String camp;
    private final String square;
    private final boolean isWhiteTurn;

    public PieceDto(String userId, String pieceName, String camp, String square, boolean isWhiteTurn) {
        this.userId = userId;
        this.pieceName = pieceName;
        this.camp = camp;
        this.square = square;
        this.isWhiteTurn = isWhiteTurn;
    }

    public String getUserId() {
        return userId;
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getCamp() {
        return camp;
    }

    public String getSquare() {
        return square;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
}
