package chess.view.output;

import chess.domain.board.TurnTrackerBoard;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.input.command.GameCommand;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final int MINIMUM_FILE_RANGE = 1;
    private static final int MAXIMUM_FILE_RANGE = 8;
    private static final int MINIMUM_RANK_RANGE = 1;
    private static final int MAXIMUM_RANK_RANGE = 8;

    public static void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        String commandMessage = Arrays.stream(GameCommand.values())
                .map(gameCommand -> "> %s".formatted(gameCommand.getHelperMessage()))
                .collect(Collectors.joining("\n"));

        System.out.println(commandMessage);
    }

    public static void printBoard(final TurnTrackerBoard board) {
        Map<Position, Piece> positions = board.getBoard();
        for (int rank = MAXIMUM_RANK_RANGE; rank >= MINIMUM_RANK_RANGE; rank--) {
            printRankLine(positions, rank);
        }
        System.out.println();
    }

    private static void printRankLine(final Map<Position, Piece> positions, final int rank) {
        String rankLine = IntStream.rangeClosed(MINIMUM_FILE_RANGE, MAXIMUM_FILE_RANGE)
                .boxed()
                .map(file -> positions.get(new Position(file, rank)))
                .map(PieceSymbol::getDisplay)
                .collect(Collectors.joining(""));
        System.out.println(rankLine);
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
