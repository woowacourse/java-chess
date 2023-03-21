package chessgame.factory;

import chessgame.domain.Team;
import chessgame.domain.piece.*;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardFactory {
    public static Map<Point, Piece> create() {
        Map<Point, Piece> initialBoard = new HashMap<>();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                makeBoard(initialBoard, rank, file);
            }
        }
        return initialBoard;
    }

    private static void makeBoard(Map<Point, Piece> initialBoard, Rank rank, File file) {
        Piece initialPiece = generateInitialPiece(rank, File.valueOf(file.name()).ordinal());
        if (initialPiece != null) {
            initialBoard.put(Point.of(file, rank), initialPiece);
        }
    }

    private static Piece generateInitialPiece(Rank rank, int idx) {
        if (rank == Rank.EIGHT) {
            return getPieces(Team.BLACK).get(idx);
        }
        if (rank == Rank.SEVEN) {
            return Pawn.from(Team.BLACK);
        }
        if (rank == Rank.TWO) {
            return Pawn.from(Team.WHITE);
        }
        if (rank == Rank.ONE) {
            return getPieces(Team.WHITE).get(idx);
        }
        return null;
    }

    private static List<Piece> getPieces(Team team) {
        return List.of(Rook.from(team),
                Knight.from(team),
                Bishop.from(team),
                Queen.from(team),
                King.from(team),
                Bishop.from(team),
                Knight.from(team),
                Rook.from(team));
    }
}
