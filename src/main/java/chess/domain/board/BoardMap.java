package chess.domain.board;

import java.util.Map;
import java.util.function.Predicate;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;

public class BoardMap {

    private static final EmptyPiece EMPTY_PIECE = EmptyPiece.create();

    private final Map<Position, Piece> pieces;

    public BoardMap(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean isTeamSame(Position position, Position other) {
        return getPieceAt(position).isSameTeamWith(getPieceAt(other));
    }

    public boolean hasPieceAt(Position position) {
        return !getPieceAt(position).isEmpty();
    }

    private Piece getPieceAt(Position position) {
        return pieces.getOrDefault(position, EMPTY_PIECE);
    }

    public long count(File file, Predicate<Piece> condition) {
        return pieces.entrySet().stream()
                .filter(it -> it.getKey().hasFile(file))
                .map(Map.Entry::getValue)
                .filter(condition)
                .count();
    }
}
