package chess.domain.chessGame;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

@FunctionalInterface
public interface BoardGenerator {

    Map<Position, Piece> generate();
}
