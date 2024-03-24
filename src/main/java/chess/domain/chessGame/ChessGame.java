package chess.domain.chessGame;

import chess.domain.location.Location;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.function.Supplier;

public interface ChessGame {

    boolean isNotEnd();

    ChessGame startGame(Supplier<Boolean> checkRestart);

    ChessGame endGame();

    void move(Location source, Location target);

    Map<Location, Piece> getBoard();
}
