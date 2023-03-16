package chess.view;

import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.dto.CommandRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final int SINGLE_COMMAND_LENGTH = 1;
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;
    private static final int POSITION_RANK_INDEX = 0;
    private static final int POSITION_FILE_INDEX = 1;
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public CommandRequest requestGameCommand() {
        String line = scanner.nextLine();
        String[] request = line.split(" ");
        if (request.length == SINGLE_COMMAND_LENGTH) {
            return CommandRequest.fromControlCommand(Command.findByAnswer(request[COMMAND_INDEX]));
        }
        return CommandRequest.fromMoveCommand(parsePosition(request[SOURCE_POSITION_INDEX]),
                parsePosition(request[DESTINATION_POSITION_INDEX]));
    }

    private List<Integer> parsePosition(String word) {
        List<Integer> position = new ArrayList<>();
        position.add(Rank.findByIndex(String.valueOf(word.charAt(POSITION_RANK_INDEX))));
        position.add(File.findByIndex(String.valueOf(word.charAt(POSITION_FILE_INDEX))));
        return position;
    }

}
