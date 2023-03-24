package controller.game;


import static controller.game.Command.CANCEL;
import static controller.game.Command.COMMAND_INDEX;
import static controller.game.Command.CURRENT_SQUARE_INDEX;
import static controller.game.Command.EMPTY;
import static controller.game.Command.END;
import static controller.game.Command.MOVE;
import static controller.game.Command.MOVE_COMMAND_LENGTH;
import static controller.game.Command.STANDARD_COMMAND_LENGTH;
import static controller.game.Command.START;
import static controller.game.Command.STATUS;
import static controller.game.Command.TARGET_SQUARE_INDEX;

import java.util.EnumMap;
import java.util.List;

import dto.GameInfoDto;
import dto.ScoreDto;
import service.ChessService;
import view.InputView;
import view.OutputView;

public class GameController {
    private final ChessService chessService;
    private final EnumMap<Command, Action> actions = new EnumMap<>(Command.class);

    public GameController(ChessService chessService) {
        this.chessService = chessService;
        actions.put(START, this::start);
        actions.put(MOVE, this::move);
        actions.put(END, this::end);
        actions.put(STATUS, this::status);
        actions.put(CANCEL, this::cancelMove);
    }



    public void gameStart(long roomId) {
        GameInfoDto gameInfo = chessService.getGameInfo(roomId);
        OutputView.printIsSavedGame(gameInfo.getBoardDtos().size());
        chessService.setUp(gameInfo);
        OutputView.printChessBoard(chessService.getChessBoard());
        OutputView.printChessInfo();
        Command command = START;
        while (command != END) {
            command = play(roomId);
        }
        chessService.end(roomId);
    }

    private Command play(long roomId) {
        try {
            List<String> inputs = InputView.requestCommand();
            Command command = Command.find(inputs.get(COMMAND_INDEX));
            Action action = actions.get(command);
            action.execute(roomId, inputs);
            return refreshCommandByResult(command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e);
            return EMPTY;
        }
    }

    private Command refreshCommandByResult(Command command) {
        if (chessService.isFinished()) {
            return END;
        }
        return command;
    }

    private void start(long ignored, List<String> inputs) {
        Command.validateCommandLength(inputs.size(), STANDARD_COMMAND_LENGTH);
        OutputView.printChessBoard(chessService.getChessBoard());
    }

    private void move(long roomId, List<String> inputs) {
        Command.validateCommandLength(inputs.size(), MOVE_COMMAND_LENGTH);
        String currentSquareInput = inputs.get(CURRENT_SQUARE_INDEX);
        String targetSquareInput = inputs.get(TARGET_SQUARE_INDEX);
        chessService.move(roomId, currentSquareInput, targetSquareInput);
        OutputView.printChessBoard(chessService.getChessBoard());
    }

    private void status(long ignored, List<String> inputs) {
        Command.validateCommandLength(inputs.size(), STANDARD_COMMAND_LENGTH);
        List<ScoreDto> scoreDto = chessService.calculateFinalScore();
        OutputView.printScores(scoreDto);
    }

    private void end(long ignored, List<String> inputs) {
        Command.validateCommandLength(inputs.size(), STANDARD_COMMAND_LENGTH);
    }

    private void cancelMove(long gameId, List<String> inputs) {
        Command.validateCommandLength(inputs.size(), STANDARD_COMMAND_LENGTH);
        chessService.cancelMove(gameId);
        OutputView.printChessBoard(chessService.getChessBoard());
    }
}
