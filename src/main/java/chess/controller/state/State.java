package chess.controller.state;

import chess.dto.ChessBoardDto;


public interface State {

    State start();

    void move(final String source, final String dest);

    State end();

    boolean isNotEnd();

    ChessBoardDto getBoard();

}
