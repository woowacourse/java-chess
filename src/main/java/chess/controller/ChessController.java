package chess.controller;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.MoveResultResponseDTO;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String START_COMMAND_INPUT = "start";
    private static final String MOVE_COMMAND_INPUT = "move";
    private static final String STATUS_COMMAND_INPUT = "status";
    private static final String END_COMMAND_INPUT = "end";

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        OutputView.printGameStartMessage();
        boolean isGameEnd = false;
        while (!isGameEnd) {
            CommandRequestDTO commandRequestDTO = InputView.getCommandRequest();
            ChessGameResponseDTO chessGameResponseDTO
                = handleRequestCommandInputAndGetIsGameEnd(commandRequestDTO);
            isGameEnd = chessGameResponseDTO.isGameEnd();
        }
    }

    private ChessGameResponseDTO handleRequestCommandInputAndGetIsGameEnd(
        CommandRequestDTO commandRequestDTO) {
        ChessGameResponseDTO chessGameResponseDTO
            = getChessGameResponseWhenRequestStartOrEnd(commandRequestDTO);
        if (chessGameResponseDTO != null) {
            return chessGameResponseDTO;
        }
        chessGameResponseDTO = getChessGameResponseWhenRequestMoveOrStatus(commandRequestDTO);
        if (chessGameResponseDTO != null) {
            return chessGameResponseDTO;
        }
        throw new IllegalArgumentException("유효하지 않은 명령어 입니다.");
    }

    private ChessGameResponseDTO getChessGameResponseWhenRequestStartOrEnd(
        CommandRequestDTO commandRequestDTO) {
        if (START_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return getChessGameResponseWhenRequestStartCommand();
        }
        if (END_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return getChessGameResponseWhenRequestEndCommand();
        }
        return null;
    }

    private ChessGameResponseDTO getChessGameResponseWhenRequestMoveOrStatus(
        CommandRequestDTO commandRequestDTO) {
        if (MOVE_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return getChessGameResponseWhenRequestMoveCommand(commandRequestDTO);
        }
        if (STATUS_COMMAND_INPUT.equals(commandRequestDTO.getCommandInput())) {
            return getChessGameResponseWhenRequestStatusCommand();
        }
        return null;
    }

    private ChessGameResponseDTO getChessGameResponseWhenRequestStartCommand() {
        ChessGameResponseDTO chessGameResponseDTO =
            chessService.startChessGameAndGetInitialBoardStatus();
        OutputView.printBoard(chessGameResponseDTO.getBoardStatusResponseDTO());
        return chessGameResponseDTO;
    }

    private ChessGameResponseDTO getChessGameResponseWhenRequestEndCommand() {
        boolean isGameEnd = true;
        return new ChessGameResponseDTO(isGameEnd);
    }

    private ChessGameResponseDTO getChessGameResponseWhenRequestMoveCommand(
        CommandRequestDTO commandRequestDTO) {
        ChessGameResponseDTO chessGameResponseDTO
            = chessService.movePieceAndGetResult(commandRequestDTO);
        MoveResultResponseDTO moveResultResponseDTO
            = chessGameResponseDTO.getMoveResultResponseDTO();
        OutputView.printBoard(moveResultResponseDTO.getBoardStatusResponseDTO());
        if (moveResultResponseDTO.isKingDead()) {
            OutputView.printWinnerTeamColor(moveResultResponseDTO.getWinnerTeamColor());
            return chessGameResponseDTO;
        }
        return chessGameResponseDTO;
    }

    private ChessGameResponseDTO getChessGameResponseWhenRequestStatusCommand() {
        ChessGameResponseDTO chessGameResponseDTO = chessService.getScores();
        OutputView.printScores(chessGameResponseDTO.getScoresResponseDTO());
        return chessGameResponseDTO;
    }
}
