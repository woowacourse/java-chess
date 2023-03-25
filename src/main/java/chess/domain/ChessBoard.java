package chess.domain;

import chess.domain.piece.PieceType;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private static final int NUMBER_OF_PLAYER = 2;

    private final List<Square> squares;
    private Turn turn;

    ChessBoard(List<Square> squares) {
        this.squares = squares;
        this.turn = new Turn();
    }

    public void move(final Position source, final Position destination) {
        validateAllyPiece(source);
        if (canEnPassant(source, destination)) {
            Square target = findEnPassantTarget(source, destination);
            target.removePiece();
            executeMove(source, destination);
            return;
        }
        validateNotExistAllyAt(destination);
        validateNotBlocked(source, destination);
        validateCanMove(source, destination);
        executeMove(source, destination);
    }

    private boolean canEnPassant(final Position source, final Position destination) {
        if (!findSquareByPosition(source).canAttack(destination)) {
            return false;
        }
        Square target = findEnPassantTarget(source, destination);
        return (target.findPieceType().equals(PieceType.PAWN)
            && target.isSoonMovedTwo(turn));
    }


    private void validateAllyPiece(final Position source) {
        if (findSquareByPosition(source).isSameTeam(turn.findCurrentEnemyTeam())) {
            throw new IllegalArgumentException("상대방의 기물은 이동시킬 수 없습니다.");
        }
    }

    private Square findSquareByPosition(final Position position) {
        return squares.stream()
            .filter(square -> square.isSamePosition(position))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("칸이 초기화되지 않았습니다."));
    }

    private Square findEnPassantTarget(final Position source, final Position destination) {
        Position target = Position.enPassantTargetPosition(source, destination);
        return squares.stream()
            .filter(square -> square.isSamePosition(target))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("칸이 초기화되지 않았습니다."));
    }

    private void validateNotExistAllyAt(final Position destination) {
        final Team team = turn.findCurrentTeam();
        if (isMyPiece(team, destination)) {
            throw new IllegalArgumentException("도착지에 아군 기물이 있습니다.");
        }
    }

    private boolean isMyPiece(final Team team, final Position destination) {
        return findSquareByPosition(destination).isSameTeam(team);
    }

    private void validateNotBlocked(final Position source, final Position destination) {
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);
        BigInteger gcd = BigInteger.valueOf(diffRank).gcd(BigInteger.valueOf(diffFile));
        int fileDirection = diffFile / gcd.intValue();
        int rankDirection = diffRank / gcd.intValue();
        Position tempPosition = source.move(fileDirection, rankDirection);
        while (!tempPosition.equals(destination)) {
            validateIsEmpty(tempPosition);
            tempPosition = tempPosition.move(fileDirection, rankDirection);
        }
    }

    private void validateIsEmpty(final Position tempPosition) {
        if (!isEmptyAt(tempPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private boolean isEmptyAt(final Position position) {
        return findSquareByPosition(position)
            .isEmpty();
    }

    private void validateCanMove(Position source, Position destination) {
        if (!(canAttack(source, destination) || canMove(source, destination))) {
            throw new IllegalArgumentException("이동할 수 없는 좌표입니다.");
        }
    }

    private boolean canAttack(final Position source, final Position destination) {
        final Square userSquare = findSquareByPosition(source);
        final Square targetSquare = findSquareByPosition(destination);
        return targetSquare.isSameTeam(turn.findCurrentEnemyTeam())
            && userSquare.canAttack(destination);
    }

    private boolean canMove(final Position source, final Position destination) {
        return isEmptyAt(destination) &&
            findSquareByPosition(source).canMove(source, destination);
    }

    private void executeMove(final Position source, final Position destination) {
        findSquareByPosition(source).moveTo(turn, findSquareByPosition(destination));
        turn = turn.next();
    }

    public Team findWinner() {
        if (isAllKingAlive()) {
            return Team.EMPTY;
        }
        return turn.findCurrentEnemyTeam();
    }

    private boolean isAllKingAlive() {
        return squares.stream()
            .filter(Square::isKing)
            .count() == NUMBER_OF_PLAYER;
    }

    public Score calculateScoreByTeam(Team team) {
        return Arrays.stream(File.values())
            .map((file) -> calculateScoreByFileAndTeam(file, team))
            .reduce(Score.ZERO, Score::add);
    }

    private Score calculateScoreByFileAndTeam(File file, Team team) {
        Map<PieceType, Long> pieceCountBoard = squares.stream()
            .filter((square) -> square.isSameFileAndTeam(file, team))
            .collect(Collectors.groupingBy(Square::findPieceType, Collectors.counting()));

        return squares.stream()
            .filter((square) -> square.isSameFileAndTeam(file, team))
            .map((square) -> square.findPieceScore(pieceCountBoard))
            .reduce(Score.ZERO, Score::add);
    }

    public List<Square> getSquares() {
        return squares;
    }
}
