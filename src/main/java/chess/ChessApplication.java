package chess;

import chess.state.Ready;

public class ChessApplication {

    public static void main(String[] args) {
        final ChessGame chessGame = new ChessGame(new Ready());
        chessGame.run();
    }
}
