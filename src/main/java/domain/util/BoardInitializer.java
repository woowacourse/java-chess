package domain.util;

import domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public class BoardInitializer {
    private BoardInitializer() {
    }

    public static List<List<Piece>> initializeBoard() {
        List<List<Piece>> status = new ArrayList<>();
        initializeFirstWhiteRank(status);
        initializeSecondWhiteRank(status);
        initializeEmptyRanks(status);
        initializeSecondBlackRank(status);
        initializeFirstBlackRank(status);

        return status;
    }

    private static void initializeEmptyRanks(List<List<Piece>> status) {
        for (int rankIndex = 2; rankIndex <= 5; rankIndex++) {
            initializeEmptyRank(status);
        }
    }

    private static void initializeEmptyRank(List<List<Piece>> status) {
        List<Piece> rank = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
            rank.add(new Empty());
        }
        status.add(rank);
    }

    private static void initializeFirstWhiteRank(List<List<Piece>> status) {
        List<Piece> rank = new ArrayList<>();
        rank.add(new WhiteRook());
        rank.add(new WhiteKnight());
        rank.add(new WhiteBishop());
        rank.add(new WhiteQueen());
        rank.add(new WhiteKing());
        rank.add(new WhiteBishop());
        rank.add(new WhiteKnight());
        rank.add(new WhiteRook());
        status.add(rank);
    }

    private static void initializeSecondWhiteRank(List<List<Piece>> status) {
        List<Piece> rank = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
            rank.add(new WhitePawn());
        }
        status.add(rank);
    }

    private static void initializeFirstBlackRank(List<List<Piece>> status) {
        List<Piece> rank = new ArrayList<>();
        rank.add(new BlackRook());
        rank.add(new BlackKnight());
        rank.add(new BlackBishop());
        rank.add(new BlackQueen());
        rank.add(new BlackKing());
        rank.add(new BlackBishop());
        rank.add(new BlackKnight());
        rank.add(new BlackRook());
        status.add(rank);
    }

    private static void initializeSecondBlackRank(List<List<Piece>> status) {
        List<Piece> rank = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
            rank.add(new BlackPawn());
        }
        status.add(rank);
    }
}
