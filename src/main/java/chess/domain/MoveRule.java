package chess.domain;

import java.util.Optional;

public interface MoveRule {
    boolean check(Position source, Position target, Optional<Team> targetTeam);
}
