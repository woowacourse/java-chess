package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.board.FileCoordinate.*;

public class BoardFactory {

    public static Board createBoard() {
        Map<Position, Piece> boards = new HashMap<>();
        for (RankCoordinate rankCoordinate : RankCoordinate.values()) {
            Team team = Team.of(rankCoordinate);
            createRank(boards, rankCoordinate, team);
        }
        return new Board(boards);
    }

    private static void createRank(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Team team) {
        RankType rankType = RankType.of(rankCoordinate);
        if (rankType.isSideRank()) {
            createSidePieces(boards, rankCoordinate, team);
        }
        if (rankType.isPawnRank()) {
            createPiecesBy(boards, rankCoordinate, new Pawn(team));
        }
        if (rankType.isEmptyRank()) {
            createPiecesBy(boards, rankCoordinate, Empty.create());
        }
    }

    private static void createSidePieces(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Team team) {
        boards.put(new Position(A, rankCoordinate), new Rook(team));
        boards.put(new Position(B, rankCoordinate), new Knight(team));
        boards.put(new Position(C, rankCoordinate), new Bishop(team));
        boards.put(new Position(D, rankCoordinate), new Queen(team));
        boards.put(new Position(E, rankCoordinate), new King(team));
        boards.put(new Position(F, rankCoordinate), new Bishop(team));
        boards.put(new Position(G, rankCoordinate), new Knight(team));
        boards.put(new Position(H, rankCoordinate), new Rook(team));
    }

    private static void createPiecesBy(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Piece piece) {
        for (FileCoordinate value : FileCoordinate.values()) {
            boards.put(new Position(value, rankCoordinate), piece);
        }
    }
}
