package domain.game;

import domain.position.Position;

@FunctionalInterface
public interface RouteValidator {
    void validate(Position source, Position target);
}
