package util;

import domain.Turn;
import domain.piece.*;
import domain.piece.Pawn;
import domain.point.File;
import domain.point.Point;
import domain.point.Rank;

import java.util.HashMap;
import java.util.Map;

import static domain.point.File.*;
import static domain.point.Rank.*;

public class BoardInitializer {

    private BoardInitializer() {
    }

    public static Map<Point, Piece> initializeBoard() {
        Map<Point, Piece> status = new HashMap<>();
        initializeFirstWhiteRank(status);
        initializeSecondRank(status);
        initializeEmptyRanks(status);
        initializeSevenRank(status);
        initializeEightRank(status);

        return status;
    }

    private static void initializeEmptyRanks(Map<Point, Piece> status) {
        initializeEmptyRank(status, THREE);
        initializeEmptyRank(status, FOUR);
        initializeEmptyRank(status, FIVE);
        initializeEmptyRank(status, SIX);
    }

    private static void initializeEmptyRank(Map<Point, Piece> status, Rank rank) {
        for (File file : File.values()) {
            status.put(new Point(file, rank), new Empty());
        }
    }

    private static void initializeFirstWhiteRank(Map<Point, Piece> status) {
        status.put(new Point(A, ONE), new Rook(Turn.WHITE));
        status.put(new Point(B, ONE), new Knight(Turn.WHITE));
        status.put(new Point(C, ONE), new Bishop(Turn.WHITE));
        status.put(new Point(D, ONE), new Queen(Turn.WHITE));
        status.put(new Point(E, ONE), new King(Turn.WHITE));
        status.put(new Point(F, ONE), new Bishop(Turn.WHITE));
        status.put(new Point(G, ONE), new Knight(Turn.WHITE));
        status.put(new Point(H, ONE), new Rook(Turn.WHITE));
    }

    private static void initializeSecondRank(Map<Point, Piece> status) {
        for (File file : File.values()) {
            status.put(new Point(file, TWO), new Pawn(Turn.WHITE));
        }
    }

    private static void initializeEightRank(Map<Point, Piece> status) {
        status.put(new Point(A, EIGHT), new Rook(Turn.BLACK));
        status.put(new Point(B, EIGHT), new Knight(Turn.BLACK));
        status.put(new Point(C, EIGHT), new Bishop(Turn.BLACK));
        status.put(new Point(D, EIGHT),new Queen(Turn.BLACK));
        status.put(new Point(E, EIGHT),new King(Turn.BLACK));
        status.put(new Point(F, EIGHT),new Bishop(Turn.BLACK));
        status.put(new Point(G, EIGHT),new Knight(Turn.BLACK));
        status.put(new Point(H, EIGHT),new Rook(Turn.BLACK));
    }

    private static void initializeSevenRank(Map<Point, Piece> status) {
        for (File file : File.values()) {
            status.put(new Point(file, SEVEN), new Pawn(Turn.BLACK));
        }
    }
}
