package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

import java.util.Map;

public interface GameStatus {

    GameStatus start();

    GameStatus load(GameStatus gameStatus);

    GameStatus playTurn(Position source, Position target);

    GameStatus end();

    boolean isOnGoing();

    Map<Position, Piece> getBoard();

    double computeScore(Color color);

    Color getTurn();
}
