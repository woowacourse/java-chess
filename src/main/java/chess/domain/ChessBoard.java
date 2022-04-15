package chess.domain;

import chess.domain.generator.BoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;

public class ChessBoard {

    private static final int DEFAULT_KING_COUNT = 2;

    private final Board board;

    public ChessBoard(BoardGenerator boardGenerator) {
        this(boardGenerator.generate());
    }

    public ChessBoard(Board board) {
        this.board = board;
    }

    public void init() {
        board.init();
    }

    public void move(String source, String target) {
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        validateSamePosition(sourcePosition, targetPosition);

        Piece sourcePiece = board.findPiece(sourcePosition);
        validateEmptyPiece(sourcePiece);
        sourcePiece.validateMove(board, sourcePosition, targetPosition);

        board.movePiece(sourcePosition, targetPosition);
    }

    private void validateSamePosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("source 위치와 target 위치는 같을 수 없습니다.");
        }
    }

    private void validateEmptyPiece(Piece piece) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException("source 위치에 기물이 존재하지 않습니다.");
        }
    }

    public double calculateScore(Color color) {
        return board.calculateScore(color);
    }

    public boolean isTurn(String source, Color color) {
        Piece sourcePiece = board.findPiece(new Position(source));
        return sourcePiece.isSameColor(color);
    }

    public boolean isFinished() {
        long count = board.getBoard().stream()
                .flatMap(List::stream)
                .filter(piece -> piece.isSamePieceType(PieceType.KING))
                .count();

        return count != DEFAULT_KING_COUNT;
    }

    public Board getBoard() {
        return board;
    }
}
