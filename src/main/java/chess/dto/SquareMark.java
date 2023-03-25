package chess.dto;

import chess.domain.chessboard.Square;
import chess.domain.piece.Empty;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;
import chess.domain.piece.state.Bishop;
import chess.domain.piece.state.King;
import chess.domain.piece.state.Knight;
import chess.domain.piece.state.Pawn;
import chess.domain.piece.state.Queen;
import chess.domain.piece.state.Rook;
import java.util.Arrays;

public enum SquareMark {
    EMPTY(".", Empty.class),
    PAWN("P", Pawn.class),
    ROOK("R", Rook.class),
    KNIGHT("N", Knight.class),
    BISHOP("B", Bishop.class),
    QUEEN("Q", Queen.class),
    KING("K", King.class);

    private final String mark;
    private final Class<? extends PieceState> pieceClass;

    SquareMark(final String mark, final Class<? extends PieceState> pieceClass) {
        this.mark = mark;
        this.pieceClass = pieceClass;
    }

    public static String getMarkBySquare(final Square square) {
        final String squareMark = getSquareMarkByPiece(square.getPieceState());

        if (EMPTY.mark.equals(squareMark)) {
            return EMPTY.mark;
        }

        return getMarkByTeam(squareMark, square.getPieceState().getTeam());
    }

    private static String getMarkByTeam(final String squareMark, final Team team) {
        if (team.equals(Team.WHITE)) {
            return squareMark.toLowerCase();
        }
        return squareMark;
    }

    private static String getSquareMarkByPiece(final PieceState pieceState) {
        return Arrays.stream(SquareMark.values())
                .filter(squareMark -> pieceState.getClass() == squareMark.pieceClass)
                .map(SquareMark::getMark)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 종류의 기물입니다."));
    }

    private String getMark() {
        return mark;
    }
}
