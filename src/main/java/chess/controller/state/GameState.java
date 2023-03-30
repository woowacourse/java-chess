package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.Scores;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.position.Position;
import chess.view.OutputView;
import java.util.Map;

public interface GameState {

    GameState changeState(final GameCommand gameCommand);

    void executeAndSave(final Position source, final Position target);

    boolean isNotEnd();

    Scores calculateScores();

    PieceColor findCurrentPlayer();

    void printScores(final OutputView outputView);

    void printBoardStatus(final OutputView outputView);

    void printCreateGameMessage(final OutputView outputView);

    Map<Position, Piece> getBoard();
}
