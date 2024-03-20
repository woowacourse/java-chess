package chess.chesspiece;

import chess.chessboard.Numbering;

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
        throw new IllegalArgumentException("[ERROR] 진영 배정에 유효한 위치가 아닙니다.");
    }
}
