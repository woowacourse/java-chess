package chess.state;

import chess.ChessBoard;
import chess.game.Score;
import chess.piece.Color;
import chess.position.Position;

public interface State {

    State start();

    State finished();

    State move(Position from, Position to);

    ChessBoard chessBoard();

    Color currentColor();

    Color changeTurn();

    boolean isFinished();

    boolean isGameEnd();

    Score score();

    Color winner();
}
