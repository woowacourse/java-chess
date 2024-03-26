package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.square.Square;
import chess.dto.PieceDrawing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {

    public static final String ERROR_NOT_TURN = "선택한 기물의 팀의 차례가 아닙니다.";
    private static final String ERROR_CANNOT_STAY = "제자리로 이동할 수 없습니다.";
    private static final String ERROR_NOT_EXIST_PIECE = "해당 위치에 기물이 존재하지 않습니다.";

    private final Set<Piece> pieces;

    public Board(Set<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    public void move(Square source, Square target, PieceColor turn) {
        Piece sourcePiece = findPiece(source);
        validateTurn(sourcePiece, turn);
        validateStay(source, target);
        sourcePiece.move(this, target);
        removeTargetPieceIfAttacked(sourcePiece, target);
    }

    private void validateTurn(Piece sourcePiece, PieceColor turn) {
        if (sourcePiece.getColor() != turn) {
            throw new IllegalArgumentException(ERROR_NOT_TURN);
        }
    }

    private void validateStay(Square source, Square target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException(ERROR_CANNOT_STAY);
        }
    }

    public boolean existOnSquare(Square square) {
        return pieces.stream()
                .anyMatch(piece -> piece.isLocated(square));
    }

    public boolean existOnSquareWithColor(Square square, PieceColor pieceColor) {
        return pieces.stream()
                .anyMatch(piece -> piece.isLocated(square) && piece.getColor() == pieceColor);
    }

    private Piece findPiece(Square square) {
        return pieces.stream()
                .filter(piece -> piece.isLocated(square))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_PIECE));
    }

    private void removeTargetPieceIfAttacked(Piece sourcePiece, Square targetSquare) {
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
