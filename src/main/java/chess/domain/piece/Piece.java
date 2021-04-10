package chess.domain.piece;

import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import chess.domain.state.Score;

import java.util.Map;

public interface Piece {

    String getPieceName();

    String getPieceType();

    TeamColor getColor();

    void dead();

    boolean isAlive();

    Score getScore();

    boolean isKing();

    boolean isPawn();

    boolean isMovable(Position source, Position target, Map<Position, Piece> chessBoard);
}