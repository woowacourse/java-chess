package chess.service;

import chess.controller.dto.ChessBoardDto;
import chess.controller.dto.PieceDto;
import chess.controller.dto.PositionDto;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;

import java.util.HashMap;
import java.util.Map;

public final class ChessBoardService {

    private final PieceService pieceService;
    private final PositionService positionService;

    public ChessBoardService(final PieceService pieceService, final PositionService positionService) {
        this.pieceService = pieceService;
        this.positionService = positionService;
    }

    public ChessBoardDto createChessBoardDto(final Map<Position, Piece> chessBoard) {
        final Map<PositionDto, PieceDto> boardDto = new HashMap<>();
        for (Position position : chessBoard.keySet()) {
            boardDto.put(
                    positionService.createPositionDto(position.getRank(), position.getFile()),
                    pieceService.createPieceDto(chessBoard.get(position)));
        }
        return new ChessBoardDto(boardDto);
    }
}
