package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.UnitClass;

import java.util.HashMap;
import java.util.Map;

public enum Rule {
    ; // Empty enum

    private static Map<UnitClass, MoveRule> moveRuleMap = new HashMap<>();

    static {
        moveRuleMap.put(UnitClass.KING, new King());
        moveRuleMap.put(UnitClass.QUEEN, new Queen());
        moveRuleMap.put(UnitClass.BISHOP, new Bishop());
        moveRuleMap.put(UnitClass.KNIGHT, new Knight());
        moveRuleMap.put(UnitClass.ROOK, new Rook());
        moveRuleMap.put(UnitClass.PAWN, new Pawn());
    }

    static Piece getPiece(final Board board, final Square square) {
        try {
            return board.getPiece(square);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static boolean isValidMove(final Board board, final Square checkTarget, final Square destination) {
        final Piece piece = getPiece(board, checkTarget);
        if (piece == null) {
            return false;
        }
        final MoveRule rule = moveRuleMap.get(piece.getUnitClass());
        return rule.isValidMove(board, piece, checkTarget, destination);
    }
}
