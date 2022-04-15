package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;

public class Board {

    private static final int BOARD_START_INDEX = 0;
    private static final int BOARD_END_INDEX = 7;
    private static final double ANOTHER_PAWN_SCORE = 0.5;

    private final List<List<Piece>> board;

    public Board(List<List<Piece>> board) {
        this.board = board;
    }

    public void init() {
        for (BoardSetting boardSetting : BoardSetting.values()) {
            fillPieces(boardSetting);
        }
    }

    private void fillPieces(BoardSetting boardSetting) {
        Piece piece = boardSetting.getPiece();
        List<Position> positions = boardSetting.getPositions();

        for (Position position : positions) {
            place(position, piece);
        }
    }

    public void movePiece(Position source, Position target) {
        place(target, findPiece(source));
        place(source, new EmptyPiece());
    }

    public void place(Position position, Piece piece) {
        board.get(position.getRankIndex()).set(position.getFileIndex(), piece);
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
        for (int fileIndex = BOARD_START_INDEX; fileIndex <= BOARD_END_INDEX; fileIndex++) {
            int pawnCount = countPawn(color, fileIndex);
            pawnScore = calculatePawnScore(pawnScore, pawnCount);
        }
        return pawnScore;
    }

    private int countPawn(Color color, int fileIndex) {
        int pawnCount = 0;
        for (int rankIndex = BOARD_START_INDEX; rankIndex <= BOARD_END_INDEX; rankIndex++) {
            Piece piece = findPiece(rankIndex, fileIndex);
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

    private double calculatePawnScore(double pawnScore, int pawnCount) {
        if (pawnCount > 1) {
            pawnScore += pawnCount * ANOTHER_PAWN_SCORE;
        }
        return pawnScore;
    }

    public Piece findPiece(Position position) {
        return board.get(position.getRankIndex()).get(position.getFileIndex());
    }

    public Piece findPiece(int rankIndex, int fileIndex) {
        return board.get(rankIndex).get(fileIndex);
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
