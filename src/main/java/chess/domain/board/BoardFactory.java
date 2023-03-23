package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.board.FileCoordinate.*;
import static chess.domain.board.RankType.*;

public class BoardFactory {

    public static Board createBoard() {
        Map<Position, Piece> boards = new HashMap<>();
        for (RankCoordinate rankCoordinate : RankCoordinate.getSortedRankCoordinates()) {
            Team team = Team.of(rankCoordinate);
            addRank(boards, rankCoordinate, team);
        }
        return new Board(boards);
    }

    private static void addRank(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Team team) {
        RankType rankType = RankType.of(rankCoordinate);
        if (rankType == SIDE_RANK) {
            addSidePieces(boards, rankCoordinate, team);
        }
        if (rankType == PAWN_RANK) {
            addPiecesBy(boards, rankCoordinate, new Pawn(team));
        }
        if (rankType == EMPTY_RANK) {
            addPiecesBy(boards, rankCoordinate, null);
        }
    }

    private static void addSidePieces(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Team team) {
        boards.put(new Position(A, rankCoordinate), new Rook(team));
        boards.put(new Position(B, rankCoordinate), new Knight(team));
        boards.put(new Position(C, rankCoordinate), new Bishop(team));
        boards.put(new Position(D, rankCoordinate), new Queen(team));
        boards.put(new Position(E, rankCoordinate), new King(team));
        boards.put(new Position(F, rankCoordinate), new Bishop(team));
        boards.put(new Position(G, rankCoordinate), new Knight(team));
        boards.put(new Position(H, rankCoordinate), new Rook(team));
    }

    private static void addPiecesBy(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Piece piece) {
        for (FileCoordinate value : FileCoordinate.getSortedFileCoordinates()) {
            boards.put(new Position(value, rankCoordinate), piece);
        }
    }
}
