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
    public Set<Position> moveStrategy(Board board, Position source) {
        Piece sourcePiece = board.pieceOfPosition(source);
        Set<Position> movable = pawnMovablePosition(sourcePiece.vectors(source), board, sourcePiece);
        movable.remove(source);
        return movable;
    }

    private Set<Position> pawnMovablePosition(List<List<Position>> positions, Board board, Piece sourcePiece) {
        Set<Position> pawnMovable = new HashSet<>();
        pawnMovable.addAll(forwardPositions(positions.get(0), board));
        pawnMovable.addAll(diagonalPositions(positions.get(1), board, sourcePiece));
        return pawnMovable;
    }

    private List<Position> forwardPositions(List<Position> positions, Board board) {
        return positions.stream()
                .filter(position -> board.pieceOfPosition(position).isEmpty())
                .collect(Collectors.toList());
    }

    private List<Position> diagonalPositions(List<Position> positions, Board board, Piece sourcePiece) {
        return positions.stream()
                .filter(position -> board.pieceOfPosition(position).isDifferentColorPiece(sourcePiece))
                .collect(Collectors.toList());
    }
}
