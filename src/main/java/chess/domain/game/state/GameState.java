package chess.domain.game.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public interface GameState {
    GameState movePiece(Position source, Position target);

    boolean isFinish();

    boolean isWaiting();

    Map<Color, Double> calculateScore();

    Color getWinTeamColor();

    Board board();
}
