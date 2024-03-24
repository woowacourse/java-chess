package chess.domain.board.dto;

import chess.domain.square.Square;
import chess.domain.piece.Piece;

import java.util.Map;

public record BoardOutput(Map<Square, Piece> board) {
}
