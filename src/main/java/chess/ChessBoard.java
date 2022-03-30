package chess;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    public static final String SAME_FILE_PAWN_SCORE = "0.5";

    private final Map<Position, Piece> board;

    public ChessBoard(List<Piece> pieces) {
        this.board = pieces.stream()
            .collect(Collectors.toMap(Piece::getPosition, piece -> piece));
    }

    public static ChessBoard createChessBoard() {
        return new ChessBoard(BoardInitializer.init());
    }

    public void move(Position from, Position to, Color color) {
        if (!isCurrentColorPiece(from, color)) {
            throw new IllegalArgumentException(String.format(
                "%s 색깔의 기물을 움직일 수 있습니다.", color));
        }
        movePickedPiece(from, to);
    }

    private boolean isCurrentColorPiece(Position position, Color color) {
        Piece piece = findPieceByPosition(position);
        return piece.isSameColor(color);
    }

    private Piece findPieceByPosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다.", position));
        }
        return board.get(position);
    }

    private void movePickedPiece(Position from, Position to) {
        Piece pickedPiece = findPieceByPosition(from);
        Piece transferredPiece = pickedPiece.transfer(to, getPieces());

        board.remove(from);
        board.put(to, transferredPiece);
    }

    public boolean isGameEnd() {
        return !hasKingByColor(Color.WHITE) || !hasKingByColor(Color.BLACK);
    }

    private boolean hasKingByColor(Color color) {
        return getPieces().stream()
            .filter(Piece::isKing)
            .anyMatch(piece -> piece.isSameColor(color));
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }

    public BigDecimal getScore(Color color) {
        return getDefaultScore(color).subtract(getDeductionOfPawn(color));
    }

    private BigDecimal getDefaultScore(Color color) {
        return getPieces().stream()
            .filter(piece -> piece.isSameColor(color))
            .map(Piece::getPoint)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getDeductionOfPawn(Color color) {
        return new BigDecimal(SAME_FILE_PAWN_SCORE).multiply(new BigDecimal(numberOfPawn(color)));
    }

    private long numberOfPawn(Color color) {
        return Arrays.stream(File.values())
            .mapToLong(file -> numberOfPawnEachFile(color, file))
            .filter(numberOfPawn -> numberOfPawn > 1)
            .sum();
    }

    private long numberOfPawnEachFile(Color color, File file) {
        return getPieces().stream()
            .filter(Piece::isPawn)
            .filter(piece -> piece.isSameColor(color))
            .filter(piece -> piece.isSameFile(file))
            .count();
    }

    public Color getWinner() {
        if (!isGameEnd()) {
            throw new IllegalStateException("체스 게임이 종료되지 않았습니다.");
        }
        if (hasKingByColor(Color.WHITE)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
