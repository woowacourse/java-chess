package chess.view;

import chess.domain.piece.*;

import java.util.Arrays;

public enum PieceMapper {
    BISHOP(Bishop.class, "b", "B"),
    KING(King.class, "k", "K"),
    KNIGHT(Knight.class, "n", "N"),
    PAWN(Pawn.class, "p", "P"),
    QUEEN(Queen.class, "q", "Q"),
    ROOK(Rook.class, "r", "R"),
    EMPTY(Empty.class, ".", "."),
    ;

    private static final String INVALID_PIECE_MESSAGE = "잘못된 말을 입력했습니다.";

    private final Class<? extends Piece> piece;
    private final String whiteTeamView;
    private final String blackTeamView;

    PieceMapper(Class<? extends Piece> piece, String whiteTeamView, String blackTeamView) {
        this.piece = piece;
        this.whiteTeamView = whiteTeamView;
        this.blackTeamView = blackTeamView;
    }

    public static PieceMapper of(Class<? extends Piece> piece) {
        return Arrays.stream(values())
                .filter(it -> it.piece.equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PIECE_MESSAGE));
    }

    String getMessage(Team team) {
        if (team == Team.BLACK) {
            return this.blackTeamView;
        }
        return this.whiteTeamView;
    }
}
