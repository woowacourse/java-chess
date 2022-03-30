package chess.controller.menu;

import chess.domain.board.Board;

public class End implements Menu{

    @Override
    public boolean play(Board board) {
        return false;
    }
}
