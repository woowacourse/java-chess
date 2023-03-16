package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class ChessGame {

    private final Board board;
    private Color color;

    public ChessGame(Board board) {
        this.board = board;
        this.color = Color.WHITE;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);
        validateColor(color, sourcePiece);
        validateCanMove(sourcePosition, targetPosition, sourcePiece, targetPiece);
        List<Position> path = sourcePosition.findPath(targetPosition);
        validatePath(path);
        board.movePiece(sourcePosition, targetPosition);
        this.color = color.getReverseColor();
    }


    private void validateColor(Color color, Piece sourcePiece) {
        if (!sourcePiece.isSameTeam(color)) {
            throw new IllegalArgumentException("상대 팀의 말을 옮길 수 없습니다.");
        }
    }

    private void validatePath(List<Position> path) {
        if (!board.isEmptyPosition(path)) {
            throw new IllegalArgumentException("경로가 없습니다.");
        }
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition, Piece sourcePiece,
                                 Piece targetPiece) {
        if (!sourcePiece.canMove(sourcePosition, targetPosition, targetPiece.getColor())) {
            throw new IllegalArgumentException("잘못된 위치를 지정하셨습니다.");
        }
    }

    public Board getBoard() {
        return board;
    }
}
