package chess.view;

import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import chess.domain.piece.Side;
import java.util.Arrays;

public enum PieceName {
    WHITE_KING("k", King.class, Side.WHITE),
    WHITE_QUEEN("q", Queen.class, Side.WHITE),
    WHITE_KNIGHT("n", Knight.class, Side.WHITE),
    WHITE_BISHOP("b", Bishop.class, Side.WHITE),
    WHITE_ROOK("r", Rook.class, Side.WHITE),
    WHITE_PAWN("p", Pawn.class, Side.WHITE),
    BLACK_KING("K", King.class, Side.BLACK),
    BLACK_QUEEN("Q", Queen.class, Side.BLACK),
    BLACK_KNIGHT("N", Knight.class, Side.BLACK),
    BLACK_BISHOP("B", Bishop.class, Side.BLACK),
    BLACK_ROOK("R", Rook.class, Side.BLACK),
    BLACK_PAWN("P", Pawn.class, Side.BLACK);

    private final String value;
    private final Class<? extends Piece> type;
    private final Side side;

    PieceName(final String value, final Class<? extends Piece> type, final Side side) {
        this.value = value;
        this.type = type;
        this.side = side;
    }

    public static String of(Class<? extends Piece> type, Side side) {
        PieceName pieceName = Arrays.stream(values())
                .filter(piece -> piece.type == type && piece.side == side)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 기물, 진영에 맞는 이름이 존재하지 않습니다."));

        return pieceName.value;
    }
}
