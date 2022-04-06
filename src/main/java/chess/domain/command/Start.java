package chess.domain.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.GameTurn;
import chess.domain.board.InitialBoardGenerator;
import chess.view.OutputView;

public class Start extends Command{

    public Start(List<String> commands) {
        super(commands);
    }

    public boolean execute(ChessGame chessGame) {
        checkInGame(chessGame);
        chessGame.startGame(new InitialBoardGenerator(), GameTurn.WHITE);
        OutputView.showBoard(chessGame.getBoard());
        return true;
    }
}
