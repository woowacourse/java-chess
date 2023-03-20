package chess.controller.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class BoardDto {

    private final Map<PositionDto, PieceDto> board;

    private BoardDto(final Map<PositionDto, PieceDto> board) {
        this.board = board;
    }

    public static BoardDto from(final Map<Position, Piece> board) {
        final Map<PositionDto, PieceDto> boardDto = new HashMap<>();
        for (Position position : board.keySet()) {
            boardDto.put(PositionDto.from(position.getRank(), position.getFile()),
                    PieceDto.from(board.get(position)));
        }
        return new BoardDto(boardDto);
    }

    public Map<PositionDto, PieceDto> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
