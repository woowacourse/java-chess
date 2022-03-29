package chess.domain.chessboard;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.Pawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, ChessPiece> pieceByPosition;

    ChessBoard(final Map<Position, ChessPiece> pieceByPosition) {
        this.pieceByPosition = pieceByPosition;
    }

    public Optional<ChessPiece> findPiece(final Position position) {
        final ChessPiece piece = pieceByPosition.get(position);
        return Optional.ofNullable(piece);
    }

    public void move(final Position from, final Position to) {
        final ChessPiece movablePiece = findPiece(from)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."));

        checkCanMove(from, to, movablePiece);
        movePiece(from, to);
    }

    private void checkCanMove(final Position from, final Position to, final ChessPiece movablePiece) {
        movablePiece.checkMovablePosition(from, to, findPiece(to));
        checkHurdle(from, to, movablePiece);
    }

    private void checkHurdle(final Position from, final Position to, final ChessPiece movablePiece) {
        final boolean hurdleExist = movablePiece.findRoute(from, to).stream()
                .map(this::findPiece)
                .anyMatch(Optional::isPresent);

        if (hurdleExist) {
            throw new IllegalArgumentException("이동 경로 사이에 다른 기물이 있습니다.");
        }
    }

    private void movePiece(final Position from, final Position to) {
        final ChessPiece movablePiece = pieceByPosition.remove(from);
        pieceByPosition.put(to, movablePiece);
    }

    public boolean isKingDie(final Position to) {
        final ChessPiece chessPiece = pieceByPosition.get(to);
        if (Objects.isNull(chessPiece)) {
            return false;
        }
        return chessPiece.isKing();
    }

    public Map<Color, Double> calculateScore() {
        return Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        color -> sumScoreExceptPawn(color) + sumPawnScore(color)));
    }

    private double sumScoreExceptPawn(final Color color) {
        return pieceByPosition.values().stream()
                .filter(chessPiece -> chessPiece.isSameColor(color))
                .filter(chessPiece -> !(chessPiece instanceof Pawn))
                .mapToDouble(ChessPiece::value)
                .sum();
    }

    private double sumPawnScore(final Color color) {
        return Arrays.stream(Rank.values())
                .mapToInt(rank -> countSameRankPawn(color, rank))
                .mapToDouble(Pawn::calculateScore)
                .sum();
    }

    private int countSameRankPawn(final Color color, final Rank rank) {
        return (int) Arrays.stream(File.values())
                .map(file -> findPiece(Position.of(rank, file)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(chessPiece -> chessPiece instanceof Pawn)
                .filter(pawn -> pawn.isSameColor(color))
                .count();
    }

    public Map<Position, ChessPiece> findAllPiece() {
        return pieceByPosition;
    }
}
