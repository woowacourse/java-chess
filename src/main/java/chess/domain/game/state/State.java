package chess.domain.game.state;

import chess.domain.piece.Position;

import java.util.Optional;

public interface State {
    void move(Position source, Position target);

    void start();

    void end();

    boolean isFinished();

    String getStatus();

    Optional<String> getWinnerColorNotation();
}
