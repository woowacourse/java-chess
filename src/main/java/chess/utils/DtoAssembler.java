package chess.utils;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.board.position.Ypoint;
import chess.dto.MovableResponseDto;
import chess.dto.PositionDto;
import chess.dto.RankDto;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoAssembler {

    public static List<RankDto> board(final Board board) {
        List<RankDto> rankDtos = new ArrayList<>();

        for (Ypoint ypoint : Ypoint.values()) {
            List<String> symbols = ypointSymbols(board, ypoint);
            rankDtos.add(new RankDto(symbols));
        }

        return rankDtos;
    }

    private static List<String> ypointSymbols(Board board, Ypoint ypoint) {
        return board.piecesByYpoint(ypoint)
            .stream()
            .map(Piece::getSymbol)
            .collect(Collectors.toList());
    }

    public static MovableResponseDto movableResponse(List<Position> positions) {
        return new MovableResponseDto(
            positions.stream()
            .map(PositionDto::new)
            .collect(Collectors.toList())
        );
    }
}
