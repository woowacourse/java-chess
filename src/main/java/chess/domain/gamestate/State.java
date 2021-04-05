package chess.domain.gamestate;

import chess.domain.game.ChessGame;
import chess.domain.game.Result;

import java.util.List;

public interface State {
    void start(List<String> input, ChessGame chessGame);

    void play(List<String> input, ChessGame chessGame);

    Result result(ChessGame chessGame);
}
