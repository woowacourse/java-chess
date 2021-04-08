package chess.util;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;

public class PieceConverter {

    private static final String EMPTY_PIECE_SYMBOL = ".";
    private static final String ROOK_SYMBOL = "R";
    private static final String KNIGHT_SYMBOL = "N";
    private static final String BISHOP_SYMBOL = "B";
    private static final String QUEEN_SYMBOL = "Q";
    private static final String KING_SYMBOL = "K";
    private static final String PAWN_SYMBOL = "P";

    private PieceConverter() {
    }

    public static Piece parsePiece(final String symbol) {
        if (symbol.equals(EMPTY_PIECE_SYMBOL)) {
            return EmptyPiece.getInstance();
        }

        if (symbol.equalsIgnoreCase(ROOK_SYMBOL)) {
            return parseRook(symbol);
        }

        if (symbol.equalsIgnoreCase(KNIGHT_SYMBOL)) {
            return parseKnight(symbol);
        }

        if (symbol.equalsIgnoreCase(BISHOP_SYMBOL)) {
            return parseBishop(symbol);
        }

        if (symbol.equalsIgnoreCase(QUEEN_SYMBOL)) {
            return parseQueen(symbol);
        }

        if (symbol.equalsIgnoreCase(KING_SYMBOL)) {
            return parseKing(symbol);
        }

        if (symbol.equalsIgnoreCase(PAWN_SYMBOL)) {
            return parsePawn(symbol);
        }
        throw new IllegalArgumentException("해당 심볼에 맞는 체스말이 없습니다.");
    }

    private static Piece parseRook(final String symbol) {
        if (symbol.equals("r")) {
            return Rook.getInstanceOf(Owner.WHITE);
        }
        return Rook.getInstanceOf(Owner.BLACK);
    }

    private static Piece parseKnight(final String symbol) {
        if (symbol.equals("n")) {
            return Knight.getInstanceOf(Owner.WHITE);
        }
        return Knight.getInstanceOf(Owner.BLACK);
    }

    private static Piece parseBishop(final String symbol) {
        if (symbol.equals("b")) {
            return Bishop.getInstanceOf(Owner.WHITE);
        }
        return Bishop.getInstanceOf(Owner.BLACK);
    }

    private static Piece parseQueen(final String symbol) {
        if (symbol.equals("q")) {
            return Queen.getInstanceOf(Owner.WHITE);
        }
        return Queen.getInstanceOf(Owner.BLACK);
    }

    private static Piece parseKing(final String symbol) {
        if (symbol.equals("k")) {
            return King.getInstanceOf(Owner.WHITE);
        }
        return King.getInstanceOf(Owner.BLACK);
    }

    private static Piece parsePawn(final String symbol) {
        if (symbol.equals("p")) {
            return Pawn.getInstanceOf(Owner.WHITE);
        }
        return Pawn.getInstanceOf(Owner.BLACK);
    }
}
