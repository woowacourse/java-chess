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
    public static final String COMMAND_DELIMITER = " ";

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
        validateExistCommand(input);
        CommandType commandType = COMMANDS.get(input);
        if (commandType != CommandType.START && commandType != CommandType.END) {
            throw new IllegalArgumentException("시작 커맨드는 start 또는 end만 가능합니다.");
        }
    }

    private static void validateExistCommand(String commandText) {
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

    private MoveArgumentDto makeMoveArgumentDto(List<String> argsText) {
        String startFileRank = argsText.get(0);
        File startFile = getFile(startFileRank);
        Rank startRank = getRank(startFileRank);

        String endFileRank = argsText.get(1);
        File endFile = getFile(endFileRank);
        Rank endRank = getRank(endFileRank);

        return new MoveArgumentDto(startFile, startRank, endFile, endRank);
    }

    private File getFile(String fileRankText) {
        String fileText = fileRankText.substring(0, 1);
        validateFileExist(fileText);
        return FILE_TEXTS.get(fileText);
    }

    private void validateFileExist(String fileText) {
        if (!FILE_TEXTS.containsKey(fileText)) {
            throw new IllegalArgumentException("존재하지 않는 파일입니다.");
        }
    }

    private Rank getRank(String fileRankText) {
        String rankText = fileRankText.substring(1, 2);
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
