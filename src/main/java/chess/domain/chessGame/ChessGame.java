package chess.domain.chessGame;

import chess.domain.location.Location;
import chess.domain.piece.Piece;
import java.util.Map;

public interface ChessGame {

    boolean isNotEnd();

    ChessGame startGame();

    ChessGame endGame();

    void move(Location source, Location target);

    Map<Location, Piece> getBoard();
}
