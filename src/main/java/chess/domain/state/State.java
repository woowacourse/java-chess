package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.position.Position;

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
