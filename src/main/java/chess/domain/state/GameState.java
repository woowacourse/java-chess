package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface GameState {

    GameState move(Position source, Position target);

    Result result();

    Map<Position, Piece> getChessBoard();

    boolean containsKey(Position position);

    //todo: 삭제필요
    Piece getPiece(Position position);

    void put(Position position, Piece piece);

}
