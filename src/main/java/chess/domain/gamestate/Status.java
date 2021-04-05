package chess.domain.gamestate;

import chess.domain.game.ChessGame;
import chess.domain.game.Result;

import java.util.List;

public class Status implements State {
    @Override
    public void start(List<String> input, ChessGame chessGame) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void play(List<String> input, ChessGame chessGame) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Result result(ChessGame chessGame) {
        chessGame.changeState(new End());
        return chessGame.result();
    }
}
