package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.State;
import chess.domain.TeamColor;
import chess.domain.player.Score;

public interface Piece {

    boolean isMoveAble(Position target, ChessBoard chessBoard);

    String getPieceName();

    boolean isEnemyTeam(Piece comparePiece);

    TeamColor getColor();

    void dead();

    State getState();

    void setCurrentPosition(Position end);

    Score getScore();

    Character getColumn();
}