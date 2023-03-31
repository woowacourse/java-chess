package chess.view;

import chess.dto.CommandRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMAND_MOVE = "move";
    private static final String REQUEST_DELIMITER = " ";
    private static final int SINGLE_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;
    private static final int POSITION_WORD_SIZE = 2;
    private static final int POSITION_FILE_INDEX = 0;
    private static final int POSITION_RANK_INDEX = 1;
    private static final String WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE = "이동 요청 정보가 잘못 되었습니다.";
    private static final String BOARD_ID_NOT_INTEGER_ERROR_MESSAGE = "방 번호는 정수여야 합니다.";
    private static final String REQUEST_BOARD_ID_MESSAGE = "> 입장할 방 번호를 선택하세요. 새로운 번호 입력 시 새 게임 방이 개설됩니다.";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static CommandRequest requestGameCommand() {
        List<String> request = readRequest();
        if (isSingleCommand(request) && !request.contains(COMMAND_MOVE)) {
            return CommandRequest.fromControlCommand(request.get(COMMAND_INDEX));
        }
        validateMoveCommandRequest(request);
        return CommandRequest.fromMoveCommand(
                COMMAND_MOVE,
                parsePosition(request.get(SOURCE_POSITION_INDEX)),
                parsePosition(request.get(DESTINATION_POSITION_INDEX))
        );
    }

    private static List<String> readRequest() {
        String line = scanner.nextLine();
        return new ArrayList<>(List.of(line.split(REQUEST_DELIMITER)));
    }

    private static boolean isSingleCommand(List<String> request) {
        return request.size() == SINGLE_COMMAND_SIZE;
    }

    private static void validateMoveCommandRequest(final List<String> request) {
        if (request.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException(WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE);
        }
        if (!request.get(COMMAND_INDEX).equalsIgnoreCase(COMMAND_MOVE)) {
            throw new IllegalArgumentException(WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE);
        }
    }

    private static List<Integer> parsePosition(String word) {
        if (word.length() != POSITION_WORD_SIZE) {
            throw new IllegalArgumentException(WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE);
        }
        List<Integer> position = new ArrayList<>();
        position.add(File.findByUserIndex(String.valueOf(word.charAt(POSITION_FILE_INDEX))));
        position.add(Rank.findByUserIndex(String.valueOf(word.charAt(POSITION_RANK_INDEX))));
        return position;
    }

    public static int requestBoardId() {
        System.out.println(REQUEST_BOARD_ID_MESSAGE);
        String line = scanner.nextLine();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(BOARD_ID_NOT_INTEGER_ERROR_MESSAGE);
        }
    }

}
