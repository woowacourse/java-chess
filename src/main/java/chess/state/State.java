package chess.state;

import chess.chessgame.Chessboard;
import chess.chessgame.MovingPosition;
import chess.chessgame.Turn;
import chess.piece.Color;

public interface State {

    State start();

    State move(MovingPosition movingPosition, Turn turn);

    Chessboard getChessboard();

    boolean isFinished();

    double computeScore(Color color, double minusScoreOfSameColumnPawn);

}
