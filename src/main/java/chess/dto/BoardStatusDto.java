package chess.dto;

import chess.domain.Position;
import chess.domain.Status;
import chess.domain.piece.Piece;
import java.util.Map;

public record BoardStatusDto(Map<Position, Piece> board, Status status) {
}
