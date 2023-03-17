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
        validate(color, sourcePosition);
        move(sourcePosition, targetPosition);
    }

    private void move(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition);
        this.color = color.getReverseColor();
    }


    private void validate(Color color, Position sourcePosition) {
        Piece sourcePiece = board.findPiece(sourcePosition);
        validateEmpty(sourcePiece);
        validateColor(color, sourcePiece);
    }

    private void validateEmpty(Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
    }

    private static void validateColor(Color color, Piece sourcePiece) {
        if (!sourcePiece.isSameTeam(color)) {
            throw new IllegalArgumentException("상대 팀의 말을 옮길 수 없습니다.");
        }
    }

    public Board getBoard() {
        return board;
    }
}
