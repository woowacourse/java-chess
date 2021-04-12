package chess.controller;

import chess.domain.game.BoardFactory;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.domain.location.Position;

public abstract class ChessController {
    protected Game game;

    public void action(String command) {
        Command.from(command)
               .action(this);
    }

    public void init() {
        game = new Game(BoardFactory.create());
    }

    public void move(String from, String to) {
        game.move(Position.from(from), Position.from(to));
    }

    public void end() {
    }

    public abstract void status();
}
