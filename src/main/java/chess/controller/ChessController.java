package chess.controller;

import chess.dao.ChessGameJdbcDao;
import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.ChessRequest;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGameService chessGameService;

    private static final Map<GameCommand, Function<List<String>, Controller>> commands = new EnumMap<>(
            GameCommand.class);

    static {
        commands.put(GameCommand.START, ignored -> new StartController());
        commands.put(GameCommand.END, ignored -> new EndController());
        commands.put(GameCommand.MOVE, MoveController::new);
        commands.put(GameCommand.STATUS, ignored -> new StatusController());
        commands.put(GameCommand.CLEAR, ignored -> new ClearController());
    }

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.chessGameService = new ChessGameService(new ChessGame(), new ChessGameJdbcDao());
    }

    public void run() {
        outputView.printStart();
        GameCommand gameCommand = GameCommand.EMPTY;
        while (gameCommand != GameCommand.END) {
            gameCommand = play();
            gameCommand = checkGameEnd(chessGameService, gameCommand);
        }
    }

    private GameCommand play() {
        try {
            ChessRequest chessRequest = inputView.readGameCommand();
            GameCommand gameCommand = chessRequest.getCommand();
            Controller controller = commands.get(gameCommand).apply(chessRequest.getParameter());
            controller.execute(chessGameService, outputView);
            return gameCommand;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return play();
        }
    }

    //왕이 죽었나 체크하는 부분입니다. 왕이 죽으면 게임을 끝내고, 출력을 하고 싶은데 마땅한 방법을 찾기가 힘드네요..
    //그래서 컨트롤러에 매번 체크하는 방식으로 만들었습니다..
    private GameCommand checkGameEnd(ChessGameService service, GameCommand gameCommand) {
        if (service.isGameEnd()) {
            outputView.printStatus(service.getGameResult());
            outputView.printWinner(service.getGameResult());
            outputView.printEnd();
            return GameCommand.END;
        }
        return gameCommand;
    }
}
