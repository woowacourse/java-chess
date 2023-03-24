package controller.room;

import static controller.room.GameRoomCommand.CREATE;
import static controller.room.GameRoomCommand.EXIT;
import static controller.room.GameRoomCommand.JOIN;
import static controller.room.GameRoomCommand.LIST;

import java.util.EnumMap;
import java.util.List;

import controller.game.GameController;
import dto.GameInfoDto;
import repository.room.JdbcRoomDao;
import repository.connector.ProdConnector;
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
        if (inputs.size() != 2) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        gameRoomService.createGameRoom(inputs.get(1));
    }

    private void joinGame(List<String> inputs) {
        if (inputs.size() != 2) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        String gameName = inputs.get(1);
        GameInfoDto startGameInfo = gameRoomService.getGameInfo(gameName);
        GameInfoDto endGameInfo = gameController.gameStart(gameName, startGameInfo);
        gameRoomService.saveGameInfo(endGameInfo);
    }

    private void readGameRooms(List<String> inputs) {
        if (inputs.size() != 1) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        List<String> gameRooms = gameRoomService.readGameRooms();
        OutputView.printGameRooms(gameRooms);
    }

    private void exitGame(List<String> inputs) {
        if (inputs.size() != 1) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
