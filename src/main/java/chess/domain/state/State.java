package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.piece.PieceState;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.domain.result.Status;

import java.util.Map;

public interface State {

    State start();

    State move(MoveParameter moveParameter, Turn turn);

    State end();

    State status();

    boolean isEnd();

    Board getBoard();

    Map<Position, PieceState> getRemainPiece(Player player);

    Status getStatus();
}
