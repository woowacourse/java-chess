package model.board;

import model.game.Color;
import model.piece.King;
import model.piece.Pawn;
import model.piece.Piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {
    private enum TileState {
        OWN,
        ENEMY,
        EMPTY
    }
    private final Color owner;

    private final Board board;
    public Hand(final Color owner, final Board board) {
        this.owner = Optional.ofNullable(owner).orElseThrow(IllegalArgumentException::new);
        this.board = Optional.ofNullable(board).orElseThrow(IllegalArgumentException::new);
    }

    public boolean tryToMoveFromTo(final Position src, final Position dest) {
        return getPossibleDestinations(src).contains(dest) && this.board.movePieceFromTo(src, dest);
    }

    public boolean isOwnPiece(final Position position) {
        return testPieceAtPosition(position, x -> x == this.owner);
    }

    public boolean isEnemyPiece(final Position position) {
        return testPieceAtPosition(position, x -> x != this.owner);
    }

    private boolean testPieceAtPosition(final Position position, final Predicate condition) {
        return this.board.getColorOfPieceAt(position)
                        .map(color -> condition.test(color))
                        .orElse(false);
    }

    public List<Position> getPossibleDestinations(final Position src) {
        return this.board.getPieceAt(src).map(p -> {
            if (p.team() != this.owner) {
                return new ArrayList<Position>();
            }
            if (p.isPawn()) {
                return getPossibleDestinationsOfPawn((Pawn) p);
            }
            if (p.isKing()) {
                return getPossibleDestinationsOfKing((King) p);
            }
            return collectEveryPossibleDestinations(p).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    private List<Position> getPossibleDestinationsOfPawn(final Pawn pawn) {
        return Stream.concat(
                pawn.possibleDiagonalDestinations()
                    .flatMap(pos -> isEnemyPiece(pos) ? Stream.of(pos) : Stream.empty()),
                collectEveryPossibleDestinations(pawn)
        ).collect(Collectors.toList());
    }

    private List<Position> getPossibleDestinationsOfKing(final King king) {
        return collectEveryPossibleDestinations(king).collect(Collectors.toList());
    }

    private Stream<Position> collectEveryPossibleDestinations(final Piece src) {
        return src.getIteratorsOfPossibleDestinations()
                    .map(this::proceedUntilBlocked)
                    .reduce(Stream.empty(), Stream::concat);
    }

    private Stream<Position> proceedUntilBlocked(final Iterator<Position> i) {
        final List<Position> result = new ArrayList<>();
        while (i.hasNext()) {
            final Position target = i.next();
            final TileState state = isOwnPiece(target)
                                    ? TileState.OWN
                                    : (isEnemyPiece(target) ? TileState.ENEMY : TileState.EMPTY);
            if (state == TileState.OWN) {
                break;
            }
            if (state == TileState.ENEMY) {
                result.add(target);
                break;
            }
            if (state == TileState.EMPTY) {
                result.add(target);
            }
        }
        return result.stream();
    }
}