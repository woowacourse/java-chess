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
    public Set<Position> movable(Board board, Position source) {
        Set<Position> movable = new HashSet<>();
        Piece piece = board.pieceOfPosition(source);

        for (List<Position> positions : piece.vectors(source)) {
            movable.addAll(knightMovablePosition(positions, board));
        }
        return movable;
    }

    private List<Position> knightMovablePosition(List<Position> positions, Board board) {
        return positions.stream()
                .filter(position -> !board.pieceOfPosition(position).isWhite())
                .collect(Collectors.toList());
    }
}
