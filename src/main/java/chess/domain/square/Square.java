package chess.domain.square;

import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;

import java.util.List;

public interface Square {
    List<Position> findPassPathTaken(TerminalPosition terminalPosition);

    List<Position> findAttackPathTaken(TerminalPosition terminalPosition);

    void move();
}
