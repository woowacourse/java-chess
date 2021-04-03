package chess.domain.piece;

import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import chess.domain.team.Score;

import java.util.Map;

public interface Piece {

    boolean isMovable(Position target, Map<Position, Piece> chessBoard);

    String getPieceName();

    String getPieceType();

    TeamColor getColor();

    void dead();

    boolean isAlive();

    void changePosition(Position end);

    Score getScore();

    Character getColumn();

    boolean isKing();

    boolean isPawn();

}