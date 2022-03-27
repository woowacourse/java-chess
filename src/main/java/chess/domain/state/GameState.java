package chess.domain.state;

import chess.domain.*;
import chess.domain.piece.Piece;

import java.util.Map;

public interface GameState {

    Piece getPiece(Position position);

    GameState move(String source, String destination);

    Map<Row, Rank> getBoard();

    boolean isFinished();

    int getTeamScore();

    Team getTeam();
}
