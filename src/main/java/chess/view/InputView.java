package chess.view;

import chess.dto.CommandRequest;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String REQUEST_DELIMITER = " ";
    private static final int SINGLE_COMMAND_LENGTH = 1;
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;
    private static final String START_OPTION_REQUEST = "새 게임을 시작하려면 %d, 이어서 진행하려면 [%s] 중 하나를 선택해주세요.";
    private static final String WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE = "이동 요청 정보가 잘못 되었습니다.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public CommandRequest requestGameCommand() {
        String[] request = readRequest();
        if (request.length == SINGLE_COMMAND_LENGTH) {
            return CommandRequest.fromControlCommand(Command.findByAnswer(request[COMMAND_INDEX]));
        }
        validateMoveCommandRequest(request);
        return CommandRequest.fromMoveCommand(request[SOURCE_POSITION_INDEX],
            request[DESTINATION_POSITION_INDEX]);
    }

    private String[] readRequest() {
        String line = scanner.nextLine();
        return line.split(REQUEST_DELIMITER);
    }

    private void validateMoveCommandRequest(final String[] request) {
        if (request.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException(WRONG_MOVE_COMMAND_REQUEST_ERROR_MESSAGE);
        }
    }

    public long readStartOption(final long newStartId, final List<Long> gameIds) {
        String options = gameIds.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));
        String optionRequest = String.format(START_OPTION_REQUEST, newStartId, options);
        System.out.println(optionRequest);
        return repeatReadOptionInIds(gameIds, newStartId);
    }

    private long repeatReadOptionInIds(final List<Long> gameIds, final long newStartId) {
        Long requestedOption = null;
        while (requestedOption == null) {
            requestedOption = readOptionInId(gameIds, newStartId);
        }
        return requestedOption;
    }

    private Long readOptionInId(final List<Long> gameIds, final long newStartId) {
        try {
            return readValueInRequests(gameIds, newStartId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private long readValueInRequests(final List<Long> gameIds, final long newStartId) {
        long option = readOnlyNumber();
        if (gameIds.contains(option) || option == newStartId) {
            return option;
        }
        throw new IllegalArgumentException("제공된 번호만 입력 가능합니다.");
    }

    private int readOnlyNumber() {
        String line = scanner.nextLine();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

}
