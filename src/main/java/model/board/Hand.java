package model.board;

import model.game.Player;
import model.piece.King;
import model.piece.Pawn;
import model.piece.Piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {
    private final Player owner;
    private final Board board;

    public Hand(final Player owner, final Board board) {
        this.owner = Optional.ofNullable(owner).orElseThrow(IllegalArgumentException::new);
        this.board = Optional.ofNullable(board).orElseThrow(IllegalArgumentException::new);
    }

    public boolean tryToMoveFromTo(final Position src, final Position dest) {
        return getPossibleDestinations(src).contains(dest) && this.board.movePieceFromTo(src, dest);
    }

    public List<Position> getPossibleDestinations(final Position src) {
        return this.board.getPieceAt(src).map(p -> {
            if (p.isPawn()) {
                return getPossibleDestinationsOfPawn((Pawn) p);
            }
            if (p.isKing()) {
                return collectEveryPossibleDestinations((King) p).collect(Collectors.toList());
            }
            return collectEveryPossibleDestinations(p).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    private List<Position> getPossibleDestinationsOfPawn(final Pawn pawn) {
        return Stream.concat(
                pawn.possibleDiagonalDestinations()
                    .flatMap(pos -> (this.board.getColorOfPieceAt(pos).map(color -> color != pawn.team()).orElse(false))
                                    ? Stream.of(pos)
                                    : Stream.empty()
                    ),
                collectEveryPossibleDestinations(pawn)
        ).collect(Collectors.toList());
    }

    private Stream<Position> collectEveryPossibleDestinations(final Piece src) {
        return src.getIteratorsOfPossibleDestinations()
                    .map(i -> proceedUntilBlocked(i))
                    .reduce(Stream::concat)
                    .orElseGet(Stream::empty);
    }

    private Stream<Position> proceedUntilBlocked(final Iterator<Position> i) {
        final List<Position> result = new ArrayList<>();
        while (i.hasNext()) {
            final Position target = i.next();
            final TileState state = this.board.getColorOfPieceAt(target)
                                                .map(color -> (color == this.owner) ? TileState.OWN : TileState.ENEMY)
                                                .orElse(TileState.EMPTY);
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

enum TileState {
    OWN,
    ENEMY,
    EMPTY
}