package chess.model.board.state;

import chess.controller.GameCommand;
import chess.model.Scores;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public interface GameState {

    GameState changeState(final GameCommand gameCommand);

    void execute(final GameCommand gameCommand, final Position source, final Position target);

    boolean isNotEnd();

    GameState isGameEnd();

    boolean isStatus();

    Scores calculateScores();

    Map<Position, Piece> getBoard();
}
