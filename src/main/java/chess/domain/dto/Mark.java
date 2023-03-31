package chess.domain.dto;

import java.util.Arrays;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

public enum Mark {

    PAWN("p", Pawn.class),
    ROOK("r", Rook.class),
    KNIGHT("n", Knight.class),
    BISHOP("b", Bishop.class),
    QUEEN("q", Queen.class),
    KING("k", King.class);

    private final String mark;
    private final Class<? extends Piece> pieceClass;

    Mark(final String mark, final Class<? extends Piece> pieceClass) {
        this.mark = mark;
        this.pieceClass = pieceClass;
    }

    public static String from(final Piece piece) {
        Class<? extends Piece> pieceClass = piece.getClass();
        Team team = piece.getColor();
        String mark = Arrays.stream(Mark.values())
                .filter(it -> it.pieceClass == pieceClass)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 클래스는 존재하지 않습니다."))
                .mark;
        return makeMarkByColor(mark, team);
    }

    private static String makeMarkByColor(final String mark, final Team team) {
        if (team == Team.BLACK) {
            return mark.toUpperCase();
        }
        return mark;
    }
}
