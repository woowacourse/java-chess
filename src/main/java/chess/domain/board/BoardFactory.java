package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.board.FileCoordinate.*;

public class BoardFactory {

    public static Board createBoard() {
        Map<Position, Piece> boards = new HashMap<>();
        for (RankCoordinate rankCoordinate : RankCoordinate.values()) {
            Color color = Color.of(rankCoordinate);
            createRank(boards, rankCoordinate, color);
        }
        return new Board(boards);
    }

    private static void createRank(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Color color) {
        RankType rankType = RankType.of(rankCoordinate);
        if (rankType.isSideRank()) {
            createSidePieces(boards, rankCoordinate, color);
        }
        if (rankType.isPawnRank()) {
            createPiecesBy(boards, rankCoordinate, new Pawn(color));
        }
        if (rankType.isEmptyRank()) {
            createPiecesBy(boards, rankCoordinate, Empty.create());
        }
    }

    private static void createSidePieces(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Color color) {
        boards.put(new Position(A, rankCoordinate), new Rook(color));
        boards.put(new Position(B, rankCoordinate), new Knight(color));
        boards.put(new Position(C, rankCoordinate), new Bishop(color));
        boards.put(new Position(D, rankCoordinate), new Queen(color));
        boards.put(new Position(E, rankCoordinate), new King(color));
        boards.put(new Position(F, rankCoordinate), new Bishop(color));
        boards.put(new Position(G, rankCoordinate), new Knight(color));
        boards.put(new Position(H, rankCoordinate), new Rook(color));
    }

    private static void createPiecesBy(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Piece piece) {
        for (FileCoordinate value : FileCoordinate.values()) {
            boards.put(new Position(value, rankCoordinate), piece);
        }
    }
}
