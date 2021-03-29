package chess.controller.console;

import chess.controller.console.dto.CommandRequestDTO;
import chess.controller.dto.request.MoveRequestDTOForDB;
import chess.controller.dto.response.ResponseDTOForDB;
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

    public void run() throws Exception {
        OutputView.printGameStartMessage();
        boolean isGameEnd = false;
        while (!isGameEnd) {
            CommandRequestDTO commandRequestDTO = InputView.getCommandRequest();
            ResponseDTOForDB responseDTO = request(commandRequestDTO);
            isGameEnd = responseDTO.getIsKingDead() || responseDTO.isEnd();
        }
        chessService.endGame();
    }

    private ResponseDTOForDB request(CommandRequestDTO commandRequestDTO) {
        ResponseDTOForDB responseDTO = startOrEnd(commandRequestDTO);
        if (responseDTO != null) {
            return responseDTO;
        }
        responseDTO = moveOrStatus(commandRequestDTO);
        if (responseDTO != null) {
            return responseDTO;
        }
        throw new IllegalArgumentException("유효하지 않은 명령어 입니다.");
    }

    private ResponseDTOForDB startOrEnd(CommandRequestDTO commandRequestDTO) {
        if (START_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return start();
        }
        if (END_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return end();
        }
        return null;
    }

    private ResponseDTOForDB moveOrStatus(CommandRequestDTO commandRequestDTO) {
        if (MOVE_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return move(commandRequestDTO);
        }
        if (STATUS_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return status();
        }
        return null;
    }

    private ResponseDTOForDB start() {
        try {
            chessService.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return printCurrentBoard();
    }

    private ResponseDTOForDB printCurrentBoard() {
        ResponseDTOForDB responseDTO;
        try {
            responseDTO = chessService.getCurrentBoard();
            OutputView.printBoard(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTOForDB(false);
        }
        return responseDTO;
    }

    private ResponseDTOForDB end() {
        try {
            chessService.endGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseDTOForDB(true);
    }

    private ResponseDTOForDB move(CommandRequestDTO commandRequestDTO) {
        String startPositionInput = commandRequestDTO.getStartPositionInput();
        String destinationInput = commandRequestDTO.getDestinationInput();
        MoveRequestDTOForDB moveRequestDTO
            = new MoveRequestDTOForDB(startPositionInput, destinationInput);
        try {
            chessService.requestMove(moveRequestDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return printCurrentBoard();
    }

    private ResponseDTOForDB status() {
        ResponseDTOForDB responseDTO;
        try {
            responseDTO = chessService.getCurrentBoard();
            OutputView.printScores(responseDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseDTOForDB(false);
    }
}
