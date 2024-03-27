package chess.view;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.dto.MoveArgumentDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String COMMAND_DELIMITER = " ";
    private static final int MOVE_ARGS_COUNT = 2;
    private static final int FILE_BEGIN_INDEX = 0;
    private static final int FILE_END_INDEX = 0;
    private static final int RANK_BEGIN_INDEX = 1;
    private static final int RANK_END_INDEX = 1;
    private static final int FILE_RANK_TEXT_LENGTH =
            (FILE_END_INDEX - FILE_BEGIN_INDEX + 1) + (RANK_END_INDEX - RANK_BEGIN_INDEX + 1);
    private static final InputView INSTANCE = new InputView(new Scanner(System.in));

    private final Scanner scanner;

    private InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public Command readStartCommand() {
        String input = scanner.nextLine();
        validateStartCommand(input);
        return Command.createNoArgCommand(CommandTypeView.find(input));
    }

    private void validateStartCommand(String input) {
        List<String> commandTypeAndArgs = Arrays.asList(input.split(COMMAND_DELIMITER));
        String commandText = commandTypeAndArgs.get(0);

        CommandType commandType = CommandTypeView.find(commandText);
        if (commandType != CommandType.START && commandType != CommandType.END) {
            throw new IllegalArgumentException("시작 커맨드는 start 또는 end만 가능합니다.");
        }
    }

    public Command readCommand() {
        String input = scanner.nextLine();
        List<String> commandTypeAndArgs = Arrays.asList(input.split(COMMAND_DELIMITER));
        CommandType commandType = makeCommandType(commandTypeAndArgs);

        if (commandType == CommandType.MOVE) {
            List<String> argsText = commandTypeAndArgs.subList(1, commandTypeAndArgs.size());

            return new Command(commandType, List.of(makeMoveArgumentDto(argsText)));
        }

        return Command.createNoArgCommand(commandType);
    }

    private CommandType makeCommandType(List<String> splittedCommand) {
        String commandTypeText = splittedCommand.get(0);
        CommandType commandType = CommandTypeView.find(commandTypeText);
        validateNotStart(commandType);
        return commandType;
    }

    // TODO: 가독성 개선
    private MoveArgumentDto makeMoveArgumentDto(List<String> argsText) {
        validateMoveArgsCount(argsText);
        String startFileRank = argsText.get(0);
        validateFileRankTextLength(startFileRank);
        File startFile = getFile(startFileRank);
        Rank startRank = getRank(startFileRank);
        String endFileRank = argsText.get(1);
        validateFileRankTextLength(endFileRank);
        File endFile = getFile(endFileRank);
        Rank endRank = getRank(endFileRank);
        return new MoveArgumentDto(startFile, startRank, endFile, endRank);
    }

    private void validateMoveArgsCount(List<String> argsText) {
        if (argsText.size() != MOVE_ARGS_COUNT) {
            throw new IllegalArgumentException("move는 source와 target을 함께 입력해줘야 합니다.");
        }
    }

    private void validateFileRankTextLength(String startFileRank) {
        if (startFileRank.length() != FILE_RANK_TEXT_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("move의 source와 target은 %d글자 이어야 합니다.", FILE_RANK_TEXT_LENGTH));
        }
    }

    private File getFile(String fileRankText) {
        String fileText = fileRankText.substring(FILE_BEGIN_INDEX, FILE_END_INDEX + 1);
        return FileView.find(fileText);
    }

    private Rank getRank(String fileRankText) {
        String rankText = fileRankText.substring(RANK_BEGIN_INDEX, RANK_END_INDEX + 1);
        return RankView.find(rankText);
    }

    private void validateNotStart(CommandType commandType) {
        if (commandType == CommandType.START) {
            throw new IllegalArgumentException("게임이 이미 진행중입니다.");
        }
    }
}
