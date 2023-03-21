package chess.view;

import chess.controller.PlayRequest;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = " ";
    private static final String DEFAULT_POSITION = "Z0";
    private static final int COMMAND_INDEX = 0;
    private static final int POSITION_INDEX = 1;

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public PlayRequest readPlayCommand() {
        final String input = readInput();

        final List<String> commands = Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toList());
        validateInputCommand(commands);

        return createPlayRequest(commands);
    }

    private void validateInputCommand(final List<String> commands) {
        if (commands.size() > 3) {
            throw new IllegalArgumentException("");
        }
    }

    private PlayRequest createPlayRequest(final List<String> commands) {
        if (commands.size() < 3) {
            return new PlayRequest(commands.get(0), DEFAULT_POSITION, DEFAULT_POSITION);
        }
        return new PlayRequest(commands.get(0), commands.get(1), commands.get(2));
    }

    private void validatePosition(final Position source, final Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("같은 위치로 이동할 수 없습니다.");
        }
    }

    private Position convertPosition(final String inputSquare) {
        final String inputFile = inputSquare.substring(COMMAND_INDEX, POSITION_INDEX);
        final String inputRank = inputSquare.substring(POSITION_INDEX);
        final File file = File.findFile(inputFile);
        final int rankValue = mapToRankValue(inputRank);
        final Rank rank = Rank.findRank(rankValue);

        return Position.of(file, rank);
    }

    private int mapToRankValue(final String inputRank) {
        try {
            return Integer.parseInt(inputRank);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 행을 입력해주세요.");
        }
    }

    private void validateBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
    }

    private String readInput() {
        return scanner.nextLine()
                .trim();
    }
}
