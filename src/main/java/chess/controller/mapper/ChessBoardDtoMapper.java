package chess.controller.mapper;

import chess.controller.dto.ChessBoardDto;
import chess.controller.dto.PieceDto;
import chess.controller.dto.PositionDto;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;

import java.util.HashMap;
import java.util.Map;

public final class ChessBoardDtoMapper {

    public static ChessBoardDto createChessBoardDto(final Map<Position, Piece> chessBoard) {
        final Map<PositionDto, PieceDto> boardDto = new HashMap<>();
        for (Position position : chessBoard.keySet()) {
            boardDto.put(
                    PositionDtoMapper.createPositionDto(position.getRank(), position.getFile()),
                    PieceDtoMapper.createPieceDto(chessBoard.get(position)));
        }
        return new ChessBoardDto(boardDto);
    }
}
