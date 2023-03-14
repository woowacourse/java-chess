package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class RankFactory {

    public static Rank createRank(RankType rankType, RankCoordinate rankCoordinate, Color color) {
        if (rankType.isSideRank()) {
            return createSideRank(rankCoordinate, color);
        }
        if (rankType.isPawnRank()) {
            return createPawnRank(rankCoordinate, color);
        }
        return createEmptyRank(rankCoordinate, color);
    }

    private static Rank createSideRank(RankCoordinate rankCoordinate, Color color) {
        List<Square> squares = new ArrayList<>();
        squares.add(new Square(FileCoordinate.A, new Piece(PieceType.ROOK, color)));
        squares.add(new Square(FileCoordinate.B, new Piece(PieceType.KNIGHT, color)));
        squares.add(new Square(FileCoordinate.C, new Piece(PieceType.BISHOP, color)));
        squares.add(new Square(FileCoordinate.D, new Piece(PieceType.QUEEN, color)));
        squares.add(new Square(FileCoordinate.E, new Piece(PieceType.KING, color)));
        squares.add(new Square(FileCoordinate.F, new Piece(PieceType.BISHOP, color)));
        squares.add(new Square(FileCoordinate.G, new Piece(PieceType.KNIGHT, color)));
        squares.add(new Square(FileCoordinate.H, new Piece(PieceType.ROOK, color)));
        return new Rank(rankCoordinate, squares);
    }

    private static Rank createPawnRank(RankCoordinate rankCoordinate, Color color) {
        List<Square> squares = new ArrayList<>();
        for (FileCoordinate fileCoordinate : FileCoordinate.values()) {
            squares.add(new Square(fileCoordinate, new Piece(PieceType.PAWN, color)));
        }
        return new Rank(rankCoordinate, squares);
    }

    private static Rank createEmptyRank(RankCoordinate rankCoordinate, Color color) {
        List<Square> squares = new ArrayList<>();
        for (FileCoordinate fileCoordinate : FileCoordinate.values()) {
            squares.add(new Square(fileCoordinate, new Piece(PieceType.EMPTY, color)));
        }
        return new Rank(rankCoordinate, squares);
    }
}
