package chess.domain.chess;

import chess.domain.piece.move.Position;

public class ChessGameHelper {
    public static void playKingDie(final ChessGame chessGame) {
        chessGame.play(new Position(7, 1), new Position(5, 0));
        chessGame.play(new Position(3, 0), new Position(4, 0));
        chessGame.play(new Position(6, 1), new Position(5, 1));
        chessGame.play(new Position(4, 0), new Position(5, 1));
        chessGame.play(new Position(6, 2), new Position(5, 2));
        chessGame.play(new Position(0, 0), new Position(5, 0));
        chessGame.play(new Position(5, 2), new Position(4, 2));
        chessGame.play(new Position(1, 3), new Position(3, 3));
        chessGame.play(new Position(6, 4), new Position(4, 4));
        chessGame.play(new Position(0, 2), new Position(5, 7));
        chessGame.play(new Position(7, 3), new Position(3, 7));
        chessGame.play(new Position(0, 6), new Position(2, 5));
        chessGame.play(new Position(7, 4), new Position(6, 4));
        chessGame.play(new Position(3, 3), new Position(4, 3));
        chessGame.play(new Position(6, 4), new Position(5, 4));
        chessGame.play(new Position(4, 3), new Position(5, 4));
    }
}
