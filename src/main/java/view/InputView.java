package view;

import view.dto.GameProceedRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final String NOT_EXIST = "";
    private static final int GAME_COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int POSITION_LENGTH = 2;

    private InputView() {
    }

    public static GameCommand inputInitialGameCommand() {
        GameCommand gameCommand = GameCommand.from(readLine());
        if (gameCommand == GameCommand.MOVE) {
            throw new IllegalArgumentException("게임을 시작(start)해야 이동 명령을 입력할 수 있습니다.");
        }
        return gameCommand;
    }

    public static GameProceedRequest inputGameProceedCommand() {
        List<String> inputMovement = Arrays.asList(readLine().split(" "));
        GameCommand gameCommand = GameCommand.from(inputMovement.get(GAME_COMMAND_INDEX));
        if (gameCommand == GameCommand.MOVE) {
            validateInputSize(inputMovement);
            validateInputPositionSize(inputMovement);
            return new GameProceedRequest(gameCommand, inputMovement.get(SOURCE_POSITION_INDEX), inputMovement.get(TARGET_POSITION_INDEX));
        }
        return new GameProceedRequest(gameCommand, NOT_EXIST, NOT_EXIST);
    }

    private static void validateInputSize(List<String> inputMovement) {
        if (inputMovement.size() == 3) {
            throw new IllegalArgumentException("source위치 target위치를 입력해 주세요. - 예. move b2 b3");
        }
    }

    private static void validateInputPositionSize(List<String> inputMovement) {
        String sourcePosition = inputMovement.get(SOURCE_POSITION_INDEX);
        String targetPosition = inputMovement.get(TARGET_POSITION_INDEX);
        if(sourcePosition.length() != POSITION_LENGTH || targetPosition.length() != POSITION_LENGTH){
            throw new IllegalArgumentException("source위치와 target위치가 올바르지 않습니다.");
        }
    }

    private static String readLine() {
        try {
            return READER.readLine();
        } catch (final IOException exception) {
            System.out.println(exception.getMessage());
            return readLine();
        }
    }
}
