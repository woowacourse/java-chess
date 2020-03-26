package chess.domain.Piece;


import chess.domain.board.Board;
import chess.domain.position.Position;

public interface Piece extends Individual{
    Piece move(Position to, Board board);
}
