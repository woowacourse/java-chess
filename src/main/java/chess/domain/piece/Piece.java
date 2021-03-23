package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.board.Position;
import chess.domain.board.Strategy;
import chess.domain.board.Team;

import java.util.List;

public interface Piece {

    Strategy strategy();

    String getName();

    Team getTeam();

    void confirmTurn(Team team);

    boolean isSameTeam(Team team);

    boolean isPawn();

    boolean isKing();

    double getPoint();

    List<Position> generate(Path path, boolean target);
}
