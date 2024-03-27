package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Movement;
import chess.domain.square.Square;
import java.util.List;
import java.util.Map;

public class Game {

    private static final String INVALID_TURN = "헤당 색의 턴이 아닙니다.";
    private final long roomId;
    private final Board board;
    private final Turn turn;

    public Game(final long roomId, final BoardFactory boardFactory) {
        this.roomId = roomId;
        this.turn = Turn.first();
        this.board = boardFactory.createBoard();
    }

    public static Game load(final long roomId, final List<Movement> movements, final BoardFactory boardFactory) {
        Game game = new Game(roomId, boardFactory);
        Board board = game.board;
        for (Movement movement : movements) {
            board.move(movement.getSource(), movement.getTarget());
        }
        game.turn.proceedTurn(movements.size());
        return game;
    }

    public void movePiece(final String source, final String target) {
        Square from = Square.from(source);
        Square to = Square.from(target);
        validateTurn(from);
        board.move(from, to);
        turn.next();
    }

    private void validateTurn(final Square square) {
        if (!board.checkTurn(square, turn)) {
            throw new IllegalArgumentException(INVALID_TURN);
        }
    }

    public GameResult getResult() {
        return new GameResult(board.getPieces());
    }

    public Map<Square, Piece> getBoardStatus() {
        return board.getPieces();
    }

    public long getRoomId() {
        return roomId;
    }
}
