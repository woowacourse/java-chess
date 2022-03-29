package chess.utils;

import chess.chessgame.Position;
import chess.piece.Piece;

import java.util.Map;

public interface ChessboardGenerator {

    Map<Position, Piece> generate();
}
