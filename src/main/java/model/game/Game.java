package model.game;

import model.board.Board;
import model.board.Hand;
import model.board.Position;
import model.game.exception.FailedToRestoreGameException;
import model.piece.Pawn;
import model.piece.Piece;
import service.LogVO;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private final Turn turn = new Turn();
    private final Board board = new Board();
    private final Map<Color, Hand> hands = new HashMap<Color, Hand>() {{
        put(Color.WHITE, new Hand(Color.WHITE, board));
        put(Color.BLACK, new Hand(Color.BLACK, board));
    }};

    public Game() {}

    public Game(final List<LogVO> log) {
        if (
                Optional.ofNullable(log).map(l ->
                    l.stream()
                    .sorted()
                    .map(row -> restore(row.src(), row.dest()))
                    .anyMatch(x -> x == false)
                ).orElseThrow(IllegalArgumentException::new)
        ) {
            throw new FailedToRestoreGameException();
        }

    }

    public boolean isOwnPiece(final Position position) {
        return hands.get(this.turn.team()).isOwnPiece(position);
    }

    public List<Position> getPossibleDestinationsOf(final Position src) {
        return this.board.getPieceAt(src)
                        .map(p -> hands.get(this.turn.team()).getPossibleDestinations(src))
                        .orElse(new ArrayList<>());
    }

    private boolean tryToMoveFromTo(final Position src, final Position dest, final boolean isNotRestoring) {
        if (
                this.board.getPieceAt(src)
                            .map(p -> hands.get(this.turn.team()).tryToMoveFromTo(p.position(), dest))
                            .orElse(false)
        ) {
            if (isNotRestoring) {
                GameDAO.holdAndWriteLog(this.turn, src, dest);
            }
            endTurn();
            return true;
        }
        return false;
    }

    public boolean tryToMoveFromTo(final Position src, final Position dest) {
        return tryToMoveFromTo(src, dest, true);
    }

    public boolean restore(final Position src, final Position dest) {
        return tryToMoveFromTo(src, dest, false);
    }

    public double getCurrentScore(final Color team) {
        return this.board.getPieces()
                        .filter(p -> !p.isPawn())
                        .filter(p -> p.team() == team)
                        .mapToDouble(Piece::getScore)
                        .sum()
                        + getPawnScore(team);
    }

    private double getPawnScore(final Color team) {
        return this.board.getPieces()
                        .filter(p -> p.isPawn())
                        .filter(p -> p.team() == team)
                        .map(p -> (Pawn) p)
                        .collect(Collectors.groupingBy(Piece::x))
                        .values().stream()
                        .flatMap(l -> getPawnScorePerColumn(l))
                        .reduce(.0, Double::sum);
    }

    private Stream<Double> getPawnScorePerColumn(final List<Pawn> pawns) {
        return (pawns.size() == 1) ? pawns.stream().map(Pawn::getScore) : pawns.stream().map(Pawn::getHalfScore);
    }

    private Turn endTurn() {
        return this.turn.endTurn();
    }

    public Turn turn() {
        return this.turn;
    }

    public Board board() {
        return this.board;
    }
}