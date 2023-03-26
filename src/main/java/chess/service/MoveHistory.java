package chess.service;

import chess.dto.MoveDto;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MoveHistory {

    private final List<MoveDto> moveDtos;

    public MoveHistory(List<MoveDto> moveDtos) {
        this.moveDtos = moveDtos;
    }

    public List<MoveDto> getSortHistory() {
        return moveDtos.stream()
                .sorted(Comparator.comparing(MoveDto::getId))
                .collect(Collectors.toList());
    }
}
