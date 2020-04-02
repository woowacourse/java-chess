package chess.domain.piece;


import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;

public interface Piece {
    Piece move(Position to, Board board);
    Team getTeam();
    boolean isNotBlank();
    boolean isBlank();
}
