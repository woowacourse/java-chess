package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ChessGame {

    private final Board board;
    private final Turn turn;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = new Turn();
    }

    public void movePieceTo(Position sourcePosition, Position targetPosition) {
        move(sourcePosition, targetPosition);
    }

    private void move(Position sourcePosition, Position targetPosition) {
        validateTurn(sourcePosition);
        board.movePiece(sourcePosition, targetPosition);
        turn.next();
    }

    private void validateTurn(Position sourcePosition) {
        Piece piece = board.findPiece(sourcePosition);
        if (turn.isNotCurrentTurn(piece.getColor())) {
            throw new IllegalArgumentException("상대 팀의 말을 옮길 수 없습니다.");
        }
    }

    public Board getBoard() {
        return board;
    }
}
