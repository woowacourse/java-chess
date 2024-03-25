package domain.game;

import domain.position.File;
import domain.position.Position;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void movePiece(final TeamColor teamColor, final Position source, final Position destination) {
        validateMoveRequest(teamColor, source, destination);

        boolean caughtEnemy = isPieceExist(destination);
        Piece piece = chessBoard.get(source);
        chessBoard.put(destination, piece);
        chessBoard.remove(source);
    }

    private void validateMoveRequest(TeamColor teamColor, Position source, Position destination) {
        validateEmpty(source);
        validateColor(teamColor, source);
        Piece piece = chessBoard.get(source);
        validateReachability(source, destination, piece);
        validateDestinationPiece(teamColor, destination);
    }

    private void validateEmpty(final Position source) {
        if (!chessBoard.containsKey(source)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
    }

    private void validateColor(final TeamColor teamColor, final Position source) {
        if (!chessBoard.get(source).hasColor(teamColor)) {
            throw new IllegalArgumentException("차례가 맞지 않습니다.");
        }
    }

    private void validateReachability(Position source, Position destination, Piece piece) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("출발지와 도착지가 동일합니다.");
        }
        if (!piece.isMovable(source, destination, generatePiecePositions(source))) {
            throw new IllegalArgumentException("이동 위치까지 이동할 수 없습니다.");
        }
    }

    private Set<Position> generatePiecePositions(final Position excluded) {
        return chessBoard.keySet().stream()
                .filter(key -> !key.equals(excluded))
                .collect(Collectors.toSet());
    }

    private void validateDestinationPiece(TeamColor teamColor, Position destination) {
        if (isAllyPieceExistOnDestination(teamColor, destination)) {
            throw new IllegalArgumentException("이동 위치에 아군 기물이 존재합니다.");
        }
    }

    private boolean isAllyPieceExistOnDestination(TeamColor teamColor, Position destination) {
        return isPieceExist(destination) && (chessBoard.get(destination).hasColor(teamColor));
    }

    private boolean isPieceExist(Position position) {
        return chessBoard.containsKey(position);
    }

    public double calculateScoreOf(TeamColor teamColor) {
        Map<Position, Piece> teamPieces = chessBoard.entrySet().stream()
                .filter(entry -> entry.getValue().hasColor(teamColor))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        double totalPieceValue = teamPieces.values().stream()
                .mapToDouble(Piece::value)
                .sum();

        Map<File, Long> pawnCounts = teamPieces.entrySet().stream()
                .filter(entry -> entry.getValue().isPawn())
                .collect(Collectors.groupingBy(entry -> entry.getKey().file(), Collectors.counting()));

        int duplicatedPawnCount = pawnCounts.values().stream()
                .filter(count -> count > 1)
                .mapToInt(Long::intValue)
                .sum();

        return totalPieceValue - (duplicatedPawnCount * 0.5);
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
