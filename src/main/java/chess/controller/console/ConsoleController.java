package chess.controller.console;

import chess.controller.console.dto.CommandRequestDTO;
import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.controller.web.MoveResponse;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleController {
    private static final String START_COMMAND_INPUT = "start";
    private static final String MOVE_COMMAND_INPUT = "move";
    private static final String STATUS_COMMAND_INPUT = "status";
    private static final String END_COMMAND_INPUT = "end";

    private final ChessService chessService;

    public ConsoleController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        OutputView.printGameStartMessage();
        boolean isGameEnd = false;
        while (!isGameEnd) {
            CommandRequestDTO commandRequestDTO = InputView.getCommandRequest();
            ResponseDTO responseDTO = request(commandRequestDTO);
            isGameEnd = responseDTO.getIsKingDead() || responseDTO.isEnd();
        }
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
        chessService.start();
        return printCurrentBoard();
    }

    private ResponseDTO printCurrentBoard() {
        ResponseDTO responseDTO = chessService.getCurrentBoard();
        OutputView.printBoard(responseDTO);
        return responseDTO;
    }

    private ResponseDTO end() {
        return new ResponseDTO(true);
    }

    private ResponseDTO move(CommandRequestDTO commandRequestDTO) {
        String startPositionInput = commandRequestDTO.getStartPositionInput();
        String destinationInput = commandRequestDTO.getDestinationInput();
        MoveRequestDTO moveRequestDTO = new MoveRequestDTO(startPositionInput, destinationInput);
        MoveResponse moveResponse = chessService.requestMove(moveRequestDTO);
        if (moveResponse.isMoveError()) {
            OutputView.printErrorMessage(moveResponse);
            return new ResponseDTO(false);
        }
        return printCurrentBoard();
    }

    private ResponseDTO status() {
        ResponseDTO responseDTO = chessService.getCurrentBoard();
        OutputView.printScores(responseDTO);
        return responseDTO;
    }
}
