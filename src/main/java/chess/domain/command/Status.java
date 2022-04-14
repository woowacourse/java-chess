package chess.domain.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.GameResult;
import chess.domain.piece.Color;
import chess.view.OutputView;

public class Status extends Command{

    public Status(List<String> commands) {
        super(commands);
    }

    public boolean execute(ChessGame chessGame) {
        GameResult gameResult = new GameResult(chessGame.getBoard());
        OutputView.showScore(gameResult, Color.WHITE);
        OutputView.showScore(gameResult, Color.BLACK);
        return false;
    }
}
