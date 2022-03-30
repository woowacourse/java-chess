package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public interface GameState {

    GameState initBoard();

    GameState movePiece(Position fromPosition, Position toPosition);

    GameState end();

    boolean isFinish();

    Board getBoard();

    double calculateScore(Color color);

    Color judgeWinner();
}
