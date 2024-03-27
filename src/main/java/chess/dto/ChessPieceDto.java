package chess.dto;

import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPieceType;

public record ChessPieceDto(
        ChessPieceType chessPieceType,
        Camp camp) {
}
