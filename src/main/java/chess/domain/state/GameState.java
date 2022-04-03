package chess.domain.state;

import chess.domain.Command;
import chess.domain.ChessBoard;
import chess.domain.Team;
import java.util.List;

public interface GameState {
    GameState execute(Command command, List<String> input);

    Team findWinner();

    boolean isFinished();

    boolean isEnd();

    ChessBoard getChessBoard();
}
