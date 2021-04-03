package chess.controller.console;

import chess.controller.console.dto.CommandRequestDTO;
import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.service.ChessConsoleService;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;

public class ConsoleController {
    private static final String START_COMMAND_INPUT = "start";
    private static final String MOVE_COMMAND_INPUT = "move";
    private static final String STATUS_COMMAND_INPUT = "status";
    private static final String END_COMMAND_INPUT = "end";

    private final ChessConsoleService chessConsoleService;

    public ConsoleController(ChessConsoleService chessConsoleService) {
        this.chessConsoleService = chessConsoleService;
    }

    public void run() throws Exception {
        ConsoleOutputView.printGameStartMessage();
        boolean isGameEnd = false;
        while (!isGameEnd) {
            CommandRequestDTO commandRequestDTO = ConsoleInputView.getCommandRequest();
            ResponseDTO responseDTO = request(commandRequestDTO);
            isGameEnd = responseDTO.getIsKingDead() || responseDTO.isEnd();
        }
        chessConsoleService.endGame();
    }

    private ResponseDTO request(CommandRequestDTO commandRequestDTO) {
        ResponseDTO responseDTO = startOrEnd(commandRequestDTO);
        if (responseDTO != null) {
            return responseDTO;
        }
        responseDTO = moveOrStatus(commandRequestDTO);
        if (responseDTO != null) {
            return responseDTO;
        }
        throw new IllegalArgumentException("유효하지 않은 명령어 입니다.");
    }

    private ResponseDTO startOrEnd(CommandRequestDTO commandRequestDTO) {
        if (START_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return start();
        }
        if (END_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return end();
        }
        return null;
    }

    private ResponseDTO moveOrStatus(CommandRequestDTO commandRequestDTO) {
        if (MOVE_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return move(commandRequestDTO);
        }
        if (STATUS_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return status();
        }
        return null;
    }

    private ResponseDTO start() {
        try {
            chessConsoleService.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return printCurrentBoard();
    }

    private ResponseDTO printCurrentBoard() {
        ResponseDTO responseDTO;
        try {
            responseDTO = chessConsoleService.getCurrentBoard();
            ConsoleOutputView.printBoard(responseDTO);
        } catch (Exception e) {
            return new ResponseDTO(false);
        }
        return responseDTO;
    }

    private ResponseDTO end() {
        try {
            chessConsoleService.endGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseDTO(true);
    }

    private ResponseDTO move(CommandRequestDTO commandRequestDTO) {
        String startPositionInput = commandRequestDTO.getStartPositionInput();
        String destinationInput = commandRequestDTO.getDestinationInput();
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO(startPositionInput, destinationInput);
        try {
            chessConsoleService.requestMove(moveRequestDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return printCurrentBoard();
    }

    private ResponseDTO status() {
        ResponseDTO responseDTO;
        try {
            responseDTO = chessConsoleService.getCurrentBoard();
            ConsoleOutputView.printScores(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseDTO(false);
    }
}
