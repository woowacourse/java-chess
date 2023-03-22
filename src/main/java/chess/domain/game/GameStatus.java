package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public interface GameStatus {

    GameStatus start();

    GameStatus playTurn(Position source, Position target);

    GameStatus end();

    boolean isOnGoing();

    Map<Position, Piece> getBoard();

    GameStatus save();
}
