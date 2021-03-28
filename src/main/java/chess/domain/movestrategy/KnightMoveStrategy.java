package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public Set<Position> currentPositionMoveStrategy(Board board, Position source) {
        Set<Position> movable = new HashSet<>();
        Piece sourcePiece = board.pieceByPosition(source);

        for (List<Position> positions : sourcePiece.vectors(source)) {
            movable.addAll(knightMovablePosition(positions, board, sourcePiece));
        }

        movable.remove(source);
        return movable;
    }

    private List<Position> knightMovablePosition(List<Position> positions, Board board,
        Piece sourcePiece) {
        return positions.stream()
            .filter(position -> board.pieceByPosition(position).isNotSameColorPiece(sourcePiece))
            .collect(Collectors.toList());
    }
}
