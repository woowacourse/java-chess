package chess.utils;

import chess.domain.LineDto;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DtoAssembler {
    public static List<LineDto> assemble(Map<Position, Piece> squares) {
        List<LineDto> lineDtos = new ArrayList<>();
        List<String> pieces = squares.values().stream().map(Piece::getSymbol).collect(Collectors.toList());
        for (int i = 0; i < 8; i++) {
            lineDtos.add(new LineDto(pieces.subList(i * 8, (i + 1) * 8)));
        }
        return lineDtos;
    }
}
