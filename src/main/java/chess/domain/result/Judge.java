package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;

import java.util.HashMap;
import java.util.Map;

public class Judge {

    public static final Color BLACK_SIDE = Color.BLACK;
    public static final Color WHITE_SIDE = Color.WHITE;
    public static final Color NOTHING_SIDE = Color.NOTHING;

    public static Map<Color, Score> calculateScore(Board board) {
        Map<Color, Score> scoreBySide = new HashMap<>();

        for (File file : File.values()) {
            double whitePawnCount = 0;
            double blackPawnCount = 0;
            for (Rank rank : Rank.values()) {
                Piece piece = board.findPiece(file, rank);
                Color color = piece.getColor();
                if (color.equals(BLACK_SIDE) || color.equals(WHITE_SIDE)) {
                    Score pieceScore = new Score(piece.getRole().getScore());
                    scoreBySide.put(color, scoreBySide.getOrDefault(color, new Score(0)).sum(pieceScore));
                    if (piece.isRole(Role.PAWN) || piece.isRole(Role.INITIAL_PAWN)) {
                        if (color.equals(BLACK_SIDE)) {
                            blackPawnCount++;
                        }
                        if (color.equals(WHITE_SIDE)) {
                            whitePawnCount++;
                        }
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

    public static Color findSideKingDied(Board board) {
        boolean whiteKingAlive = false;
        boolean blackKingAlive = false;

        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Piece piece = board.findPiece(file, rank);
                if (piece.isRole(Role.KING)) {
                    if (piece.getColor().equals(BLACK_SIDE)) {
                        blackKingAlive = true;
                    }
                    if (piece.getColor().equals(WHITE_SIDE)) {
                        whiteKingAlive = true;
                    }
                }
            }
        }
        if (whiteKingAlive && !blackKingAlive) {
            return BLACK_SIDE;
        }
        if (blackKingAlive && !whiteKingAlive) {
            return WHITE_SIDE;
        }
        return NOTHING_SIDE;
    }
}
