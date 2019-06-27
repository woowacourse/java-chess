package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Position;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.UnitClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    static Optional<Piece> getPiece(final Board board, final Square square) {
        try {
            return Optional.ofNullable(board.getPiece(square));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    static Optional<Piece> getPiece(final Board board, final Optional<Square> square) {
        try {
            return Optional.ofNullable(board.getPiece(square.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static boolean isValidMove(final Board board, final Square checkTarget, final Square destination) {
        final Optional<Piece> maybePiece = getPiece(board, checkTarget);
        if (maybePiece.isEmpty()) {
            return false;
        }
        final Piece piece = maybePiece.get();
        final PieceRule rule = ruleMap.get(piece.getUnitClass());
        return rule.isValidMove(board, piece, checkTarget, destination);
    }

    public static double getPieceScore(final Board board, final Position position) {
        final PieceRule rule = ruleMap.get(position.getPiece().getUnitClass());
        return rule.getPieceScore(board, position);
    }
}
