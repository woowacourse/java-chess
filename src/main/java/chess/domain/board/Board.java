package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.NoPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board generatedBy(BoardGenerator boardGenerator) {
        return new Board(boardGenerator.generate());
    }

    public void move(Position source, Position target, Color color) {
        Piece piece = board.get(source);
        validateMove(source, target, color);
        board.put(source, new NoPiece(Color.NO_COLOR));
        board.put(target, piece);
    }

    private void validateMove(Position source, Position target, Color color) {
        validateNoPieceAtSource(source);
        validateTurn(source, color);
        validatePieceRule(source, target);
    }

    private void validateNoPieceAtSource(Position source) {
        if (!findPieceAt(source).exists()) {
            throw new IllegalArgumentException("출발점에 말이 없습니다.");
        }
    }

    private void validateTurn(Position source, Color color) {
        if (findPieceAt(source).isNotColored(color)) {
            throw new IllegalArgumentException(String.format("%s이 움직일 차례입니다.", color.toString()));
        }
    }

    private void validatePieceRule(Position source, Position target) {
        if (findPieceAt(source).canMove(source, target, board)) {
            return;
        }
        throw new IllegalArgumentException("말의 규칙에 맞지 않는 이동입니다.");
    }

    public Piece findPieceAt(Position position) {
        return board.get(position);
    }
}
