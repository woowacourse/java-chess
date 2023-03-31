package chess.domain.game.state;

import chess.domain.game.Board;
import chess.domain.game.Position;

public interface GameState {

    GameState start();

    void progress(Position source, Position target, Board board);

    boolean isNotTerminated();

    double calculateBlackScore(Board board);

    double calculateWhiteScore(Board board);
}
