package chess.dto;

import chess.domain.position.File;
import chess.domain.position.Rank;

public record MoveArgumentDto(File startFile, Rank startRank, File endFile, Rank endRank) implements ArgumentDto {
}
