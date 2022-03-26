package chess.utils;

import chess.piece.Piece;

import java.util.List;

public interface ChessboardGenerator {

    List<List<Piece>> generate();
}
