package chessgame.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chessgame.domain.piece.Bishop;
import chessgame.domain.piece.King;
import chessgame.domain.piece.Knight;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Queen;
import chessgame.domain.piece.Rook;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

public class GameBoardFactory {
    public static Map<Point, Piece> create() {
        Map<Point, Piece> initialBoard = new HashMap<>();

        for (Rank rank : Rank.values()) {
            createOneLine(initialBoard, rank);
        }
        return initialBoard;
    }

    private static void createOneLine(Map<Point, Piece> initialBoard, Rank rank) {
        for (File file : File.values()) {
            Piece initialPiece = generateInitialPiece(rank, File.valueOf(file.name()).ordinal());
            addPieceToBoard(initialBoard, rank, file, initialPiece);
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

    private static void addPieceToBoard(Map<Point, Piece> initialBoard, Rank rank, File file, Piece initialPiece) {
        if (initialPiece != null) {
            initialBoard.put(Point.of(file, rank), initialPiece);
        }
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
