package domain.game;

import domain.position.Position;

public interface Executable {
    void start();

    void move(Position source, Position target);

    void end();
}
