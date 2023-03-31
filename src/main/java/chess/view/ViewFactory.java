package chess.view;

import chess.controller.ErrorOutput;
import chess.controller.game.BoardOutput;
import chess.controller.game.games.GamesOutput;
import chess.controller.game.status.StatusOutput;
import chess.controller.main.InitialOutput;
import chess.controller.room.create.CreateRoomOutput;
import chess.controller.room.join.JoinBoard;
import chess.controller.room.join.JoinBoardOutput;
import chess.controller.user.Login;
import chess.controller.user.LoginOutput;
import chess.view.resposne.BoardOutputView;
import chess.view.resposne.CreateRoomOutputView;
import chess.view.resposne.ErrorOutputView;
import chess.view.resposne.GamesOutputView;
import chess.view.resposne.InitialOutputView;
import chess.view.resposne.JoinBoardOutputView;
import chess.view.resposne.LoginOutputView;
import chess.view.resposne.StatusOutputView;

public class ViewFactory {

    private static final ViewFactory instance = new ViewFactory();
    
    private final LoginImpl login;
    private final JoinBoardImpl joinBoard;
    private final InputView inputView;
    private final BoardOutput boardOutput;
    private final CreateRoomOutput createRoomOutput;
    private final ErrorOutput errorOutput;
    private final GamesOutput gamesOutput;
    private final InitialOutput initialOutput;
    private final JoinBoardOutput joinBoardOutput;
    private final LoginOutput loginOutput;
    private final StatusOutput statusOutput;

    private ViewFactory() {
        login = new LoginImpl();
        joinBoard = new JoinBoardImpl();
        inputView = new InputView(login, joinBoard);
        boardOutput = new BoardOutputView();
        createRoomOutput = new CreateRoomOutputView();
        errorOutput = new ErrorOutputView();
        gamesOutput = new GamesOutputView();
        initialOutput = new InitialOutputView();
        joinBoardOutput = new JoinBoardOutputView();
        loginOutput = new LoginOutputView();
        statusOutput = new StatusOutputView();
    }

    public static ViewFactory getInstance() {
        return instance;
    }

    public BoardOutput getBoardOutput() {
        return boardOutput;
    }

    public CreateRoomOutput getCreateRoomOutput() {
        return createRoomOutput;
    }

    public ErrorOutput getErrorOutput() {
        return errorOutput;
    }

    public GamesOutput getGamesOutput() {
        return gamesOutput;
    }

    public InitialOutput getInitialOutput() {
        return initialOutput;
    }

    public JoinBoardOutput getJoinBoardOutput() {
        return joinBoardOutput;
    }

    public LoginOutput getLoginOutput() {
        return loginOutput;
    }

    public StatusOutput getStatusOutput() {
        return statusOutput;
    }

    public InputView getInputView() {
        return inputView;
    }

    public Login getLogin() {
        return login;
    }

    public JoinBoard getJoinBoard() {
        return joinBoard;
    }
}
