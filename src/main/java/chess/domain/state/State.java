package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.TeamScore;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public interface State {

    State start();

    State end();

    boolean isRunning();

    Board getBoard();

    Piece move(Team currentTeam, Location source, Location target);

    TeamScore getScore();

    Team getTeam();

    State getNextState(Piece piece);
}
