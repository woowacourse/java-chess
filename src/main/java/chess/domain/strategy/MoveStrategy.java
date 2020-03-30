package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.List;

public interface MoveStrategy {
    Board movePieceWithTurnValidation(Board board, Piece fromPiece, Piece toPiece, Team currentTurn);

    List<Position> getPossiblePositions(List<Piece> board, Piece piece);

    List<Direction> getDirections();

    boolean isInBoardRange(Position position);

    int boardIndexOf(Position position);
}
