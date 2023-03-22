package chess.domain.board;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.Team;
import chess.domain.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class BoardMaker {

    private static final int START_MIDDLE_ROW = 3;
    private static final int END_MIDDLE_ROW = 6;

    private BoardMaker() {
    }

    public static List<Line> create() {
        List<Line> lines = new ArrayList<>();

        lines.add(createFirstWhite());
        lines.add(createWhitePawn());
        for (int row = START_MIDDLE_ROW; row <= END_MIDDLE_ROW; row++) {
            lines.add(createEmptyPiece(row));
        }
        lines.add(createBlackPawn());
        lines.add(createLastBlack());

        return lines;
    }

    private static Line createFirstWhite() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(Rank.ONE, File.A), new Rook(Team.WHITE)),
                new Square(new Position(Rank.ONE, File.B), new Knight(Team.WHITE)),
                new Square(new Position(Rank.ONE, File.C), new Bishop(Team.WHITE)),
                new Square(new Position(Rank.ONE, File.D), new Queen(Team.WHITE)),
                new Square(new Position(Rank.ONE, File.E), new King(Team.WHITE)),
                new Square(new Position(Rank.ONE, File.F), new Bishop(Team.WHITE)),
                new Square(new Position(Rank.ONE, File.G), new Knight(Team.WHITE)),
                new Square(new Position(Rank.ONE, File.H), new Rook(Team.WHITE))
        ));

        return new Line(squares);
    }

    private static Line createWhitePawn() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(Rank.TWO, File.A), new Pawn(Team.WHITE)),
                new Square(new Position(Rank.TWO, File.B), new Pawn(Team.WHITE)),
                new Square(new Position(Rank.TWO, File.C), new Pawn(Team.WHITE)),
                new Square(new Position(Rank.TWO, File.D), new Pawn(Team.WHITE)),
                new Square(new Position(Rank.TWO, File.E), new Pawn(Team.WHITE)),
                new Square(new Position(Rank.TWO, File.F), new Pawn(Team.WHITE)),
                new Square(new Position(Rank.TWO, File.G), new Pawn(Team.WHITE)),
                new Square(new Position(Rank.TWO, File.H), new Pawn(Team.WHITE))
        ));
        return new Line(squares);
    }

    private static Line createEmptyPiece(int rank) {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(Rank.of(rank), File.A), new Pawn(Team.NEUTRALITY)),
                new Square(new Position(Rank.of(rank), File.B), new Pawn(Team.NEUTRALITY)),
                new Square(new Position(Rank.of(rank), File.C), new Pawn(Team.NEUTRALITY)),
                new Square(new Position(Rank.of(rank), File.D), new Pawn(Team.NEUTRALITY)),
                new Square(new Position(Rank.of(rank), File.E), new Pawn(Team.NEUTRALITY)),
                new Square(new Position(Rank.of(rank), File.F), new Pawn(Team.NEUTRALITY)),
                new Square(new Position(Rank.of(rank), File.G), new Pawn(Team.NEUTRALITY)),
                new Square(new Position(Rank.of(rank), File.H), new Pawn(Team.NEUTRALITY))
        ));
        return new Line(squares);
    }

    private static Line createBlackPawn() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(Rank.SEVEN, File.A), new Pawn(Team.BLACK)),
                new Square(new Position(Rank.SEVEN, File.B), new Pawn(Team.BLACK)),
                new Square(new Position(Rank.SEVEN, File.C), new Pawn(Team.BLACK)),
                new Square(new Position(Rank.SEVEN, File.D), new Pawn(Team.BLACK)),
                new Square(new Position(Rank.SEVEN, File.E), new Pawn(Team.BLACK)),
                new Square(new Position(Rank.SEVEN, File.F), new Pawn(Team.BLACK)),
                new Square(new Position(Rank.SEVEN, File.G), new Pawn(Team.BLACK)),
                new Square(new Position(Rank.SEVEN, File.H), new Pawn(Team.BLACK))
        ));
        return new Line(squares);
    }

    private static Line createLastBlack() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(Rank.EIGHT, File.A), new Rook(Team.BLACK)),
                new Square(new Position(Rank.EIGHT, File.B), new Knight(Team.BLACK)),
                new Square(new Position(Rank.EIGHT, File.C), new Bishop(Team.BLACK)),
                new Square(new Position(Rank.EIGHT, File.D), new Queen(Team.BLACK)),
                new Square(new Position(Rank.EIGHT, File.E), new King(Team.BLACK)),
                new Square(new Position(Rank.EIGHT, File.F), new Bishop(Team.BLACK)),
                new Square(new Position(Rank.EIGHT, File.G), new Knight(Team.BLACK)),
                new Square(new Position(Rank.EIGHT, File.H), new Rook(Team.BLACK))
        ));

        return new Line(squares);
    }
}
