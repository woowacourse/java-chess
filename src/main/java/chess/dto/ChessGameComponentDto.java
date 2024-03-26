package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public record ChessGameComponentDto(Position position, Piece piece, int gameId) {
}
