package chess.chesspiece;

public class ChessPiece {

    private final Camp camp;
    private final ChessPieceProperty chessPieceProperty;

    public ChessPiece(Camp camp, ChessPieceProperty chessPieceProperty) {
        this.camp = camp;
        this.chessPieceProperty = chessPieceProperty;
    }

    public boolean isBlackCamp() {
        return camp == Camp.BLACK;
    }

    public boolean isWhiteCamp() {
        return camp == Camp.WHITE;
    }
}
