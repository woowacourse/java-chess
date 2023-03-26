package chess.domain.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.exception.IllegalMoveException;
import chess.domain.game.Team;
import chess.domain.move.Move;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;

public class Board {

    private static final EmptyPiece EMPTY_PIECE = EmptyPiece.create();

    private final Map<Position, Piece> pieces;

    protected Board(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public void move(Position source, Position target) {
        validateNotSameTeam(source, target);
        validateMove(source, target);
        validateNoObstacle(source, target);
        makeMove(source, target);
    }

    private void validateNotSameTeam(Position source, Position target) {
        if (getPieceAt(source).isSameTeamWith(getPieceAt(target))) {
            throw new IllegalMoveException("목표 위치에 같은 색 말이 있습니다");
        }
    }

    private void validateMove(Position source, Position target) {
        if (!hasMove(source, target)) {
            throw new IllegalMoveException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private boolean hasMove(Position source, Position target) {
        Move move = Move.of(source, target);
        if (hasPieceAt(target)) {
            return getPieceAt(source).hasAttackMove(move);
        }
        return getPieceAt(source).hasMove(move);
    }

    private void validateNoObstacle(Position source, Position target) {
        Move unitMove = Move.of(source, target).getUnitMove();
        Position current = unitMove.move(source);
        while (!current.equals(target)) {
            validateNoPieceAt(current);
            current = unitMove.move(current);
        }
    }

    private void validateNoPieceAt(Position position) {
        if (hasPieceAt(position)) {
            throw new IllegalMoveException("다른 기물을 지나칠 수 없습니다");
        }
    }

    private void makeMove(Position source, Position target) {
        pieces.put(target, getPieceAt(source).touch());
        pieces.remove(source);
    }

    public boolean hasPositionTeamOf(Position position, Team team) {
        return getPieceAt(position).hasTeam(team);
    }

    private boolean hasPieceAt(Position target) {
        return !getPieceAt(target).isEmpty();
    }

    private Piece getPieceAt(Position position) {
        return pieces.getOrDefault(position, EMPTY_PIECE);
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }

    public double score(Team team) {
        return Arrays.stream(File.values())
                .mapToDouble(file -> score(team, file))
                .sum();
    }

    private double score(Team team, File file) {
        List<Piece> pieces = findPiecesIn(file).stream()
                .filter(piece -> piece.hasTeam(team))
                .collect(Collectors.toList());
        return scoreExceptPawns(pieces) + scorePawns(pieces);
    }

    private List<Piece> findPiecesIn(File file) {
        return pieces.entrySet().stream()
                .filter(it -> it.getKey().hasFile(file))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private double scoreExceptPawns(List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> !piece.getType().equals(PieceType.PAWN))
                .mapToDouble(Piece::score)
                .sum();
    }

    private double scorePawns(List<Piece> pieces) {
        List<Piece> pawns = findPawnsIn(pieces);
        double score = pawns.stream()
                .mapToDouble(Piece::score)
                .sum();
        if (pawns.size() > 1) {
            return score / 2;
        }
        return score;
    }

    private List<Piece> findPawnsIn(List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> piece.getType().equals(PieceType.PAWN))
                .collect(Collectors.toList());
    }
}
