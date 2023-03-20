package chess.controller;

import chess.domain.Position;

@FunctionalInterface
public interface WugaAction {
    void execute(String... strings);
}
