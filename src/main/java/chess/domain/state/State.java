package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public interface State {

    State start(List<String> parameters);

    State move(MoveParameter moveParameter);

    State end(List<String> parameters);

    ChessGame getChessGame();

    Map<Position, PieceState> getBoard();

    Map<Team, Double> getStatus();

    boolean isEnd();

    Team getWinner();
}
