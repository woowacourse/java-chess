package chess.domain.state;

import chess.Command;
import chess.domain.Status;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface ChessState {

    ChessState execute(Command command, String... commandArgs);

    boolean isEnd();

    Map<Position, Piece> getPieces();

    Status createStatus();
}
