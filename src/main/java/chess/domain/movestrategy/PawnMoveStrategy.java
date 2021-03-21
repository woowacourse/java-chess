package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public Set<Position> movable(Board board, Position source) {
        Piece piece = board.pieceOfPosition(source);

        return pawnMovablePosition(piece.vectors(source), board);
    }

    private Set<Position> pawnMovablePosition(List<List<Position>> positions, Board board) {
        Set<Position> pawnMovable = new HashSet<>();
        pawnMovable.addAll(forwardPositions(positions.get(0), board));
        pawnMovable.addAll(diagonalPositions(positions.get(1), board));
        return pawnMovable;
    }

    private List<Position> forwardPositions(List<Position> positions, Board board) {
        return positions.stream()
                .filter(position -> board.pieceOfPosition(position).isEmpty())
                .collect(Collectors.toList());
    }

    private List<Position> diagonalPositions(List<Position> positions, Board board) {
        return positions.stream()
                .filter(position -> board.pieceOfPosition(position).isBlack())
                .collect(Collectors.toList());
    }
}
