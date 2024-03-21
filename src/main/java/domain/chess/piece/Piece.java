package domain.chess.piece;

import domain.Position;
import domain.chess.board.ChessBoard;

public interface Piece {
    PieceMoveResult move(Position targetPosition, ChessBoard chessBoard);

    boolean isOn(Position position);

    Team getTeam();

    PieceType getPieceType();

    int getColumn();

    int getRow();
}
