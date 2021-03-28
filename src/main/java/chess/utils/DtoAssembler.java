package chess.utils;

import chess.domain.board.Board;
import chess.domain.board.position.Ypoint;
import chess.domain.dto.RankDto;
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
}
