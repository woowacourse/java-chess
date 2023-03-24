package chess.view;

import chess.dto.CommandRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMAND_MOVE = "move";
    private static final String REQUEST_DELIMITER = " ";
    private static final int SINGLE_COMMAND_LENGTH = 1;
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;
    private static final int POSITION_FILE_INDEX = 0;
    private static final int POSITION_RANK_INDEX = 1;
    private static final String WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE = "이동 요청 정보가 잘못 되었습니다.";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static CommandRequest requestGameCommand() {
        String[] request = readRequest();
        if (request.length == SINGLE_COMMAND_LENGTH) {
            return CommandRequest.fromControlCommand(request[COMMAND_INDEX]);
        }
        validateMoveCommandRequest(request);
        return CommandRequest.fromMoveCommand(
                COMMAND_MOVE,
                parsePosition(request[SOURCE_POSITION_INDEX]),
                parsePosition(request[DESTINATION_POSITION_INDEX])
        );
    }

    private static String[] readRequest() {
        String line = scanner.nextLine();
        return line.split(REQUEST_DELIMITER);
    }

    private static void validateMoveCommandRequest(final String[] request) {
        if (request.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException(WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE);
        }
        if (!request[COMMAND_INDEX].equalsIgnoreCase(COMMAND_MOVE)) {
            throw new IllegalArgumentException(WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE);
        }
    }

    private static List<Integer> parsePosition(String word) {
        List<Integer> position = new ArrayList<>();
        position.add(File.findByUserIndex(String.valueOf(word.charAt(POSITION_FILE_INDEX))));
        position.add(Rank.findByUserIndex(String.valueOf(word.charAt(POSITION_RANK_INDEX))));
        return position;
    }

}
