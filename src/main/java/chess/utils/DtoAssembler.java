package chess.utils;

import chess.domain.board.LineDto;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DtoAssembler {
    public static List<LineDto> assemble(List<Map<Position, Piece>> squares) {
        List<LineDto> lineDtos = new ArrayList<>();
        for (Map<Position, Piece> square : squares) {
            List<String> pieces = square.values().stream().map(Piece::getSymbol).collect(Collectors.toList());
            lineDtos.add(new LineDto(pieces));
        }
        return lineDtos;
    }
}
