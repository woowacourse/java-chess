package chess.utils;

import chess.domain.board.LineDto;
import chess.domain.board.position.Position;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DtoAssembler {
    public static List<LineDto> assemble(Map<Position, Piece> squares) {
        List<LineDto> lineDtos = new ArrayList<>();
        for (Ypoint ypoint : Ypoint.values()) {
            List<String> pieces = squares.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().isSameY(ypoint))
                    .map(entry -> entry.getValue().getSymbol())
                    .collect(Collectors.toList());
            lineDtos.add(new LineDto(pieces));
        }
        return lineDtos;
    }
}
