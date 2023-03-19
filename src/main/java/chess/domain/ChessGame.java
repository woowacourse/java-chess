package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class ChessGame {

    private final Board board;
    private Color color;

    public ChessGame(Board board) {
        this.board = board;
        this.color = Color.WHITE;
    }

    public void movePieceTo(Position sourcePosition, Position targetPosition) {
        move(sourcePosition, targetPosition);
    }

    private void move(Position sourcePosition, Position targetPosition) {
        validateColor(color, sourcePosition);
        board.movePiece(sourcePosition, targetPosition);
        this.color = color.getReverseColor();
    }

    private void validateColor(Color color, Position sourcePosition) {
        Piece piece = board.findPiece(sourcePosition);
        if (!piece.isSameTeam(color)) {
            throw new IllegalArgumentException("상대 팀의 말을 옮길 수 없습니다.");
        }
    }

    public Board getBoard() {
        return board;
    }
}
