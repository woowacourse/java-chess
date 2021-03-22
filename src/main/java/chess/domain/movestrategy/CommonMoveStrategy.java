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
    public Set<Position> moveStrategy(Board board, Position source) {
        Set<Position> movable = new HashSet<>();
        Piece piece = board.pieceOfPosition(source);

        for (List<Position> positions : piece.vectors(source)) {
            movable.addAll(movablePosition(positions, board));
        }

        return movable;
    }

    private List<Position> movablePosition(List<Position> positions, Board board) {
        if (positions.isEmpty()) {
            return positions;
        }
        int breakIndex = findBreakIndex(positions, board);

        return positions.stream()
                .limit(breakIndex)
                .collect(Collectors.toList());
    }

    private int findBreakIndex(List<Position> positions, Board board) {
        int breakIndex = positions.size();
        Position collision = positions.stream()
                .filter(position -> board.pieceOfPosition(position).isBlack() || board.pieceOfPosition(position).isWhite())
                .findFirst().orElse(positions.get(breakIndex - 1));
        if (board.pieceOfPosition(collision).isBlack()) { // isDifferent
            breakIndex = positions.indexOf(collision) + 1;
        }
        if (board.pieceOfPosition(collision).isWhite()) { // isSame
            breakIndex = positions.indexOf(collision);
        }
        return breakIndex;
    }
}
