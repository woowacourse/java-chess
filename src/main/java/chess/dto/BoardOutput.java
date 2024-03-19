package chess.dto;

import chess.domain.position.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public record BoardOutput(Map<Position, Piece> board) {
}
