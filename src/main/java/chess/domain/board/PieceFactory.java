package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Arrays;

public enum PieceFactory {

    WHITE_PAWN("white pawn", new Pawn(Team.WHITE));

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
