package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.square.Square;
import chess.dto.PieceDrawing;

import java.util.HashSet;
import java.util.List;
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
        sourcePiece.move(target);
        removeTargetPieceIfAttacked(sourcePiece, target);
    }

    public boolean existOnSquare(Square square) {
        return pieces.stream()
                .anyMatch(piece -> piece.isLocated(square));
    }

    public boolean existOnSquareWithColor(Square square, PieceColor pieceColor) {
        return pieces.stream()
                .anyMatch(piece -> piece.isLocated(square) && piece.getColor() == pieceColor);
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

    public List<PieceDrawing> generatePieceDrawings() {
        return pieces.stream()
                .map(PieceDrawing::of)
                .toList();
    }
}
