package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.RuleStrategy.KnightRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.BishopRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.RookRuleStrategy;
import chess.domain.chessPiece.PieceColor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PieceTypeCache {
    private final static Map<String, PieceType> PIECE_TYPE_CACHE = new HashMap<>();

    static {
        for (PieceColor pieceColor : PieceColor.values()) {
            King king = new King(pieceColor, new KingRuleStrategy());
            Queen queen = new Queen(pieceColor, new QueenRuleStrategy());
            Bishop bishop = new Bishop(pieceColor, new BishopRuleStrategy());
            Rook rook = new Rook(pieceColor, new RookRuleStrategy());
            Knight knight = new Knight(pieceColor, new KnightRuleStrategy());
            Pawn pawn = new Pawn(pieceColor, pieceColor.getPawnRuleStrategy());

            PIECE_TYPE_CACHE.put(king.getName(), king);
            PIECE_TYPE_CACHE.put(queen.getName(), queen);
            PIECE_TYPE_CACHE.put(bishop.getName(), bishop);
            PIECE_TYPE_CACHE.put(rook.getName(), rook);
            PIECE_TYPE_CACHE.put(knight.getName(), knight);
            PIECE_TYPE_CACHE.put(pawn.getName(), pawn);
        }
    }

    public static PieceType from(String key) {
        Objects.requireNonNull(key, "체스 피스 타입의 key가 null입니다.");
        PieceType pieceType = PIECE_TYPE_CACHE.get(key);

        if (Objects.isNull(pieceType)) {
            throw new IllegalArgumentException("유효하지 않은 체스 피스 타입입니다.");
        }
        return pieceType;
    }
}
