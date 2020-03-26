package chess.domain.Piece;


import chess.domain.Board;
import chess.domain.position.Position;

public interface Piece extends Individual{
    Piece move(Position to, Board board);
}
