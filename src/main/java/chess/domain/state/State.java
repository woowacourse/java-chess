package chess.domain.state;

import chess.domain.Score;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public interface State {

    String INIT_TURN = "blank";
    String EXCEPTION_TURN_LOAD = "Play 상태에서 turn을 load해주세요.";

    State start();

    State move(Position source, Position target);

    State end();

    boolean isFinished();

    boolean isRunning();

    boolean isRightTurn(String turn);

    Score computeScore(Color color);

    void loadTurn();

    State loadBoard(Map<String, Piece> pieces);

    String turn();

    Chessboard getChessboard();
}
