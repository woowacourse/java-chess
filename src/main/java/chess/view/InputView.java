package chess.view;

import chess.controller.GameCommand;
import chess.controller.MoveRequest;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = " ";
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final int SOURCE_COMMAND_INDEX = 1;
    private static final int TARGET_COMMAND_INDEX = 2;
    private static final Position DEFAULT_POSITION = new Position(File.A, Rank.FIRST);
    private static final int COMMAND_INDEX = 0;
    private static final int POSITION_INDEX = 1;

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public GameCommand readGameCommand() {
        final String command = readInput();

        validateBlank(command);

        return GameCommand.from(command);
    }

    public MoveRequest readPlayCommand() {
        System.out.println();
        final String input = readInput();

        final List<String> commands = Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toList());

        return createPlayRequest(commands);
    }

    private MoveRequest createPlayRequest(final List<String> commands) {
        final GameCommand gameCommand = GameCommand.from(commands.get(MOVE_COMMAND_INDEX));
        if (!GameCommand.MOVE.equals(gameCommand)) {
            return new MoveRequest(gameCommand, DEFAULT_POSITION, DEFAULT_POSITION);
        }

        final Position source = convertPosition(commands.get(SOURCE_COMMAND_INDEX));
        final Position target = convertPosition(commands.get(TARGET_COMMAND_INDEX));

        validatePosition(source, target);

        return new MoveRequest(gameCommand, source, target);
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

        return new Position(file, rank);
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
