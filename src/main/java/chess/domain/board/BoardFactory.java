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
            addPawnPieces(boards, rankCoordinate, team);
        }
        if (rankType == EMPTY_RANK) {
            addEmptyPieces(boards, rankCoordinate);
        }
    }

    private static void addSidePieces(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Team team) {
        boards.put(new Position(A, rankCoordinate), new Rook(team, new Position(A, rankCoordinate)));
        boards.put(new Position(B, rankCoordinate), new Knight(team, new Position(B, rankCoordinate)));
        boards.put(new Position(C, rankCoordinate), new Bishop(team, new Position(C, rankCoordinate)));
        boards.put(new Position(D, rankCoordinate), new Queen(team, new Position(D, rankCoordinate)));
        boards.put(new Position(E, rankCoordinate), new King(team, new Position(E, rankCoordinate)));
        boards.put(new Position(F, rankCoordinate), new Bishop(team, new Position(F, rankCoordinate)));
        boards.put(new Position(G, rankCoordinate), new Knight(team, new Position(G, rankCoordinate)));
        boards.put(new Position(H, rankCoordinate), new Rook(team, new Position(H, rankCoordinate)));
    }

    private static void addPawnPieces(Map<Position, Piece> boards, RankCoordinate rankCoordinate, Team team) {
        for (FileCoordinate value : FileCoordinate.getSortedFileCoordinates()) {
            Position position = new Position(value, rankCoordinate);
            boards.put(position, new Pawn(team, position));
        }
    }

    private static void addEmptyPieces(Map<Position, Piece> boards, RankCoordinate rankCoordinate) {
        for (FileCoordinate value : FileCoordinate.getSortedFileCoordinates()) {
            Position position = new Position(value, rankCoordinate);
            boards.put(position, Empty.create(position));
        }
    }
}
