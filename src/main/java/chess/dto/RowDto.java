package chess.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RowDto {

    private final List<SquareDto> squares;

    public RowDto(String displayRow) {
        this.squares = Arrays.stream(displayRow.split(""))
                .map(SquareDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<SquareDto> getSquares() {
        return squares;
    }
}
