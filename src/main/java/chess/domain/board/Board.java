package chess.domain.board;

import static java.util.stream.Collectors.toList;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rank;
import chess.domain.pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {

    private static final int START_MIDDLE_ROW = 2;
    private static final int END_MIDDLE_ROW = 5;

    private final List<Rank> board;

    private Board(List<Rank> ranks) {
        this.board = ranks;
    }

    public static Board create() {
        List<Rank> ranks = new ArrayList<>();

        ranks.add(createFirstRank());
        ranks.add(createSecondRank());
        for (int row = START_MIDDLE_ROW; row <= END_MIDDLE_ROW; row++) {
            ranks.add(createMiddleRank(row));
        }
        ranks.add(createSecondLastRank());
        ranks.add(createLastRank());

        return new Board(ranks);
    }

    private static Rank createFirstRank() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(0, 0), new Rook(Team.BLACK)),
                new Square(new Position(0, 1), new Knight(Team.BLACK)),
                new Square(new Position(0, 2), new Bishop(Team.BLACK)),
                new Square(new Position(0, 3), new Queen(Team.BLACK)),
                new Square(new Position(0, 4), new King(Team.BLACK)),
                new Square(new Position(0, 5), new Bishop(Team.BLACK)),
                new Square(new Position(0, 6), new Knight(Team.BLACK)),
                new Square(new Position(0, 7), new Rook(Team.BLACK))
        ));

        return new Rank(squares);
    }

    private static Rank createSecondRank() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(1, 0), new Pawn(Team.BLACK)),
                new Square(new Position(1, 1), new Pawn(Team.BLACK)),
                new Square(new Position(1, 2), new Pawn(Team.BLACK)),
                new Square(new Position(1, 3), new Pawn(Team.BLACK)),
                new Square(new Position(1, 4), new Pawn(Team.BLACK)),
                new Square(new Position(1, 5), new Pawn(Team.BLACK)),
                new Square(new Position(1, 6), new Pawn(Team.BLACK)),
                new Square(new Position(1, 7), new Pawn(Team.BLACK))
        ));

        return new Rank(squares);
    }

    private static Rank createSecondLastRank() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(1, 0), new Pawn(Team.WHITE)),
                new Square(new Position(1, 1), new Pawn(Team.WHITE)),
                new Square(new Position(1, 2), new Pawn(Team.WHITE)),
                new Square(new Position(1, 3), new Pawn(Team.WHITE)),
                new Square(new Position(1, 4), new Pawn(Team.WHITE)),
                new Square(new Position(1, 5), new Pawn(Team.WHITE)),
                new Square(new Position(1, 6), new Pawn(Team.WHITE)),
                new Square(new Position(1, 7), new Pawn(Team.WHITE))
        ));

        return new Rank(squares);
    }

    private static Rank createLastRank() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(0, 0), new Rook(Team.WHITE)),
                new Square(new Position(0, 1), new Knight(Team.WHITE)),
                new Square(new Position(0, 2), new Bishop(Team.WHITE)),
                new Square(new Position(0, 3), new Queen(Team.WHITE)),
                new Square(new Position(0, 4), new King(Team.WHITE)),
                new Square(new Position(0, 5), new Bishop(Team.WHITE)),
                new Square(new Position(0, 6), new Knight(Team.WHITE)),
                new Square(new Position(0, 7), new Rook(Team.WHITE))
        ));

        return new Rank(squares);
    }

    private static Rank createMiddleRank(int row) {
        List<Square> squares = IntStream.range(0, 8)
                .mapToObj(col -> new Square(new Position(row, col), new EmptyPiece()))
                .collect(toList());

        return new Rank(squares);
    }

    public List<Rank> getBoard() {
        return List.copyOf(board);
    }
}
