package chess.domain;

public interface ChessGame {

    void movePiece(Position currentPosition, Position targetPosition);

    GameResult gameResult();

    boolean isKingDead();

    boolean isChecked();

    boolean isCheckmate();

    Pieces currentPieces();

    int boardSize();

    TeamColor currentColor();
}
