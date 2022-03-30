package chess.domain;

import chess.domain.generator.BoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ChessBoard {

    private static final int DEFAULT_KING_COUNT = 2;

    private final Board board;

    public ChessBoard(BoardGenerator boardGenerator) {
        this.board = new Board(boardGenerator.generate());
    }

    public void move(String sourceInput, String targetInput) {
        Position source = new Position(sourceInput);
        Position target = new Position(targetInput);
        validateSamePosition(source, target);

        Piece sourcePiece = board.findPiece(source);
        validateEmptyPiece(sourcePiece);
        sourcePiece.validateMove(board, source, target);

        board.shift(source, target);
    }

    private void validateSamePosition(Position source, Position target) {
        if (source.equals(target)) {
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
        return board.calculateKingCount() != DEFAULT_KING_COUNT;
    }

    public Board getBoard() {
        return board;
    }
}
