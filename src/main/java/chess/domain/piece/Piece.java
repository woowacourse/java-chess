package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.Score;
import java.util.Map;

public interface Piece {

    boolean isMoveAble(Position target, Map<Position, Piece> chessBoard);

    String getPieceName();

    TeamColor getColor();

    void dead();

    State getState();

    void changePosition(Position end);

    Score getScore();

    Character getColumn();
}