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

    WHITE_PAWN("white_pawn", new Pawn(Team.WHITE)),
    BLACK_PAWN("black_pawn", new Pawn(Team.BLACK)),
    WHITE_ROOK("white_rook", new Rook(Team.WHITE)),
    BLACK_ROOK("black_rook", new Rook(Team.BLACK)),
    WHITE_KNIGHT("white_knight", new Knight(Team.WHITE)),
    BLACK_KNIGHT("black_knight", new Knight(Team.BLACK)),
    WHITE_BISHOP("white_bishop", new Bishop(Team.WHITE)),
    BLACK_BISHOP("black_bishop", new Bishop(Team.BLACK)),
    WHITE_QUEEN("white_queen", new Queen(Team.WHITE)),
    BLACK_QUEEN("black_queen", new Queen(Team.BLACK)),
    WHITE_KING("white_king", new King(Team.WHITE)),
    BLACK_KING("black_king", new King(Team.BLACK)),
    BLANK("blank", new Blank());

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
