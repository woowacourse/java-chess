package chess.view;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.function.Predicate;

public enum PieceView {

    BLACK_KING((piece -> piece.isKing() && !piece.isWhite()), "♔"),
    WHITE_KING((piece -> piece.isKing() && piece.isWhite()), "♚"),
    BLACK_QUEEN((piece -> piece.isQueen() && !piece.isWhite()), "♕"),
    WHITE_QUEEN((piece -> piece.isQueen() && piece.isWhite()), "♛"),
    BLACK_BISHOP((piece -> piece.isBishop() && !piece.isWhite()), "♗"),
    WHITE_BISHOP((piece -> piece.isBishop() && piece.isWhite()), "♝"),
    BLACK_KNIGHT((piece -> piece.isKnight() && !piece.isWhite()), "♘"),
    WHITE_KNIGHT((piece -> piece.isKnight() && piece.isWhite()), "♞"),
    BLACK_ROOK((piece -> piece.isRook() && !piece.isWhite()), "♖"),
    WHITE_ROOK((piece -> piece.isRook() && piece.isWhite()), "♜"),
    BLACK_PAWN((piece -> piece.isPawn() && !piece.isWhite()), "♙"),
    WHITE_PAWN((piece -> piece.isPawn() && piece.isWhite()), "♟");

    private static final String NOT_HAVE_SYMBOL = "심볼이 없는 피스입니다.";

    private final Predicate<Piece> piecePredicate;
    private final String symbol;

    PieceView(Predicate<Piece> piecePredicate, String symbol) {
        this.piecePredicate = piecePredicate;
        this.symbol = symbol;
    }

    public static String from(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceView -> pieceView.piecePredicate.test(piece))
                .map(pieceView -> pieceView.symbol)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_HAVE_SYMBOL));
    }
}
