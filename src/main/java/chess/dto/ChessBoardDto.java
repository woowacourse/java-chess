package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public record ChessBoardDto(Map<Position, Piece> chessBoard) {
}
