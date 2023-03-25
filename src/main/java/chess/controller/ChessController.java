package chess.controller;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.infra.connection.ConnectionPoolImpl;
import chess.infra.connection.JdbcTemplate;
import chess.repository.ChessGameDao;
import chess.repository.MoveDao;
import chess.repository.UserDao;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.request.Request;
import chess.view.resposne.PieceResponse;
import chess.view.resposne.StatusResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    private static final String LOGIN = "login";
    private static final String GAMES = "games";
    private static final String CREATE = "create";
    private static final String JOIN = "join";
    private static final Filter filter = new Filter(
            List.of(START, END, MOVE, STATUS, GAMES, CREATE, JOIN),
            List.of(START, END, MOVE, STATUS)
    );
    private static final int USER_ID_INDEX = 1;
    private static final int JOIN_BOARDID_INDEX = 1;
    private final OutputView output;
    private final InputView input;
    private final ChessGameService chessGameService;
    private final Map<String, Action> actions = Map.of(
            START, new Action(this::start),
            END, new Action(this::finish),
            MOVE, new Action(this::move),
            STATUS, new Action(this::printStatus),
            GAMES, new Action(this::printGames),
            CREATE, new Action(this::createGame),
            JOIN, new Action(this::joinGame),
            LOGIN, new Action(this::login)
    );

    public ChessController(OutputView output, InputView input) {
        this.output = output;
        this.input = input;
        chessGameService = new ChessGameService(
                new ChessGameDao(new JdbcTemplate(ConnectionPoolImpl.getInstance())),
                new MoveDao(new JdbcTemplate(ConnectionPoolImpl.getInstance())),
                new UserDao(new JdbcTemplate(ConnectionPoolImpl.getInstance()))
        );
    }

    public void run() {
        output.printInitialMessage();
        while (true) {
            Request request = input.inputGameCommand();
            executeCommandAndHandleError(request);
        }
    }

    private void executeCommandAndHandleError(Request request) {
        try {
            filter.validateRequest(request);
            executeCommand(request);
        } catch (Exception e) {
            output.printError(e);
        }
    }

    private void executeCommand(Request request) {
        String command = request.getCommands().get(0);
        Action action = actions.get(command);
        if (action == null) {
            throw new IllegalArgumentException("잘못된 명령입니다.");
        }
        action.execute(request);
    }

    public void login(Request request) {
        String userId = request.getCommands().get(USER_ID_INDEX);
        chessGameService.login(userId);
        input.setUserId(userId);
        output.printLoginSuccess();
    }

    private void printGames(Request request) {
        output.printGames(chessGameService.getGames(request.getUserId()));
    }

    private void createGame(Request request) {
        int boardId = chessGameService.createGame(request.getUserId());
        output.printCreateGameSuccess();
        input.setBoardId(boardId);
    }

    private void joinGame(Request request) {
        int boardId = Integer.parseInt(request.getCommands().get(JOIN_BOARDID_INDEX));
        String boardStatus = chessGameService.joinGame(request.getUserId(), boardId);
        output.printJoinGameSuccess(boardStatus);
        input.setBoardId(boardId);
        printBoard(boardId);
    }

    public void move(Request request) {
        String origin = request.getCommands().get(1);
        String destination = request.getCommands().get(2);
        chessGameService.move(request.getBoardId(), origin, destination);
        printBoard(request.getBoardId());
    }

    public void start(Request request) {
        chessGameService.start(request.getBoardId());
        printBoard(request.getBoardId());
    }

    public void finish(Request request) {
        chessGameService.end(request.getBoardId());
    }

    public void printBoard(int boardId) {
        List<List<Piece>> boardResult = getBoardResult(boardId);
        output.printBoard(makeBoardResponse(boardResult));
    }

    private List<List<Piece>> getBoardResult(int boardId) {
        return chessGameService.getBoard(boardId);
    }

    private List<List<PieceResponse>> makeBoardResponse(List<List<Piece>> boardResult) {
        return boardResult.stream()
                .map(this::makeRankResponse)
                .collect(Collectors.toList());
    }

    private List<PieceResponse> makeRankResponse(List<Piece> row) {
        return row.stream()
                .map(PieceResponse::from)
                .collect(Collectors.toList());
    }

    private void printStatus(Request request) {
        Map<Color, Double> result = chessGameService.getStatus(request.getBoardId());
        output.printStatus(makeStatusResponse(result));
    }

    private StatusResponse makeStatusResponse(Map<Color, Double> query) {
        double whiteScore = query.get(Color.WHITE);
        double blackScore = query.get(Color.BLACK);
        return new StatusResponse(whiteScore, blackScore);
    }
}
