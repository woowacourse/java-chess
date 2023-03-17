package chess.controller.state;

import chess.dto.ChessBoardDto;
import java.util.List;


public interface State {

    State start();

    void move(final List<Integer> source, final List<Integer> dest);

    State end();

    boolean isNotEnd();

    ChessBoardDto getBoard();

}
