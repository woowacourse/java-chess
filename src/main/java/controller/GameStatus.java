package controller;

import domain.chessboard.ChessBoardFactory;
import domain.chessgame.ChessGame;

import java.util.List;

public abstract class GameStatus {

    private static final int COMMAND_INDEX = 0;

    protected final ChessGame chessGame;

    protected GameStatus(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public abstract void playTurn(final List<String> inputs);

    public abstract boolean isKeepGaming();

    public final GameStatus transition(final List<String> inputs) {
        final Command command = Command.from(inputs.get(COMMAND_INDEX));

        if (command == Command.START) {
            return new Start(new ChessGame(ChessBoardFactory.generate()));
        }
        if (command == Command.MOVE) {
            return new Move(chessGame);
        }
        if (command == Command.STATUS) {
            return new Status(chessGame);
        }
        return new End(chessGame);
    }

}
