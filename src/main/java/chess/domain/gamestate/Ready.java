package chess.domain.gamestate;

import chess.domain.game.ChessGame;
import chess.domain.game.Result;

import java.util.List;

public class Ready implements State {
    public static final String NOT_START_OR_END_ERROR = "start나 end만 입력 가능합니다.";

    @Override
    public void start(List<String> input, ChessGame chessGame) {
        if (Option.isStart(input)) {
            chessGame.initBoard();
            chessGame.changeState(new Running());
            return;
        }
        if (Option.isEnd(input)) {
            chessGame.changeState(new End());
            return;
        }
        throw new IllegalArgumentException(NOT_START_OR_END_ERROR);
    }

    @Override
    public void play(List<String> input, ChessGame chessGame) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Result result(ChessGame chessGame) {
        return null;
    }
}
