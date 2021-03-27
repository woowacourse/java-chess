package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.player.PieceSet;
import chess.domain.position.Position;

import java.util.Map;

public interface GameState {

    GameState move(Position source, Position target);

    Result result(PieceSet black, PieceSet white);

    Map<Position, Piece> getChessBoard();

    boolean containsKey(Position position);

    GameState terminate();
}
