package chess.domain.gamestatus;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.score.Score;
import java.util.Map;

public interface GameStatus {

    GameStatus start();

    GameStatus move(String fromPosition, String toPosition);

    Score scoring();

    GameStatus finish();

    Map<Position, Piece> getBoard();

    String getBoardString();

}
