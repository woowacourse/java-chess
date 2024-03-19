package domain;

public interface Piece {
    PieceMoveResult move(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard);

    boolean isOn(Position position);

    Team getTeam();
}
