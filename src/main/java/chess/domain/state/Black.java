package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.dto.ChessInputDto;

public class Black extends State {
    public Black(final Board board) {
        super(board);
    }

    @Override
    public State start() {
        return new Start(Board.create());
    }

    @Override
    public State move(final ChessInputDto dto) {
        board.move(dto.getSource(), dto.getTarget(), Color.BLACK);
        return new White(board);
    }

    @Override
    public State end() {
        return new End(board);
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
