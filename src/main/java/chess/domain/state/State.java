package chess.domain.state;

import chess.Score;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public interface State {

    State start();

    State move(Position source, Position target);

    State end();

    boolean isFinished();

    Score computeScore(Color color);

    Chessboard getChessboard();

    boolean isRunning();
}
