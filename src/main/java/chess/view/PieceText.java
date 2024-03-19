package chess.view;

import chess.model.Bishop;
import chess.model.King;
import chess.model.Knight;
import chess.model.Pawn;
import chess.model.Piece;
import chess.model.Queen;
import chess.model.Rook;
import chess.model.Side;
import java.util.Arrays;

public enum PieceText {
    PAWN_BLACK_TEXT(new Pawn(Side.BLACK), "P"),
    ROOK_BLACK_TEXT(new Rook(Side.BLACK), "R"),
    KNIGHT_BLACK_TEXT(new Knight(Side.BLACK), "N"),
    BISHOP_BLACK_TEXT(new Bishop(Side.BLACK), "B"),
    QUEEN_BLACK_TEXT(new Queen(Side.BLACK), "Q"),
    KING_BLACK_TEXT(new King(Side.BLACK), "K"),

    PAWN_WHITE_TEXT(new Pawn(Side.WHITE), "p"),
    ROOK_WHITE_TEXT(new Rook(Side.WHITE), "r"),
    KNIGHT_WHITE_TEXT(new Knight(Side.WHITE), "n"),
    BISHOP_WHITE_TEXT(new Bishop(Side.WHITE), "b"),
    QUEEN_WHITE_TEXT(new Queen(Side.WHITE), "q"),
    KING_WHITE_TEXT(new King(Side.WHITE), "k"),
    ;

    private final Piece piece;
    private final String text;

    PieceText(Piece piece, String text) {
        this.piece = piece;
        this.text = text;
    }

    public static PieceText from(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceText -> pieceText.piece.equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("기물에 해당하는 메세지가 없습니다."));
    }

    public String getText() {
        return text;
    }
}
