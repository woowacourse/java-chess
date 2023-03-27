package chess.controller;

import chess.domain.chessgame.ChessGame;
import chess.domain.result.Score;
import chess.domain.side.Color;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class StatusCommand implements Command {

    @Override
    public ChessGame execute(final ChessGame chessGame, final List<String> input, final OutputView outputView) {
        Map<Color, Score> status = chessGame.status();
        outputView.printStatus(status);
        return chessGame;
    }

}
