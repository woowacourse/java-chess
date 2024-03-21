package chess.domain.piece;

import chess.domain.Position;
import chess.domain.board.ChessBoard;

public interface Piece {
    PieceMoveResult move(Position targetPosition, ChessBoard chessBoard);

    boolean isOn(Position position);

    Team getTeam();

    PieceType getPieceType();

    int getColumn();

    int getRow();
}
