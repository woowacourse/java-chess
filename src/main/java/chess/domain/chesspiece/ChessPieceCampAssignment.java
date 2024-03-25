package chess.domain.chesspiece;

import chess.domain.chessboard.Numbering;

public class ChessPieceCampAssignment {

    private static final ChessPieceCampAssignment INSTANCE = new ChessPieceCampAssignment();

    private ChessPieceCampAssignment() {
    }

    public static ChessPieceCampAssignment getInstance() {
        return INSTANCE;
    }

    public Camp determineCamp(Numbering numbering) {
        if (numbering == Numbering.ONE || numbering == Numbering.TWO) {
            return Camp.WHITE;
        }
        if (numbering == Numbering.SEVEN || numbering == Numbering.EIGHT) {
            return Camp.BLACK;
        }
        return Camp.NONE;
    }
}
