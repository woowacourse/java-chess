package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.TeamScore;
<<<<<<< HEAD
=======
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
>>>>>>> step1

public interface State {

    State start();

    State end();

    boolean isRunning();

    Board getBoard();

<<<<<<< HEAD
    State move(Location source, Location target);

    TeamScore getScore();
=======
    Piece move(Team currentTeam, Location source, Location target);

    TeamScore getScore();

    Team getTeam();

    State getNextState(Piece piece);
>>>>>>> step1
}
