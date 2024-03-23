package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.square.Square;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Board {
    private static final String ERROR_NOT_EXIST_PIECE = "해당 위치에 기물이 존재하지 않습니다.";
    private final Set<Piece> pieces;

    public Board(Set<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    public void move(Square source, Square target) {
        Piece sourcePiece = findPiece(source).orElseThrow(() -> new IllegalStateException(ERROR_NOT_EXIST_PIECE));
        removeTargetPieceIfAttacked(sourcePiece, target);
        sourcePiece.move(target);
    }

    public Optional<Piece> findPiece(Square square) {
        return pieces.stream()
                .filter(piece -> piece.isLocated(square))
                .findAny();
    }

    private void removeTargetPieceIfAttacked(Piece sourcePiece, Square targetSquare) {
        findPiece(targetSquare).ifPresent(targetPiece -> {
            if (sourcePiece.getColor() != targetPiece.getColor()) {
                pieces.remove(targetPiece);
            }
        });
    }

    public Set<Piece> getPieces() {
        return pieces;
    }
}
