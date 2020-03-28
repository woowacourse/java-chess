package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Map;

public interface State {

    State start();

    State move(MoveParameter moveParameter, Turn turn);

    State end();

    Board getBoard();

    boolean isEnd();

    Map<Position, PieceState> getRemainPiece(Team team);
}
