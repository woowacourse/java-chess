package chess.DTO;

import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {

    private final Map<String, String> board;

    public BoardDto(final Board board) {
        this.board = Position.getPositionCache()
            .stream()
            .collect(Collectors
                .toMap(Position::toString, position -> board.findPieceBy(position).getName()));
    }

    public Map<String, String> getBoard() {
        board.entrySet().forEach(entry -> System.out.println(entry.getKey() + entry.getValue()));
        return board;
    }
}
