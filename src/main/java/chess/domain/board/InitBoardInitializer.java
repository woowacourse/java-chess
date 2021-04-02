package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static chess.domain.piece.attribute.Color.BLACK;
import static chess.domain.piece.attribute.Color.WHITE;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static java.util.stream.Collectors.toList;

public class InitBoardInitializer implements BoardInitializer {
    private static InitBoardInitializer boardInitializer = new InitBoardInitializer();

    private InitBoardInitializer() {
    }

    public static Board getBoard() {
        return boardInitializer.createBoard(initialize());
    }

    @Override
    public Board createBoard(List<Square> squares) {
        return Board.of(squares);
    }

    private static List<Square> initialize() {
        List<Square> board = createBlankSquare();

        board.addAll(createColoredExceptPawn(ONE, WHITE));

        board.addAll(createPawns(TWO, WHITE));

        board.addAll(createColoredExceptPawn(EIGHT, BLACK));

        board.addAll(createPawns(SEVEN, BLACK));

        return board;
    }

    private static List<Square> createBlankSquare() {
        return Rank.getBlankRanks().stream()
                .flatMap(rank ->
                        Arrays.stream(File.values())
                                .map(file -> Position.of(file, rank))
                                .map(position -> new Square(position, Blank.getInstance()))
                )
                .collect(Collectors.collectingAndThen(toList(), ArrayList::new));
    }

    private static List<Square> createColoredExceptPawn(Rank rank, Color color) {
        return Arrays.asList(
                new Square(Position.of(A, rank), new Rook(color)),
                new Square(Position.of(B, rank), new Knight(color)),
                new Square(Position.of(C, rank), new Bishop(color)),
                new Square(Position.of(D, rank), new Queen(color)),
                new Square(Position.of(E, rank), new King(color)),
                new Square(Position.of(F, rank), new Bishop(color)),
                new Square(Position.of(G, rank), new Knight(color)),
                new Square(Position.of(H, rank), new Rook(color))
        );
    }

    private static List<Square> createPawns(Rank rank, Color color) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .map(position -> new Square(position, new Pawn(color)))
                .collect(toList());
    }
}

