package chess.domain.game;

import static chess.domain.board.piece.PieceType.KING;

import chess.domain.board.Board;
import chess.domain.board.piece.Color;
import chess.domain.board.position.Position;
import chess.dto.MoveCommandDto;

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
