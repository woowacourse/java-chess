package domain.game;

import domain.piece.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private static final double DUPLICATE_PAWN_SCORE = 0.5d;

    private final Map<Position, Piece> chessBoard;

    public Board(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        movePiece(sourcePosition, targetPosition, sourcePiece);
    }

    public boolean isMovable(Position sourcePosition, Position targetPosition, Side side) {
        Piece sourcePiece = chessBoard.get(sourcePosition);
        validateSourcePositionIsEmpty(sourcePosition, sourcePiece);
        validateTurn(side, sourcePiece);
        validateIsMovable(sourcePosition, targetPosition);
        validatePathIncludeAnyPiece(sourcePosition, targetPosition, sourcePiece);
        return true;
    }

    public boolean isKing(Position targetPosition) {
        return chessBoard.get(targetPosition).isSameType(PieceType.KING);
    }

    public Map<Side, Double> calculateScore() {
        Map<Side, Double> scores = new EnumMap<>(Side.class);

        List<Piece> whitePieces = collectPiecesBySide(Side.WHITE);
        List<Piece> blackPieces = collectPiecesBySide(Side.BLACK);

        scores.put(Side.WHITE, calculateScoreOneSidePieces(whitePieces) - calculateMinusScoreBySameRankPawn(Side.WHITE));
        scores.put(Side.BLACK, calculateScoreOneSidePieces(blackPieces) - calculateMinusScoreBySameRankPawn(Side.BLACK));

        return scores;
    }

    public Side calculateWinner() {
        Map<Side, Double> scores = calculateScore();
        double whiteScore = scores.get(Side.WHITE);
        double blackScore = scores.get(Side.BLACK);

        return Side.calculateWinner(whiteScore, blackScore);
    }

    private void validateTurn(Side side, Piece sourcePiece) {
        if (sourcePiece.isIncorrectTurn(side)) {
            throw new IllegalArgumentException("다른 진영의 말은 움직일 수 없습니다.");
        }
    }

    private void validateSourcePositionIsEmpty(Position sourcePosition, Piece sourcePiece) {
        if (sourcePiece.isEmptyPiece()) {
            throw new IllegalArgumentException(sourcePosition + "에 움직일 수 있는 말이 없습니다.");
        }
    }

    private void validatePathIncludeAnyPiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        List<Position> path = sourcePiece.collectPath(sourcePosition, targetPosition);
        for (Position position : path) {
            checkIsExistAnyPieceOn(position);
        }
    }

    private void checkIsExistAnyPieceOn(Position position) {
        if (!this.chessBoard.get(position).isEmptyPiece()) {
            throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
        }
    }

    private void validateIsMovable(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        Piece targetPiece = this.chessBoard.get(targetPosition);
        if (!sourcePiece.isMovable(targetPiece, sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("올바른 움직임이 아닙니다.");
        }
    }

    private void movePiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        this.chessBoard.put(sourcePosition, new EmptyPiece());
        this.chessBoard.put(targetPosition, sourcePiece);
    }

    private List<Piece> collectPiecesBySide(Side side) {
        return chessBoard.values().stream()
                .filter(piece -> piece.isSameSide(side))
                .collect(Collectors.toList());
    }

    private double calculateScoreOneSidePieces(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::score)
                .sum();
    }

    private double calculateMinusScoreBySameRankPawn(Side side) {
        Map<File, Long> duplicatePawns = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> Position.of(file.getText(), rank.getText())))
                .filter(position -> chessBoard.get(position).isSameSide(side) && chessBoard.get(position).isSameType(PieceType.PAWN))
                .collect(Collectors.groupingBy(Position::getFile, Collectors.counting()));

        return duplicatePawns.values().stream()
                .filter(duplicatePawnCount -> duplicatePawnCount > 1)
                .mapToDouble(duplicatePawnCount -> duplicatePawnCount * DUPLICATE_PAWN_SCORE)
                .sum();
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
