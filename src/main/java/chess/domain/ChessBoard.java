package chess.domain;

import chess.domain.generator.BoardGenerator;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;

public class ChessBoard {

    private final List<List<Piece>> board;

    public ChessBoard(BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
    }

    public void move(String source, String target) {
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);
        validateSamePosition(sourcePosition, targetPosition);

        Piece sourcePiece = findPiece(sourcePosition);
        sourcePiece.validateMove(board, sourcePosition, targetPosition);

        board.get(sourcePosition.getRankIndex()).set(sourcePosition.getFileIndex(), new EmptyPiece());
        board.get(targetPosition.getRankIndex()).set(targetPosition.getFileIndex(), sourcePiece);
    }

    private void validateSamePosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("source 위치와 target 위치는 같을 수 없습니다.");
        }
    }

    private Piece findPiece(Position sourcePosition) {
        int rankIndex = sourcePosition.getRankIndex();
        int fileIndex = sourcePosition.getFileIndex();
        Piece piece = board.get(rankIndex).get(fileIndex);
        validateEmptyPiece(piece);

        return piece;
    }

    private void validateEmptyPiece(Piece piece) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException("source 위치에 기물이 존재하지 않습니다.");
        }
    }

    public double calculateScore(Color color) {
        double score = board.stream()
                .flatMap(List::stream)
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();

        return score - getPawnScore(color);
    }

    private double getPawnScore(Color color) {
        double pawnScore = 0;
        for (int i = 0; i < 8; i++) {
            int pawnCount = getPawnCount(color, i);
            if (getPawnCount(color, i) > 1) {
                pawnScore += pawnCount * 0.5;
            }
        }
        return pawnScore;
    }

    private int getPawnCount(Color color, int fileIndex) {
        int pawnCount = 0;
        for (int j = 0; j < 8; j++) {
            Piece piece = board.get(j).get(fileIndex);
            pawnCount += calculatePawnCount(piece, color);
        }

        return pawnCount;
    }

    private int calculatePawnCount(Piece piece, Color color) {
        if (piece.isSamePieceType(PieceType.PAWN) && piece.isSameColor(color)) {
            return 1;
        }

        return 0;
    }

    public boolean isTurn(String source, Color color) {
        Piece sourcePiece = findPiece(new Position(source));
        return sourcePiece.isSameColor(color);
    }

    public boolean isFinished() {
        long count = board.stream()
                .flatMap(List::stream)
                .filter(piece -> piece.isSamePieceType(PieceType.KING))
                .count();

        return count != 2;
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
