package chess.dto;

import chess.domain.Position;
import chess.domain.Status;
import chess.domain.piece.character.Character;
import java.util.Map;

public record BoardStatusDto(Map<Position, Character> board, Status status) {
}
