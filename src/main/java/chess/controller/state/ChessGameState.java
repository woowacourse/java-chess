package chess.controller.state;

public interface ChessGameState {
    ChessGameState start();

    ChessGameState move(String from, String to);

    ChessGameState status();

    ChessGameState end();

    boolean isEnded();
}
