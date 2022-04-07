package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.piece.Color;
import chess.domain.board.piece.PieceType;
import chess.domain.board.position.Position;
import chess.domain.event.Event;
import chess.domain.event.MoveCommand;
import chess.domain.game.statistics.GameResult;

abstract class Running extends Started {

    private static final int ONGOING_GAME_KING_COUNT = 2;
    private static final String NOT_YET_IMPLEMENTED_EXCEPTION_MESSAGE = "아직 구현되지 않은 기능입니다.";

    Running(Board board) {
        super(board);
    }

    @Override
    public Game play(Event event) {
        if (event.isMove()) {
            return moveChessmen(event.toMoveCommand());
        }
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_EXCEPTION_MESSAGE);
    }

    @Override
    public final Game moveChessmen(MoveCommand moveCommand) {
        Position from = moveCommand.getSource();
        Position to = moveCommand.getTarget();

        board.movePiece(from, to, getCurrentTurnColor());
        return moveResult();
    }

    private Game moveResult() {
        if (board.countByType(PieceType.KING) < ONGOING_GAME_KING_COUNT) {
            return new GameOver(board);
        }
        return continueGame();
    }

    protected abstract Color getCurrentTurnColor();

    protected abstract Game continueGame();

    @Override
    public final boolean isEnd() {
        return false;
    }

    @Override
    public final GameResult getResult() {
        throw new UnsupportedOperationException("아직 종료되지 않은 게임입니다.");
    }
}
