package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

import java.util.Optional;

public interface State {
    void move(Position source, Position target);

    void start();

    void end();

    boolean isFinished();

    Optional<Color> getWinnerColor();
}
