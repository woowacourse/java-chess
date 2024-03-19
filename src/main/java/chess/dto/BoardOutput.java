package chess.dto;

import chess.domain.position.Square;
import chess.domain.piece.Piece;

import java.util.Map;

public record BoardOutput(Map<Square, Piece> board) {
}
