package chess.domain.gamestate;

import chess.domain.Color;
import chess.domain.Winner;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface State {

    State start();

    State move(Position beforePosition, Position afterPosition);

    State end();

    double statusOfBlack();

    double statusOfWhite();

    boolean isRunning();

    Board getBoard();

    Winner findWinner();

    State load(Map<Position, Piece> board, Color turn);

    Color getTurn();
}
