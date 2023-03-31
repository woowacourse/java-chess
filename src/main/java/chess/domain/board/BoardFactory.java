package chess.domain.board;

import static chess.domain.position.FileCoordinate.A;
import static chess.domain.position.FileCoordinate.B;
import static chess.domain.position.FileCoordinate.C;
import static chess.domain.position.FileCoordinate.D;
import static chess.domain.position.FileCoordinate.E;
import static chess.domain.position.FileCoordinate.F;
import static chess.domain.position.FileCoordinate.G;
import static chess.domain.position.FileCoordinate.H;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.FileCoordinate;
import chess.domain.position.Position;
import chess.domain.position.RankCoordinate;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private final static Map<Position, Piece> INIT_CHESS_BOARD = new HashMap<>();

    static {
        for (RankCoordinate rankCoordinate : RankCoordinate.values()) {
            createRank(INIT_CHESS_BOARD, rankCoordinate);
        }
    }

    private static void createRank(Map<Position, Piece> boards, RankCoordinate rankCoordinate) {
        Color initColor = getMatchColor(rankCoordinate);
        if (isSideRank(rankCoordinate)) {
            putSideRank(boards, rankCoordinate, initColor);
        }
        if (isPawnRank(rankCoordinate)) {
            put(boards, rankCoordinate, new Pawn(initColor));
        }
        if (isEmptyRank(rankCoordinate)) {
            put(boards, rankCoordinate, Empty.create());
        }
    }

    private static boolean isSideRank(RankCoordinate rankCoordinate) {
        return rankCoordinate == RankCoordinate.ONE || rankCoordinate == RankCoordinate.EIGHT;
    }

    private static boolean isPawnRank(RankCoordinate rankCoordinate) {
        return rankCoordinate == RankCoordinate.TWO || rankCoordinate == RankCoordinate.SEVEN;
    }

    private static boolean isEmptyRank(RankCoordinate rankCoordinate) {
        return rankCoordinate == RankCoordinate.THREE || rankCoordinate == RankCoordinate.FOUR
                || rankCoordinate == RankCoordinate.FIVE || rankCoordinate == RankCoordinate.SIX;
    }

    private static Color getMatchColor(RankCoordinate rankCoordinate) {
        if (isWhiteColor(rankCoordinate)) {
            return Color.WHITE;
        }
        if (isBlackColor(rankCoordinate)) {
            return Color.BLACK;
        }
        return Color.EMPTY;
    }

    private static boolean isBlackColor(RankCoordinate rankCoordinate) {
        return rankCoordinate == RankCoordinate.EIGHT || rankCoordinate == RankCoordinate.SEVEN;
    }

    private static boolean isWhiteColor(RankCoordinate rankCoordinate) {
        return rankCoordinate == RankCoordinate.ONE || rankCoordinate == RankCoordinate.TWO;
    }

    private static void putSideRank(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Color color) {
        boards.put(new Position(A, rankCoordinate), new Rook(color));
        boards.put(new Position(B, rankCoordinate), new Knight(color));
        boards.put(new Position(C, rankCoordinate), new Bishop(color));
        boards.put(new Position(D, rankCoordinate), new Queen(color));
        boards.put(new Position(E, rankCoordinate), new King(color));
        boards.put(new Position(F, rankCoordinate), new Bishop(color));
        boards.put(new Position(G, rankCoordinate), new Knight(color));
        boards.put(new Position(H, rankCoordinate), new Rook(color));
    }

    private static void put(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Piece piece) {
        for (FileCoordinate value : FileCoordinate.values()) {
            boards.put(new Position(value, rankCoordinate), piece);
        }
    }

    public static Map<Position, Piece> createBoard() {
        return new HashMap<>(INIT_CHESS_BOARD);
    }
}
