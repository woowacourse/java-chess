package domain.util;

import domain.piece.*;
import domain.piece.bishop.BlackBishop;
import domain.piece.bishop.WhiteBishop;
import domain.piece.king.BlackKing;
import domain.piece.king.WhiteKing;
import domain.piece.knight.BlackKnight;
import domain.piece.knight.WhiteKnight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.queen.BlackQueen;
import domain.piece.queen.WhiteQueen;
import domain.piece.rook.BlackRook;
import domain.piece.rook.WhiteRook;
import domain.point.File;
import domain.point.Point;
import domain.point.Rank;

import java.util.*;

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
    }

    private static void initializeEmptyRank(Map<Point, Piece> status, Rank rank) {
        for (File file : File.values()) {
            status.put(new Point(file, rank), new Empty());
        }
    }

    private static void initializeFirstWhiteRank(Map<Point, Piece> status) {
        status.put(new Point(A, ONE), new WhiteRook());
        status.put(new Point(B, ONE), new WhiteKnight());
        status.put(new Point(C, ONE), new WhiteBishop());
        status.put(new Point(D, ONE), new WhiteQueen());
        status.put(new Point(E, ONE), new WhiteKing());
        status.put(new Point(F, ONE), new WhiteBishop());
        status.put(new Point(G, ONE), new WhiteKnight());
        status.put(new Point(H, ONE), new WhiteRook());
    }

    private static void initializeSecondRank(Map<Point, Piece> status) {
        for (File file : File.values()) {
            status.put(new Point(file, TWO), new WhitePawn());
        }
    }

    private static void initializeEightRank(Map<Point, Piece> status) {
        status.put(new Point(A, EIGHT), new BlackRook());
        status.put(new Point(B, EIGHT), new BlackKnight());
        status.put(new Point(C, EIGHT), new BlackBishop());
        status.put(new Point(D, EIGHT),new BlackQueen());
        status.put(new Point(E, EIGHT),new BlackKing());
        status.put(new Point(F, EIGHT),new BlackBishop());
        status.put(new Point(G, EIGHT),new BlackKnight());
        status.put(new Point(H, EIGHT),new BlackRook());
    }

    private static void initializeSevenRank(Map<Point, Piece> status) {
        for (File file : File.values()) {
            status.put(new Point(file, SEVEN), new BlackPawn());
        }
    }
}
