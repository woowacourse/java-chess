package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.board.Squares;

import java.util.List;

public interface GameStatus {

    GameStatus start();

    GameStatus playTurn(Position source, Position target);

    List<Squares> getBoard();
}
