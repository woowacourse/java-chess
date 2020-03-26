package chess.domain.Piece;


import chess.domain.Piece.team.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;

public interface Piece {
    Piece move(Position to, Board board);
    Team getTeam();
    boolean isNotBlank();
    boolean isBlank();

}
