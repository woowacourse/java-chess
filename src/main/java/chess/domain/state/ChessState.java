package chess.domain.state;

import chess.domain.Status;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface ChessState {

    boolean isRunning();

    boolean isFinished();

    ChessState start();

    ChessState move(Position start, Position target);

    ChessState end();

    Map<Position, Piece> getPieces();

    Status createStatus();
}
