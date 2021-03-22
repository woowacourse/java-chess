package chess.view;

import chess.controller.dto.request.CommandRequestDTO;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMAND_DELIMITER = " ";
    private static final String MOVE_COMMAND = "move";
    private static final String START_COMMAND = "start";
    private static final String STATUS_COMMAND = "status";
    private static final String END_COMMAND = "end";
    private static final int COMMAND_INDEX = 0;
    private static final int START_POSITION_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int MOVE_COMMAND_FACTORS_SIZE = 3;
    private static final int COMMAND_FACTORS_SIZE_EXCEPT_MOVE = 1;

    private InputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static CommandRequestDTO getCommandRequest() {
        String commandLineInput = SCANNER.nextLine();
        String[] splitCommandLineInput = commandLineInput.split(COMMAND_DELIMITER);
        validateCommandLineFormat(splitCommandLineInput);
        return parseCommandLineInput(splitCommandLineInput);
    }

    private static void validateCommandLineFormat(String[] splitCommandLineInput) {
        if (splitCommandLineInput[COMMAND_INDEX].equals(MOVE_COMMAND)) {
            validateMoveCommandLineFormat(splitCommandLineInput);
            return;
        }
        validateCommandLineFormatExceptMove(splitCommandLineInput);
    }

    private static void validateMoveCommandLineFormat(String[] splitCommandLineInput) {
        if (splitCommandLineInput.length != MOVE_COMMAND_FACTORS_SIZE) {
            throw new IllegalArgumentException("명령어를 잘못 입력했습니다.");
        }
    }

    private static void validateCommandLineFormatExceptMove(String[] splitCommandLineInput) {
        if (splitCommandLineInput.length != COMMAND_FACTORS_SIZE_EXCEPT_MOVE) {
            throw new IllegalArgumentException("명령어를 잘못 입력했습니다.");
        }
    }

    private static CommandRequestDTO parseCommandLineInput(String[] splitCommandLineInput) {
        String commandInput = splitCommandLineInput[COMMAND_INDEX];
        if (commandInput.equals(MOVE_COMMAND)) {
            return parseMoveCommandLineInput(splitCommandLineInput);
        }
        if (commandInput.equals(START_COMMAND) || commandInput.equals(STATUS_COMMAND)
            || commandInput.equals(END_COMMAND)) {
            return parseCommandLineInputExceptMove(splitCommandLineInput);
        }
        throw new IllegalArgumentException("명령어를 잘못 입력했습니다.");
    }

    private static CommandRequestDTO parseMoveCommandLineInput(String[] splitCommandLineInput) {
        String commandInput = splitCommandLineInput[COMMAND_INDEX];
        String startPositionInput = splitCommandLineInput[START_POSITION_INDEX];
        String destinationInput = splitCommandLineInput[DESTINATION_INDEX];
        return new CommandRequestDTO(commandInput, startPositionInput, destinationInput);
    }

    private static CommandRequestDTO parseCommandLineInputExceptMove(
        String[] splitCommandLineInput) {

        String commandInput = splitCommandLineInput[COMMAND_INDEX];
        return new CommandRequestDTO(commandInput);
    }
}
