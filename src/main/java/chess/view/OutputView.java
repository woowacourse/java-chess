package chess.view;

import chess.domain.Board;
import chess.domain.Piece;
import chess.domain.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final int MINIMUM_FILE = 1;
    private static final int MAXIMUM_FILE = 8;
    private static final int MINIMUM_RANK = 1;
    private static final int MAXIMUM_RANK = 8;

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        String commandMessage = Arrays.stream(GameCommand.values())
                .map(gameCommand -> "> %s".formatted(gameCommand.getHelperMessage()))
                .collect(Collectors.joining("\n"));

        System.out.println(commandMessage);
    }

    public void printBoard(final Board board) {
        Map<Position, Piece> positions = board.getBoard();
        for (int rank = MAXIMUM_RANK; rank >= MINIMUM_RANK; rank--) {
            printRankLine(positions, rank);
        }
        System.out.println();
    }

    private void printRankLine(Map<Position, Piece> positions, int rank) {
        String rankLine = IntStream.rangeClosed(MINIMUM_FILE, MAXIMUM_FILE)
                .boxed()
                .map(file -> positions.get(new Position(rank, file)))
                .map(PieceSymbol::getDisplay)
                .collect(Collectors.joining(""));
        System.out.println(rankLine);
    }
}
