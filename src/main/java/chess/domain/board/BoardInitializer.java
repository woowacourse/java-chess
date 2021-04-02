package chess.domain.board;

import java.util.List;

public interface BoardInitializer {
    Board createBoard(List<Square> squares);
}
