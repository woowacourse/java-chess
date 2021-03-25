package chess.controller;

import static spark.Spark.get;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.MoveResultResponseDTO;
import chess.service.ChessService;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {
    private static final String ROOT = "/";
    private static final String START_COMMAND_INPUT = "start";
    private static final String MOVE_COMMAND_INPUT = "move";
    private static final String STATUS_COMMAND_INPUT = "status";
    private static final String END_COMMAND_INPUT = "end";

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        homeRequest();

        get(ROOT + START_COMMAND_INPUT, (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("requestCommand", ROOT + START_COMMAND_INPUT);
            return render(model, "index.html");
        });

        get(ROOT + MOVE_COMMAND_INPUT, (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("requestCommand", ROOT + MOVE_COMMAND_INPUT);
            return render(model, "index.html");
        });

        get(ROOT + STATUS_COMMAND_INPUT, (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("requestCommand", ROOT + STATUS_COMMAND_INPUT);
            return render(model, "index.html");
        });

        get(ROOT + END_COMMAND_INPUT, (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("requestCommand", ROOT + END_COMMAND_INPUT);
            return render(model, "index.html");
        });

//        OutputView.printGameStartMessage();
//        CommandRequestDTO commandRequestDTO = new CommandRequestDTO("start");
//        handleRequestCommandInputAndGetIsGameEnd(commandRequestDTO);
//        boolean isGameEnd = false;
//        while (!isGameEnd) {
//            CommandRequestDTO commandRequestDTO = new CommandRequestDTO("start");
//            ChessGameResponseDTO chessGameResponseDTO
//                = handleRequestCommandInputAndGetIsGameEnd(commandRequestDTO);
//            isGameEnd = chessGameResponseDTO.isGameEnd();
//        }
    }

    private void homeRequest() {
        get(ROOT, (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("requestCommand", ROOT);
            return render(model, "index.html");
        });
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
            OutputView.printWinnerTeamColor(moveResultResponseDTO.getWinnerTeamColorName());
            return chessGameResponseDTO;
        }
        return chessGameResponseDTO;
    }

    private ChessGameResponseDTO getChessGameResponseWhenRequestStatusCommand() {
        ChessGameResponseDTO chessGameResponseDTO = chessService.getScores();
        OutputView.printScores(chessGameResponseDTO.getScoresResponseDTO());
        return chessGameResponseDTO;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
