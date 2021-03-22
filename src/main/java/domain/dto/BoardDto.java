package domain.dto;

import domain.Board;
import domain.piece.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BoardDto implements MenuDto{
    private List<String> result = new ArrayList<>();

    public BoardDto(Board board) {
        AtomicInteger count = new AtomicInteger(0);
        IntStream.range(0, 8)
                .boxed()
                .flatMap(row -> IntStream.range(0, 8)
                        .mapToObj(column -> Position.valueOf(row, column)))
                .forEach(position -> {
                    result.add(board.getPiece(position).getName());
                    if (count.incrementAndGet() % 8 == 0) {
                        result.add("\n");
                    }
                });
    }

    public List<String> getMenuDto() {
        return Collections.unmodifiableList(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(result, boardDto.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
