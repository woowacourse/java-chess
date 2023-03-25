package chess.domain.chess;

import chess.domain.piece.move.Position;

public class ChessGameHelper {
    public static boolean playKingDie(final ChessGame chessGame) {
        chessGame.run(new Position(7, 1), new Position(5, 0), CampType.BLACK);
        chessGame.run(new Position(3, 0), new Position(4, 0), CampType.WHITE);
        chessGame.run(new Position(6, 1), new Position(5, 1), CampType.BLACK);
        chessGame.run(new Position(4, 0), new Position(5, 1), CampType.WHITE);
        chessGame.run(new Position(6, 2), new Position(5, 2), CampType.BLACK);
        chessGame.run(new Position(0, 0), new Position(5, 0), CampType.WHITE);
        chessGame.run(new Position(5, 2), new Position(4, 2), CampType.BLACK);
        chessGame.run(new Position(1, 3), new Position(3, 3), CampType.WHITE);
        chessGame.run(new Position(6, 4), new Position(4, 4), CampType.BLACK);
        chessGame.run(new Position(0, 2), new Position(5, 7), CampType.WHITE);
        chessGame.run(new Position(7, 3), new Position(3, 7), CampType.BLACK);
        chessGame.run(new Position(0, 6), new Position(2, 5), CampType.WHITE);
        chessGame.run(new Position(7, 4), new Position(6, 4), CampType.BLACK);
        chessGame.run(new Position(3, 3), new Position(4, 3), CampType.WHITE);
        chessGame.run(new Position(6, 4), new Position(5, 4), CampType.BLACK);
        return chessGame.run(new Position(4, 3), new Position(5, 4), CampType.WHITE);
    }
}
