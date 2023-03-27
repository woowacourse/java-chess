package controller;

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
import static controller.room.GameRoomCommand.CREATE;
import static controller.room.GameRoomCommand.EXIT;
import static controller.room.GameRoomCommand.JOIN;
import static controller.room.GameRoomCommand.LIST;
import static controller.room.GameRoomCommand.SPECIAL_ROOM_COMMAND_LENGTH;
import static controller.room.GameRoomCommand.STANDARD_ROOM_COMMAND_LENGTH;
import static controller.room.GameRoomCommand.validateCommandLength;

import java.util.EnumMap;
import java.util.List;

import controller.game.Action;
import controller.game.Command;
import controller.room.GameRoomAction;
import controller.room.GameRoomCommand;
import dto.GameInfoDto;
import dto.ScoreDto;
import service.ChessService;
import service.GameRoomService;
import view.InputView;
import view.OutputView;

public class Controller {
    private final GameRoomService gameRoomService;
    private final ChessService chessService;
    private final EnumMap<GameRoomCommand, GameRoomAction> gameRoomActions = new EnumMap<>(GameRoomCommand.class);
    private final EnumMap<Command, Action> actions = new EnumMap<>(Command.class);

    public Controller(GameRoomService gameRoomService, ChessService chessService) {
        this.gameRoomService = gameRoomService;
        this.chessService = chessService;
        gameRoomActions.put(CREATE, this::createGameRoom);
        gameRoomActions.put(LIST, this::readGameRooms);
        gameRoomActions.put(EXIT, this::exitGame);
        gameRoomActions.put(JOIN, this::joinGame);
        actions.put(START, this::start);
        actions.put(MOVE, this::move);
        actions.put(END, this::end);
        actions.put(STATUS, this::status);
        actions.put(CANCEL, this::cancelMove);
    }

    public void run() {
        GameRoomCommand gameRoomCommand = GameRoomCommand.NONE;
        while (gameRoomCommand != EXIT) {
            gameRoomCommand = runByGameRooAction();
        }
    }

    private GameRoomCommand runByGameRooAction() {
        try {
            GameRoomCommand gameRoomCommand;
            OutputView.printGameRoomInfo();
            List<String> inputs = InputView.requestCommand();
            gameRoomCommand = GameRoomCommand.find(inputs.get(0));
            GameRoomAction gameRoomAction = gameRoomActions.get(gameRoomCommand);
            gameRoomAction.execute(inputs);
            return gameRoomCommand;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return GameRoomCommand.NONE;
        }
    }

    private void createGameRoom(List<String> inputs) {
        validateCommandLength(inputs.size(), SPECIAL_ROOM_COMMAND_LENGTH);
        gameRoomService.createGameRoom(inputs.get(1));
    }

    private void joinGame(List<String> inputs) {
        validateCommandLength(inputs.size(), SPECIAL_ROOM_COMMAND_LENGTH);
        String gameName = inputs.get(1);
        long roomId = gameRoomService.findRoomIdByRoomName(gameName);
        gameStart(roomId);
    }

    private void readGameRooms(List<String> inputs) {
        validateCommandLength(inputs.size(), STANDARD_ROOM_COMMAND_LENGTH);
        List<String> gameRooms = gameRoomService.readGameRooms();
        OutputView.printGameRooms(gameRooms);
    }

    private void exitGame(List<String> inputs) {
        validateCommandLength(inputs.size(), STANDARD_ROOM_COMMAND_LENGTH);
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
