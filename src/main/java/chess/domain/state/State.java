package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.piece.Team;
import java.util.Map;

public interface State {
    State start();

    State stop();

    Map<Team, Double> status(ChessBoard chessBoard);

    State changeTurn(Command command, ChessBoard chessBoard);

    boolean isEnd();
}
