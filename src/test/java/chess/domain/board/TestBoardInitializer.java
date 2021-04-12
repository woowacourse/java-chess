package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TestBoardInitializer implements BoardInitializer {
    public static Board createTestBoard(List<Square> testCases) {
        List<Square> board = createBlankSquare();
        List<Square> replaceable = board.stream()
                .filter(blank -> testCases.stream()
                        .anyMatch(square -> square.isSamePosition(blank.getPosition())))
                .collect(toList());
        board.removeAll(replaceable);
        board.addAll(testCases);

        return Board.of(board);
    }

    @Override
    public Board createBoard(List<Square> squares) {
        return InitBoardInitializer.getBoard();
    }

    private static List<Square> createBlankSquare() {
        return Arrays.stream(Rank.values())
                .flatMap(rank ->
                        Arrays.stream(File.values())
                                .map(file -> Position.of(file, rank))
                                .map(position -> new Square(position, Blank.getInstance()))
                )
                .collect(Collectors.collectingAndThen(toList(), ArrayList::new));
    }


}
