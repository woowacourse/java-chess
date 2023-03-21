package chess.model.game;

import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardFactory;
import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.view.ChessBoardResponse;
import java.util.Map;

public class ChessGame {

    private ChessBoard chessBoard;
    private Turn turn;

    public void initialChessGame() {
        this.chessBoard = ChessBoardFactory.create();
        this.turn = new Turn();
    }

    public void move(final Position source, final Position target, final Camp camp) {
        validateInitialChessGame();
        validatePosition(source, target);
        chessBoard.move(source, target, camp);
    }

    private void validateInitialChessGame() {
        if (chessBoard == null || turn == null) {
            throw new IllegalStateException("게임을 진행할 수 없는 상태입니다.");
        }
    }

    private void validatePosition(final Position source, final Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("동일한 위치로 기물을 이동시킬 수 없습니다.");
        }
    }

    public ChessBoardResponse getChessBoard() {
        final Map<Position, Piece> square = chessBoard.getBoard();

        return new ChessBoardResponse(square);
    }
}
