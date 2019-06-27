package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Position;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.UnitClass;

import java.util.HashMap;
import java.util.Map;

public class Rule {
    private static Map<UnitClass, PieceRule> ruleMap = new HashMap<>();

    static {
        ruleMap.put(UnitClass.KING, new KingRule());
        ruleMap.put(UnitClass.QUEEN, new QueenRule());
        ruleMap.put(UnitClass.BISHOP, new BishopRule());
        ruleMap.put(UnitClass.KNIGHT, new KnightRule());
        ruleMap.put(UnitClass.ROOK, new RookRule());
        ruleMap.put(UnitClass.PAWN, new PawnRule());
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
        final PieceRule rule = ruleMap.get(piece.getUnitClass());
        return rule.isValidMove(board, piece, checkTarget, destination);
    }

    public static double getPieceScore(final Board board, final Position position) {
        final PieceRule rule = ruleMap.get(position.getPiece().getUnitClass());
        return rule.getPieceScore(board, position);
    }
}
