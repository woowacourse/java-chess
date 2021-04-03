package chess.service;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;

import java.util.Arrays;

public enum PieceSymbolMapper {
    WHITE_KING(Owner.WHITE, Symbol.KING, "&#9812;"),
    WHITE_QUEEN(Owner.WHITE, Symbol.QUEEN, "&#9813;"),
    WHITE_ROOK(Owner.WHITE, Symbol.ROOK, "&#9814;"),
    WHITE_BISHOP(Owner.WHITE, Symbol.BISHOP, "&#9815;"),
    WHITE_KNIGHT(Owner.WHITE, Symbol.KNIGHT, "&#9816;"),
    WHITE_PAWN(Owner.WHITE, Symbol.PAWN, "&#9817;"),
    BLACK_KING(Owner.BLACK, Symbol.KING, "&#9818;"),
    BLACK_QUEEN(Owner.BLACK, Symbol.QUEEN, "&#9819;"),
    BLACK_ROOK(Owner.BLACK, Symbol.ROOK, "&#9820;"),
    BLACK_BISHOP(Owner.BLACK, Symbol.BISHOP, "&#9821;"),
    BLACK_KNIGHT(Owner.BLACK, Symbol.KNIGHT, "&#9822;"),
    BLACK_PAWN(Owner.BLACK, Symbol.PAWN, "&#9823;"),
    EMPTY(Owner.NONE, Symbol.EMPTY, "");

    private final Owner owner;
    private final Symbol symbol;
    private final String uniCode;

    PieceSymbolMapper(final Owner owner, final Symbol symbol, final String uniCode) {
        this.owner = owner;
        this.symbol = symbol;
        this.uniCode = uniCode;
    }

    private static String parse(final Owner owner, final Symbol symbol) {
        return Arrays.stream(values())
                .filter(value -> value.owner.equals(owner))
                .filter(value -> value.symbol.equals(symbol))
                .map(uniCodeMapper -> uniCodeMapper.uniCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("심볼, 색상 매칭 오류"));
    }

    public static String[][] parseBoardAsUnicode(final Board board) {
        final String[][] uniCodeBoard = new String[8][8];
        for (final Vertical v : Vertical.values()) {
            for (final Horizontal h : Horizontal.values()) {
                final Piece piece = board.of(new Position(v, h));
                final String uniCode = parse(piece.owner(), piece.symbol());
                uniCodeBoard[h.getIndex() - 1][v.getIndex() - 1] = uniCode;
            }
        }
        return uniCodeBoard;
    }
}
