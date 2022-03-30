package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public interface GameState {
    GameState movePiece(Position fromPosition, Position toPosition);

    boolean isFinish();

    boolean isWaiting();

    double calculateScore(Color color);

    Color getWinTeamColor();

    Board board();
}
