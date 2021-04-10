package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonMoveStrategy implements MoveStrategy {

    @Override
    public Set<Position> currentPositionMoveStrategy(Board board, Position source) {
        Set<Position> movable = new HashSet<>();
        Piece sourcePiece = board.pieceByPosition(source);

        for (List<Position> positions : sourcePiece.vectors(source)) {
            movable.addAll(movablePosition(positions, board, sourcePiece));
        }

        movable.remove(source);
        return movable;
    }

    private List<Position> movablePosition(List<Position> positions, Board board,
        Piece sourcePiece) {
        if (positions.isEmpty()) {
            return positions;
        }
        int breakIndex = findBreakIndex(positions, board, sourcePiece);

        return positions.stream()
            .limit(breakIndex)
            .collect(Collectors.toList());
    }

    private int findBreakIndex(List<Position> positions, Board board, Piece sourcePiece) {
        int breakIndex = positions.size();
        Position collision = positions.stream()
            .filter(position -> board.pieceByPosition(position).isNotEmpty())
            .findFirst().orElse(positions.get(breakIndex - 1));

        if (board.pieceByPosition(collision).isNotSameColorPiece(sourcePiece)) {
            return positions.indexOf(collision) + 1;
        }
        return positions.indexOf(collision);
    }
}
