package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.GameResult;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.Map;

public interface State {

    State start();

    State move(Positions positions);

    State move(Position before, Position after);

    Camp switchCamp();

    State end();

    double statusOfBlack();

    double statusOfWhite();

    Map<Position, Piece> getBoard();

    GameResult calculateResult();

    boolean isRunning();
}
