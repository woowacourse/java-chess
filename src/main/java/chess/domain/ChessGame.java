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
        validateMove(sourcePosition, targetPosition);
        move(sourcePosition, targetPosition);
    }

    private void validateMove(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);
        validateColor(color, sourcePiece);
        validateCanMove(sourcePosition, targetPosition, sourcePiece, targetPiece);
        List<Position> path = sourcePosition.findPath(targetPosition);
        validatePath(path);
    }

    private void move(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition);
        this.color = color.getReverseColor();
    }


    private void validateColor(Color color, Piece sourcePiece) {
        if (!sourcePiece.isSameTeam(color)) {
            throw new IllegalArgumentException("본인의 말만 옮길 수 있습니다.");
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
            throw new IllegalArgumentException("잘못된 위치를 입력하셨습니다.");
        }
    }

    public Board getBoard() {
        return board;
    }
}
