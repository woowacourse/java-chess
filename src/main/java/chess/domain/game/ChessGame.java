package chess.domain.game;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Coordinate;
import java.util.Map;

public final class ChessGame {

    private static final int COORDINATE_FROM_INDEX = 0;
    private static final int COORDINATE_TO_INDEX = 1;

    private final Map<CommandType, GameAction> commandActions = Map.of(
            CommandType.START, this::start,
            CommandType.END, this::end,
            CommandType.MOVE, this::move
    );

    private ChessBoard chessBoard;
    private GameState gameState = GameState.READY;

    public void execute(final Command command) {
        commandActions.get(command.getType()).execute(command);
    }

    private void start(final Command command) {
        if (gameState != GameState.READY) {
            throw new IllegalArgumentException("이미 게임이 시작하였습니다.");
        }
        this.chessBoard = new ChessBoard();
        gameState = GameState.RUNNING;
    }

    private void move(final Command command) {
        if (gameState != GameState.RUNNING) {
            throw new IllegalArgumentException("게임을 먼저 시작해 주세요.");
        }
        final Coordinate from = command.getCoordinate().get(COORDINATE_FROM_INDEX);
        final Coordinate to = command.getCoordinate().get(COORDINATE_TO_INDEX);
        chessBoard.move(from, to);
    }

    private void end(final Command command) {
        gameState = GameState.END;
    }

    public boolean isGameEnd() {
        return gameState == GameState.END;
    }

    public ChessBoard getChessBoard() {
        return this.chessBoard;
    }
}
