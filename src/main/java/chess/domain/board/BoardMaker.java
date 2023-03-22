package chess.domain.board;

import static java.util.stream.Collectors.toList;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.pawn.BlackPawn;
import chess.domain.pieces.pawn.WhitePawn;
import chess.domain.pieces.sliding.Bishop;
import chess.domain.pieces.sliding.Queen;
import chess.domain.pieces.sliding.Rook;
import chess.domain.pieces.nonsliding.King;
import chess.domain.pieces.nonsliding.Knight;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class BoardMaker {

    private static final int START_MIDDLE_ROW = 2;
    private static final int END_MIDDLE_ROW = 5;

    public List<Rank> createBoard() {
        List<Rank> ranks = new ArrayList<>();

        ranks.add(createBlackPiecesExpectPawn());
        ranks.add(createBlackPawns());
        for (int row = START_MIDDLE_ROW; row <= END_MIDDLE_ROW; row++) {
            ranks.add(createEmptyPieces(row));
        }
        ranks.add(createWhitePawns());
        ranks.add(createWhitePiecesExpectPawn());

        return ranks;
    }

    private Rank createBlackPiecesExpectPawn() {
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

    private Rank createBlackPawns() {
        List<Square> squares = IntStream.range(0, 8)
                .mapToObj(column -> new Square(new Position(1, column), new BlackPawn()))
                .collect(toList());

        return new Rank(squares);
    }

    private Rank createEmptyPieces(int row) {
        List<Square> squares = IntStream.range(0, 8)
                .mapToObj(column -> new Square(new Position(row, column), new EmptyPiece()))
                .collect(toList());

        return new Rank(squares);
    }

    private Rank createWhitePawns() {
        List<Square> squares = IntStream.range(0, 8)
                .mapToObj(column -> new Square(new Position(6, column), new WhitePawn()))
                .collect(toList());

        return new Rank(squares);
    }

    private Rank createWhitePiecesExpectPawn() {
        List<Square> squares = new ArrayList<>(List.of(
                new Square(new Position(7, 0), new Rook(Team.WHITE)),
                new Square(new Position(7, 1), new Knight(Team.WHITE)),
                new Square(new Position(7, 2), new Bishop(Team.WHITE)),
                new Square(new Position(7, 3), new Queen(Team.WHITE)),
                new Square(new Position(7, 4), new King(Team.WHITE)),
                new Square(new Position(7, 5), new Bishop(Team.WHITE)),
                new Square(new Position(7, 6), new Knight(Team.WHITE)),
                new Square(new Position(7, 7), new Rook(Team.WHITE))
        ));

        return new Rank(squares);
    }
}
