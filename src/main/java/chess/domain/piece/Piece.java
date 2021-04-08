package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.result.Score;
import chess.domain.state.State;
import chess.domain.state.TeamColor;

public interface Piece {

    boolean isMovable(Position source, Position target, ChessBoard chessBoard);

    String getPieceName();

    boolean isEnemyTeam(Piece comparePiece);

    TeamColor getColor();

    void dead();

    State getState();

    void setPosition(Position end);

    Score getScore();

    Character getColumn();

}