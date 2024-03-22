package chess.dto;

import chess.domain.chessboard.Square;
import java.util.Map;
import java.util.Optional;

public record ChessBoardDto(
        Map<Square, Optional<ChessPieceDto>> chessBoard) {
}
