package chess.dto;

import chess.domain.board.Board;
import java.util.List;
import java.util.stream.Collectors;

public class BoardResultDto {

    private final List<PieceDto> pieces;

    private BoardResultDto(final List<PieceDto> pieces) {
        this.pieces = pieces;
    }

    public static BoardResultDto toDto(final Board board) {

        List<PieceDto> positionsWithPieces = board.getBoard().entrySet().stream()
                .map(entry -> PieceDto.toDto(entry.getKey(), board.getBoard().get(entry.getKey())))
                .collect(Collectors.toList());

        return new BoardResultDto(positionsWithPieces);
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }
}
