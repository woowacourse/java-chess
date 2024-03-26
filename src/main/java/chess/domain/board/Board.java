package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.square.Square;
import chess.dto.PieceDrawing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {

    private static final String ERROR_NOT_TURN = "선택한 기물의 팀의 차례가 아닙니다.";
    private static final String ERROR_CANNOT_STAY = "제자리로 이동할 수 없습니다.";
    private static final String ERROR_NOT_EXIST_PIECE = "해당 위치에 기물이 존재하지 않습니다.";

    private final Set<Piece> pieces;

    public Board(final Set<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    public void move(final Square source, final Square target, final PieceColor turn) {
        Piece sourcePiece = findPiece(source);
        validateTurn(sourcePiece, turn);
        validateStay(source, target);
        sourcePiece.move(this, target);
        removeTargetPieceIfAttacked(sourcePiece, target);
    }

    private void validateTurn(final Piece sourcePiece, final PieceColor turn) {
        if (sourcePiece.getColor() != turn) {
            throw new IllegalArgumentException(ERROR_NOT_TURN);
        }
    }

    private void validateStay(final Square source, final Square target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException(ERROR_CANNOT_STAY);
        }
    }

    public boolean existOnSquare(final Square square) {
        return pieces.stream()
                .anyMatch(piece -> piece.isLocated(square));
    }

    public boolean existOnSquareWithColor(final Square square, final PieceColor pieceColor) {
        return pieces.stream()
                .anyMatch(piece -> piece.isLocated(square) && piece.getColor() == pieceColor);
    }

    private Piece findPiece(final Square square) {
        return pieces.stream()
                .filter(piece -> piece.isLocated(square))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_PIECE));
    }

    private void removeTargetPieceIfAttacked(final Piece sourcePiece, final Square targetSquare) {
        pieces.stream()
                .filter(piece -> piece.isLocated(targetSquare))
                .filter(piece -> piece.getColor() != sourcePiece.getColor())
                .findFirst()
                .ifPresent(pieces::remove);
    }

    public List<PieceDrawing> getStatus() {
        return pieces.stream()
                .map(PieceDrawing::from)
                .toList();
    }
}
