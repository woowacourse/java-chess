package chess.domain.command;

import java.util.List;

import chess.domain.position.Position;
import chess.domain.piece.Piece;

public interface CommandStatus {

    CommandStatus start();

    CommandStatus move(Position sourcePosition, Position targetPosition);

    CommandStatus end();

    boolean isEnd();

    List<Piece> getPieces();

    String getTurnDisplayName();
}
