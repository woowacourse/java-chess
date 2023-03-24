package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.HashMap;
import java.util.Map;

public class Judge {

    public static final Side BLACK_SIDE = Side.from(Color.BLACK);
    public static final Side WHITE_SIDE = Side.from(Color.WHITE);

    public static Map<Side, Score> calculateScore(Board board) {
        Map<Side, Score> scoreBySide = new HashMap<>();
        scoreBySide.put(BLACK_SIDE, new Score(0));
        scoreBySide.put(WHITE_SIDE, new Score(0));

        for (File file : File.values()) {
            double whitePawnCount = 0;
            double blackPawnCount = 0;
            for (Rank rank : Rank.values()) {
                Piece piece = board.findPiece(file, rank);
                Score pieceScore = new Score(piece.getRole().getScore());
                Side side = piece.getSide();
                scoreBySide.put(side, scoreBySide.getOrDefault(side, new Score(0)).sum(pieceScore));
                if (piece.isRole(Role.PAWN) || piece.isRole(Role.INITIAL_PAWN)) {
                    if (side.equals(BLACK_SIDE)) {
                        blackPawnCount++;
                    }
                    if (side.equals(WHITE_SIDE)) {
                        whitePawnCount++;
                    }
                }
            }
            if (blackPawnCount > 1) {
                Score pawnPenalty = new Score(blackPawnCount * (-0.5));
                scoreBySide.put(BLACK_SIDE, scoreBySide.getOrDefault(BLACK_SIDE, new Score(0)).sum(pawnPenalty));
            }
            if (whitePawnCount > 1) {
                Score pawnPenalty = new Score(whitePawnCount * (-0.5));
                scoreBySide.put(WHITE_SIDE, scoreBySide.getOrDefault(WHITE_SIDE, new Score(0)).sum(pawnPenalty));
            }
        }
        return scoreBySide;
    }

    public static Side findSideKingDied(Board board) {
        boolean whiteKingAlive = false;
        boolean blackKingAlive = false;

        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Piece piece = board.findPiece(file, rank);
                if (piece.isRole(Role.KING)) {
                    if (piece.getSide().equals(BLACK_SIDE)) {
                        blackKingAlive = true;
                    }
                    if (piece.getSide().equals(WHITE_SIDE)) {
                        whiteKingAlive = true;
                    }
                }
            }
        }
        if (whiteKingAlive && !blackKingAlive) {
            return Side.from(Color.BLACK);
        }
        if (blackKingAlive && !whiteKingAlive) {
            return Side.from(Color.WHITE);
        }
        return Side.from(Color.NOTHING);
    }
}
