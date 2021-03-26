package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PawnMoveStrategy implements MoveStrategy {

    public static final int FORWARD_INDEX = 0;
    public static final int DIAGONAL_INDEX = 1;

    @Override
    public Set<Position> moveStrategy(Board board, Position source) {
        Piece sourcePiece = board.pieceByPosition(source);
        Set<Position> movable = pawnMovablePosition(
            sourcePiece.vectors(source), board, sourcePiece
        );
        movable.remove(source);
        return movable;
    }

    private Set<Position> pawnMovablePosition(List<List<Position>> positions, Board board, Piece sourcePiece) {
        Set<Position> pawnMovable = new HashSet<>();
        pawnMovable.addAll(forwardPositions(positions.get(FORWARD_INDEX), board));
        pawnMovable.addAll(diagonalPositions(positions.get(DIAGONAL_INDEX), board, sourcePiece));

        return pawnMovable;
    }

    private List<Position> forwardPositions(List<Position> positions, Board board) {
        Piece frontPiece = board.pieceByPosition(positions.get(FORWARD_INDEX));
        if (frontPiece.isNotEmpty()) {
            return new ArrayList<>();
        }

        return positions.stream()
            .filter(position -> {
                Piece piece = board.pieceByPosition(position);
                return piece.isEmpty();
            })
            .collect(Collectors.toList());
    }

    private List<Position> diagonalPositions(List<Position> positions, Board board, Piece sourcePiece) {
        return positions.stream()
            .filter(position -> {
                Piece piece = board.pieceByPosition(position);
                return piece.isOppositeColorPiece(sourcePiece);
            })
            .collect(Collectors.toList());
    }
}
