package chess.domain.piece;

import chess.domain.piece.move_rule.*;

import java.util.Arrays;


public enum PieceType {
    BISHOP(3, "bishop", BishopMoveRule.getInstance()),
    ROOK(5, "rook", RookMoveRule.getInstance()),
    QUEEN(9, "queen", QueenMoveRule.getInstance()),
    KNIGHT(2.5, "knight", KnightMoveRule.getInstance()),
    KING(0, "king", KingMoveRule.getInstance()),
    BLACK_PAWN(1, "black_pawn", PawnMoveRule.getInstance(Color.BLACK)),
    WHITE_PAWN(1, "white_pawn", PawnMoveRule.getInstance(Color.WHITE)),
    BLANK(0, "blank", new BlankMoveRule());

    private final double score;
    private final String name;
    private final MoveRule rule;

    PieceType(double score, String name, MoveRule rule) {
        this.score = score;
        this.name = name;
        this.rule = rule;
    }

    public static PieceType from(String type) {
        return Arrays.stream(PieceType.values())
                .filter(t -> t.name.equals(type))
                .findAny().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 종류입니다."));
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public MoveRule getRule() {
        return rule;
    }
}
