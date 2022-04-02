package chess.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Start extends Command{

    public Start(List<String> commands) {
        super(commands);
    }

    public boolean execute(ChessGame chessGame) {
        checkInGame(chessGame);
        chessGame.startGame();
        OutputView.showBoard(chessGame.getBoard());
        return true;
    }
}
