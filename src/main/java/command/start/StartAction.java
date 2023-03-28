package command.start;

import domain.ChessGame;

@FunctionalInterface
public interface StartAction {
    ChessGame init();
}
