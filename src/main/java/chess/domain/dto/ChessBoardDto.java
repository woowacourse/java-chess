package chess.domain.dto;


import chess.domain.piece.Piece;
import chess.domain.piece.info.Position;

import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardDto {
    private Map<PositionDto, PieceDto> chessBoard;

    public ChessBoardDto(Map<Position, Piece> chessBoard) {
        Map<PositionDto, PieceDto> chessBoardDto = chessBoard.keySet().stream()
                .collect(Collectors.toMap(position -> new PositionDto(String.valueOf(position.getX()) + position.getY()),
                        position -> new PieceDto(chessBoard.get(position).getName(), chessBoard.get(position).getColor().name())));
        this.chessBoard = chessBoardDto;
    }

    public Map<PositionDto, PieceDto> getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Map<PositionDto, PieceDto> chessBoard) {
        this.chessBoard = chessBoard;
    }
}
