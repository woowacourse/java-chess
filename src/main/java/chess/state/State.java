package chess.state;

import chess.chessgame.Chessboard;
import chess.chessgame.MovingPosition;
import chess.chessgame.Turn;
import chess.piece.Color;

public interface State {

    String UNSUPPORTED_STATE = "현재 상태에서 지원되지 않는 기능입니다.";

    State start();

    State move(MovingPosition movingPosition, Turn turn);

    Chessboard getChessboard();

    State end();

    boolean isPlaying();

    boolean isFinished();

    double computeScore(Color color, double minusScoreOfSameColumnPawn);

}
