package chess.view;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.dto.MoveArgumentDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final InputView INSTANCE = new InputView(new Scanner(System.in));
    private static final Map<String, CommandType> COMMANDS = Map.of(
            "start", CommandType.START,
            "end", CommandType.END,
            "move", CommandType.MOVE
    );
    private static final Map<String, File> FILE_TEXTS = Map.of(
            "a", File.A,
            "b", File.B,
            "c", File.C,
            "d", File.D,
            "e", File.E,
            "f", File.F,
            "g", File.G,
            "h", File.H
    );
    private static final Map<String, Rank> RANK_TEXTS = Map.of(
            "1", Rank.FIRST,
            "2", Rank.SECOND,
            "3", Rank.THIRD,
            "4", Rank.FOURTH,
            "5", Rank.FIFTH,
            "6", Rank.SIXTH,
            "7", Rank.SEVENTH,
            "8", Rank.EIGHTH
    );
    private static final String COMMAND_DELIMITER = " ";
    private static final int MOVE_ARGS_COUNT = 2;
    private static final int FILE_BEGIN_INDEX = 0;
    private static final int FILE_END_INDEX = 0;
    private static final int RANK_BEGIN_INDEX = 1;
    private static final int RANK_END_INDEX = 1;
    private static final int FILE_RANK_TEXT_LENGTH =
            (FILE_END_INDEX - FILE_BEGIN_INDEX + 1) + (RANK_END_INDEX - RANK_BEGIN_INDEX + 1);


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
        return Command.createNoArgCommand(COMMANDS.get(input));
    }

    private void validateStartCommand(String input) {
        List<String> commandTypeAndArgs = Arrays.asList(input.split(COMMAND_DELIMITER));
        String commandText = commandTypeAndArgs.get(0);

        validateExistCommand(commandText);
        CommandType commandType = COMMANDS.get(commandText);
        if (commandType != CommandType.START && commandType != CommandType.END) {
            throw new IllegalArgumentException("시작 커맨드는 start 또는 end만 가능합니다.");
        }
    }

    private void validateExistCommand(String commandText) {
        if (!COMMANDS.containsKey(commandText)) {
            throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
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
        validateExistCommand(commandTypeText);
        CommandType commandType = COMMANDS.get(commandTypeText);
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
        validateFileExist(fileText);
        return FILE_TEXTS.get(fileText);
    }

    private void validateFileExist(String fileText) {
        if (!FILE_TEXTS.containsKey(fileText)) {
            throw new IllegalArgumentException("존재하지 않는 파일입니다.");
        }
    }

    private Rank getRank(String fileRankText) {
        String rankText = fileRankText.substring(RANK_BEGIN_INDEX, RANK_END_INDEX + 1);
        validateRankExist(rankText);
        return RANK_TEXTS.get(rankText);
    }

    private void validateRankExist(String rankText) {
        if (!RANK_TEXTS.containsKey(rankText)) {
            throw new IllegalArgumentException("존재하지 않는 랭크입니다.");
        }
    }

    private void validateNotStart(CommandType commandType) {
        if (commandType == CommandType.START) {
            throw new IllegalArgumentException("게임이 이미 진행중입니다.");
        }
    }
}
