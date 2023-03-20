package chess.domain.board;

import static chess.domain.board.FileCoordinate.A;
import static chess.domain.board.FileCoordinate.B;
import static chess.domain.board.FileCoordinate.C;
import static chess.domain.board.FileCoordinate.D;
import static chess.domain.board.FileCoordinate.E;
import static chess.domain.board.FileCoordinate.F;
import static chess.domain.board.FileCoordinate.G;
import static chess.domain.board.FileCoordinate.H;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private final static Map<Position, Piece> INIT_CHESS_BOARD = new HashMap<>();

    static {
        for (RankCoordinate rankCoordinate : RankCoordinate.values()) {
            Color initColor = rankCoordinate.getInitColor();
            createRank(INIT_CHESS_BOARD, rankCoordinate, initColor);
        }
    }

    private static void createRank(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Color color) {
        RankType rankType = rankCoordinate.getRankType();
        if (rankType.isSideRank()) {
            putSideRank(boards, rankCoordinate, color);
        }
        if (rankType.isPawnRank()) {
            put(boards, rankCoordinate, new Pawn(color));
        }
        if (rankType.isEmptyRank()) {
            put(boards, rankCoordinate, Empty.create());
        }
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

    public static Board createBoard() {
        return new Board(new HashMap<>(INIT_CHESS_BOARD));
    }
}
