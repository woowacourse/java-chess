package chess.domain.game;

import static chess.domain.board.piece.PieceType.KING;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.dto.request.MoveCommandDto;

abstract class Running extends Started {

    private static final int ONGOING_GAME_KING_COUNT = 2;

    Running(Board board, GameState state) {
        super(board, state);
    }

    @Override
    public final Game moveChessmen(MoveCommandDto moveCommand) {
        Position from = moveCommand.source();
        Position to = moveCommand.target();

        board.movePiece(from, to, getCurrentTurnColor());
        return moveResult();
    }

    static Game of(GameState state, Board board) {
        if (state == GameState.BLACK_TURN) {
            return new BlackTurn(board);
        }
        return new WhiteTurn(board);
    }

    private Game moveResult() {
        if (board.countByType(KING) < ONGOING_GAME_KING_COUNT) {
            return new GameOver(board);
        }
        return continueGame();
    }

    protected abstract Game continueGame();

    @Override
    public final GameResult getResult() {
        throw new UnsupportedOperationException("아직 종료되지 않은 게임입니다.");
    }

    @Override
    public final boolean isEnd() {
        return false;
    }
}
