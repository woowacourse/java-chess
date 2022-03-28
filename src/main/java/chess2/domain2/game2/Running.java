package chess2.domain2.game2;

import static chess2.domain2.board2.piece2.PieceType.KING;

import chess2.domain2.board2.Board;
import chess2.domain2.board2.Position;
import chess2.domain2.board2.piece2.Color;
import chess2.dto2.MoveCommandDto;

abstract class Running extends Started {

    private static final int ONGOING_GAME_KING_COUNT = 2;

    Running(Board board) {
        super(board);
    }

    @Override
    public final Game moveChessmen(MoveCommandDto moveCommand) {
        Position from = fromPosition(moveCommand);
        Position to = toPosition(moveCommand);

        board.movePiece(from, to, currentTurnColor());
        return moveResult();
    }

    private Game moveResult() {
        if (board.countByType(KING) < ONGOING_GAME_KING_COUNT) {
            return new GameOver(board);
        }
        return continueGame();
    }

    abstract protected Color currentTurnColor();

    abstract protected Game continueGame();

    private Position fromPosition(MoveCommandDto moveCommand) {
        String source = moveCommand.source();
        return Position.of(source);
    }

    private Position toPosition(MoveCommandDto moveCommand) {
        String target = moveCommand.target();
        return Position.of(target);
    }

    @Override
    public final GameResult result() {
        throw new UnsupportedOperationException("아직 종료되지 않은 게임입니다.");
    }

    @Override
    public final boolean isEnd() {
        return false;
    }
}
