package chess.controller;

import java.util.Map;
import java.util.function.BiFunction;

import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.Arguments;
import chess.dto.GameRequest;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    public ChessController() {
    }

    public void run() {
        OUTPUT_VIEW.printIntroduction();
        playGame();
    }

    private void playGame() {
        GameState state = new Ready();
        while (state.isRunnable()) {
            state = tryExecute(state);
        }
    }

    private GameState tryExecute(GameState state) {
        try {
            return execute(state);
        } catch (RuntimeException e) {
            OUTPUT_VIEW.printException(e);
            return tryExecute(state);
        }
    }

    private GameState execute(GameState state) {
        String input = INPUT_VIEW.inputCommand();
        GameRequest gameRequest = GameRequest.of(input);
        return Mapper.findAndExecute(state, gameRequest);
    }

    private static class Mapper {
        private static final Map<Command, BiFunction<GameState, Arguments, GameState>> MAPPER =
            Map.of(Command.START, Mapper::start,
                Command.FINISH, Mapper::end,
                Command.MOVE, Mapper::move,
                Command.STATUS, Mapper::status);

        public static GameState findAndExecute(GameState state, GameRequest gameRequest) {
            BiFunction<GameState, Arguments, GameState> executor = findExecutor(gameRequest.getCommand());
            return executor.apply(state, gameRequest.getArguments());
        }

        private static BiFunction<GameState, Arguments, GameState> findExecutor(Command command) {
            if (!MAPPER.containsKey(command)) {
                throw new IllegalArgumentException("[ERROR] 해당하는 명령어가 없습니다.");
            }
            return MAPPER.get(command);
        }

        private static GameState start(GameState state, Arguments ignored) {
            state = state.start();
            OUTPUT_VIEW.printBoard(state.getPointPieces(), state.getColor());
            return state;
        }

        private static GameState end(GameState state, Arguments ignored) {
            state = state.finish();
            return state;
        }

        private static GameState move(GameState state, Arguments arguments) {
            state = state.move(arguments);
            OUTPUT_VIEW.printBoard(state.getPointPieces(), state.getColor());
            return state;
        }

        private static GameState status(GameState state, Arguments ignored) {
            OUTPUT_VIEW.printScore(state.getColorScore());
            return state;
        }
    }
}
