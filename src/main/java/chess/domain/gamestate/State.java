package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface State {

    State start();

    State load(Map<Position, Piece> board, boolean whiteTurn);

    State move(Position sourcePosition, Position targetPosition);

    State end();

    boolean isFinished();

    Board getBoard();

    Map<Camp, Score> getScores();

    Camp getWinner();
}
