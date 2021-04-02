package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.team.PieceSet;

import java.util.Map;

public interface GameState {

    GameState move(Position source, Position target);

    Result result(PieceSet black, PieceSet white);

    Map<Position, Piece> getChessBoard();

    GameState terminate();

    boolean isRunning();

}
