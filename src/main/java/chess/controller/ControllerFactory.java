package chess.controller;

import chess.controller.game.end.EndController;
import chess.controller.game.games.GamesController;
import chess.controller.game.move.MoveController;
import chess.controller.game.start.StartController;
import chess.controller.game.status.StatusController;
import chess.controller.main.ActionType;
import chess.controller.main.CommandMapper;
import chess.controller.main.MainController;
import chess.controller.room.create.CreateRoomController;
import chess.controller.room.join.JoinBoardController;
import chess.controller.user.LoginController;
import chess.service.ServiceFactory;
import chess.view.ViewFactory;
import java.util.Map;

public class ControllerFactory {

    private static final ControllerFactory instance = new ControllerFactory();

    private final StartController startController;
    private final EndController endController;
    private final MainController mainController;
    private final MoveController moveController;
    private final StatusController statusController;
    private final GamesController gamesController;
    private final CreateRoomController createRoomController;
    private final JoinBoardController joinBoardController;
    private final LoginController loginController;

    private ControllerFactory() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        startController = new StartController(serviceFactory.getStartChessGameService());
        endController = new EndController(serviceFactory.getEndChessGameService());
        ViewFactory viewFactory = ViewFactory.getInstance();
        moveController = new MoveController(serviceFactory.getMoveChessGameService(),
                serviceFactory.getLoadChessGameService(), viewFactory.getBoardOutput());
        statusController = new StatusController(viewFactory.getStatusOutput(),
                serviceFactory.getStatusChessGameService());
        gamesController = new GamesController(serviceFactory.getGamesService(), viewFactory.getGamesOutput());
        createRoomController = new CreateRoomController(serviceFactory.getCreateRoomService(),
                viewFactory.getCreateRoomOutput());
        joinBoardController = new JoinBoardController(viewFactory.getJoinBoard(), viewFactory.getJoinBoardOutput(),
                serviceFactory.getLoadChessGameService(), viewFactory.getBoardOutput());
        loginController = new LoginController(viewFactory.getLogin(), viewFactory.getLoginOutput(),
                serviceFactory.getLoginService());
        mainController = new MainController(createCommandMapper(), viewFactory.getErrorOutput(),
                viewFactory.getInputView(), viewFactory.getInitialOutput());
    }

    public static ControllerFactory getInstance() {
        return instance;
    }

    private CommandMapper createCommandMapper() {
        return new CommandMapper(Map.of(
                ActionType.START, startController,
                ActionType.END, endController,
                ActionType.MOVE, moveController,
                ActionType.STATUS, statusController,
                ActionType.GAMES, gamesController,
                ActionType.CREATE, createRoomController,
                ActionType.JOIN, joinBoardController,
                ActionType.LOGIN, loginController
        ));
    }

    public MainController getMainController() {
        return mainController;
    }
}
