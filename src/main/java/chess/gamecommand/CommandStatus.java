package chess.gamecommand;

import java.util.List;

import chess.board.Position;
import chess.piece.Piece;

public interface CommandStatus {

    CommandStatus start();

    CommandStatus move(Position sourcePosition, Position targetPosition);

    CommandStatus end();

    boolean isEnd();

    List<Piece> getPieces();
}
