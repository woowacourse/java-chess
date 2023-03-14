package chessgame;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import chessgame.point.File;
import chessgame.point.Point;
import chessgame.point.Rank;

public class ChessBoardFactory {
    public static Map<Point, Pieces> create() {
        Map<Point, Pieces> initialBoard = new LinkedHashMap<>();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                initialBoard.put(Point.of(file, rank), generateInitialPiece(rank, File.valueOf(file.name()).ordinal()));
            }
        }

        return initialBoard;
    }
    private static Pieces generateInitialPiece(Rank rank,int idx){
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
    private static List<Pieces> getPieces(Team team) {
        List<Pieces> pieces = List.of(Rook.from(team),
            Knight.from(team),
            Bishop.from(team),
            Queen.from(team),
            King.from(team),
            Bishop.from(team),
            Knight.from(team),
            Rook.from(team));
        return pieces;
    }
}
