package chess.dto;

import chess.domain.chessboard.Square;
import java.util.Map;

public record ChessBoardDto(
        Map<Square, ChessPieceDto> chessBoard) {
}
