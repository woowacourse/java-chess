package chess.domain;

import chess.domain.chesspieces.ChessPiece;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, ChessPiece> chessBoard = new LinkedHashMap<>();
}
