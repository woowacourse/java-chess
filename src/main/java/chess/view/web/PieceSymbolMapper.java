package chess.view.web;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;

import java.util.Arrays;
import java.util.Map;

public enum PieceSymbolMapper {
    WHITE_KING(King.of(Owner.WHITE), "&#9812;"),
    WHITE_QUEEN(Queen.of(Owner.WHITE), "&#9813;"),
    WHITE_ROOK(Rook.of(Owner.WHITE), "&#9814;"),
    WHITE_BISHOP(Bishop.of(Owner.WHITE), "&#9815;"),
    WHITE_KNIGHT(Knight.of(Owner.WHITE), "&#9816;"),
    WHITE_PAWN(Pawn.of(Owner.WHITE), "&#9817;"),
    BLACK_KING(King.of(Owner.BLACK), "&#9818;"),
    BLACK_QUEEN(Queen.of(Owner.BLACK), "&#9819;"),
    BLACK_ROOK(Rook.of(Owner.BLACK), "&#9820;"),
    BLACK_BISHOP(Bishop.of(Owner.BLACK), "&#9821;"),
    BLACK_KNIGHT(Knight.of(Owner.BLACK), "&#9822;"),
    BLACK_PAWN(Pawn.of(Owner.BLACK), "&#9823;"),
    EMPTY(Empty.of(), "");

    private final String uniCode;
    private final Piece piece;

    PieceSymbolMapper(final Piece piece, final String uniCode) {
        this.uniCode = uniCode;
        this.piece = piece;
    }

    public static String[][] parseBoardAsUnicode(final Map<Position, Piece> board) {
        final String[][] uniCodeBoard = new String[8][8];
        for (final Vertical v : Vertical.values()) {
            for (final Horizontal h : Horizontal.values()) {
                final Position position = new Position(v, h);
                final Piece piece = board.get(position);
                uniCodeBoard[h.getIndex() - 1][v.getIndex() - 1] = parseToUnicode(piece);
            }
        }
        return uniCodeBoard;
    }

    private static String parseToUnicode(final Piece piece) {
        return Arrays.stream(values())
                .filter(value -> value.piece.equals(piece))
                .map(value -> value.uniCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("심볼, 색상 매칭 오류" + piece));
    }

    public static Piece parseToPiece(final String uniCode) {
        return Arrays.stream(values())
                .filter(value -> value.uniCode.equals(uniCode))
                .map(value -> value.piece)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("심볼, 색상 매칭 오류"));
    }
}
