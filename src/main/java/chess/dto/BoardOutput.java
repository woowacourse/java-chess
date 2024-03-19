package chess.dto;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public record BoardOutput(Map<Position, Piece> board) {
}
