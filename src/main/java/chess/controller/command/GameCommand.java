package chess.controller.command;

import chess.controller.action.GameAction;
import chess.domain.game.ChessGame;
import chess.domain.game.GameResult;
import chess.domain.position.Position;
import chess.dto.GameResultDto;
import chess.service.ChessService;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;

public enum GameCommand {

    START("start", (chessService, chessGame, ignored, outputView) -> start(chessService, chessGame, outputView)),
    END("end", (chessService, chessGame, ignore, ignored) -> end(chessService, chessGame)),
    MOVE("move", GameCommand::move),
    STATUS("status", (chessService, chessGame, ignored, outputView) -> status(chessService, chessGame, outputView));

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final String symbol;
    private final GameAction gameAction;

    GameCommand(final String symbol, final GameAction gameAction) {
        this.symbol = symbol;
        this.gameAction = gameAction;
    }

    public static GameCommand from(final String symbol) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }

    private static void start(final ChessService chessService, final ChessGame chessGame, final OutputView outputView) {
        chessService.startGame(chessGame);
        outputView.printBoard(chessService.getBoard(chessGame));
    }

    private static void end(final ChessService chessService, final ChessGame chessGame) {
        chessService.endGame(chessGame);
    }

    private static void move(final ChessService chessService, final ChessGame chessGame, final List<String> command, final OutputView outputView) {
        if (command.size() != 3) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }

        final Position source = Position.from(command.get(SOURCE_INDEX));
        final Position target = Position.from(command.get(TARGET_INDEX));

        chessService.move(chessGame, source, target);
        outputView.printBoard(chessService.getBoard(chessGame));
    }

    private static void status(final ChessService chessService, final ChessGame chessGame, final OutputView outputView) {
        final GameResult gameResult = chessService.getGameResult(chessGame);

        outputView.printBoard(chessService.getBoard(chessGame));
        outputView.printStatus(GameResultDto.from(gameResult));
    }

    public void execute(final ChessService chessService, final ChessGame chessGame, final List<String> gameCommand, final OutputView outputView) {
        this.gameAction.execute(chessService, chessGame, gameCommand, outputView);
    }
}
