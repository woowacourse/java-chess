package chessgame.domain.state;

import chessgame.domain.Team;

public interface Running extends State {
    Team team();
}
