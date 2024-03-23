package domain.game;

import domain.position.Position;

public interface Execute {
    void start();

    void move(Position source, Position target);

    void end();
}
