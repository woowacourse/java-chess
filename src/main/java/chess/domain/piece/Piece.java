package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.Score;

public interface Piece {

    boolean isMoveAble(Position target, ChessBoard chessBoard);

    String getPieceName();

    TeamColor getColor();

    void dead();

    State getState();

    void setCurrentPosition(Position end);

    Score getScore();

    Character getColumn();
}