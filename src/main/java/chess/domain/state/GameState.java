package chess.domain.state;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public interface GameState {
    Piece getPiece(Position position);

    GameState move(String source, String destination);

    Map<Position, Piece> getBoard();

    boolean isRunning();

    Team getTeam();
}
