package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;

public enum PieceTypeView {
    BISHOP(Bishop.class, "b", "B"),
    KING(King.class, "k", "K"),
    KNIGHT(Knight.class, "n", "N"),
    PAWN(Pawn.class, "p", "P"),
    QUEEN(Queen.class, "q", "Q"),
    ROOK(Rook.class, "r", "R"),
    EMPTY(Empty.class, ".", "."),
    ;

    private final Class<? extends Piece> piece;
    private final String whiteTeamView;
    private final String blackTeamView;

    PieceTypeView(Class<? extends Piece> piece, String whiteTeamView, String blackTeamView) {
        this.piece = piece;
        this.whiteTeamView = whiteTeamView;
        this.blackTeamView = blackTeamView;
    }

    public static PieceTypeView of(Class<? extends Piece> piece) {
        return Arrays.stream(values())
                .filter(it -> it.piece.equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String getMessage(Color color) {
        if (color == Color.BLACK) {
            return this.blackTeamView;
        }
        return this.whiteTeamView;
    }
}
