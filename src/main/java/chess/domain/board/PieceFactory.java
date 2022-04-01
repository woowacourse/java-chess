package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.Arrays;

public enum PieceFactory {

    WHITE_PAWN("white pawn", new Pawn(Team.WHITE)),
    BLACK_PAWN("black pawn", new Pawn(Team.BLACK)),
    WHITE_ROOK("white rook", new Rook(Team.WHITE)),
    BLACK_ROOK("black rook", new Rook(Team.BLACK)),
    WHITE_KNIGHT("white knight", new Knight(Team.WHITE)),
    BLACK_KNIGHT("black knight", new Knight(Team.BLACK)),
    WHITE_BISHOP("white bishop", new Bishop(Team.WHITE)),
    BLACK_BISHOP("black bishop", new Bishop(Team.BLACK)),
    WHITE_QUEEN("white queen", new Queen(Team.WHITE)),
    BLACK_QUEEN("black queen", new Queen(Team.BLACK)),
    WHITE_KING("white king", new King(Team.WHITE)),
    BLACK_KING("black king", new King(Team.BLACK)),
    BLANK(" ", new Blank());

    private final String name;
    private final Piece piece;

    PieceFactory(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }

    public static Piece createPiece(final String name) {
        return Arrays.stream(values())
                .filter(value -> name.equals(value.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 피스입니다."))
                .piece;
    }
}
