package controller.room;

import static controller.room.GameRoomCommand.CREATE;
import static controller.room.GameRoomCommand.EXIT;
import static controller.room.GameRoomCommand.JOIN;
import static controller.room.GameRoomCommand.LIST;
import static controller.room.GameRoomCommand.SPECIAL_ROOM_COMMAND_LENGTH;
import static controller.room.GameRoomCommand.STANDARD_ROOM_COMMAND_LENGTH;
import static controller.room.GameRoomCommand.validateCommandLength;

import java.util.EnumMap;
import java.util.List;

import controller.game.GameController;
import repository.connector.ProdConnector;
import repository.room.JdbcRoomDao;
import service.ChessService;
import service.GameRoomService;
import view.InputView;
import view.OutputView;

public class RoomController {
    private final GameController gameController = new GameController(new ChessService());
    private final GameRoomService gameRoomService = new GameRoomService(new JdbcRoomDao(new ProdConnector()));
    private final EnumMap<GameRoomCommand, GameRoomAction> gameRoomActions = new EnumMap<>(GameRoomCommand.class);

    public RoomController() {
        gameRoomActions.put(CREATE, this::createGameRoom);
        gameRoomActions.put(LIST, this::readGameRooms);
        gameRoomActions.put(EXIT, this::exitGame);
        gameRoomActions.put(JOIN, this::joinGame);
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
        gameController.gameStart(roomId);
    }

    private void readGameRooms(List<String> inputs) {
        validateCommandLength(inputs.size(), STANDARD_ROOM_COMMAND_LENGTH);
        List<String> gameRooms = gameRoomService.readGameRooms();
        OutputView.printGameRooms(gameRooms);
    }

    private void exitGame(List<String> inputs) {
        validateCommandLength(inputs.size(), STANDARD_ROOM_COMMAND_LENGTH);
    }
}
