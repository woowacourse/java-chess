package chess.domain.gamestate;

import chess.domain.game.ChessGame;
import chess.domain.game.Result;

import java.util.List;

public class Running implements State {
    @Override
    public void start(List<String> input, ChessGame chessGame) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void play(List<String> input, ChessGame chessGame) {
        if (Option.isStatus(input)) {
            chessGame.changeState(new Status());
            return;
        }
        chessGame.movePiece(input);
        if (!chessGame.isOngoing()) {
            chessGame.changeState(new Status());
        }
    }

    @Override
    public Result result(ChessGame chessGame) {
        return null;
    }
}
