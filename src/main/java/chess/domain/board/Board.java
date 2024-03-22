package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Board {
    private static final String ERROR_NOT_EXIST_PIECE = "해당 위치에 기물이 존재하지 않습니다.";
    private final Set<Piece> pieces;

    public Board(Set<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = findPiece(source).orElseThrow(() -> new IllegalStateException(ERROR_NOT_EXIST_PIECE));
        removeTargetPieceIfAttacked(sourcePiece, target);
        sourcePiece.move(target);
    }

    public Optional<Piece> findPiece(Position position) {
        return pieces.stream()
                .filter(piece -> piece.isLocated(position))
                .findAny();
    }

    private void removeTargetPieceIfAttacked(Piece sourcePiece, Position targetPosition) {
        findPiece(targetPosition).ifPresent(targetPiece -> {
            if (sourcePiece.getColor() != targetPiece.getColor()) {
                pieces.remove(targetPiece);
            }
        });
    }

    public Set<Piece> getPieces() {
        return pieces;
    }
}
