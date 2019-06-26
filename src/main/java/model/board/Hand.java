package model.board;

import model.game.Player;
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
        return getPossibleDestinations(src).contains(dest) ? this.board.movePieceFromTo(src, dest) : false;
    }

    public List<Position> getPossibleDestinations(final Position src) {
        final Optional<Piece> pieceAtSrc = this.board.getPieceAt(src);
        if (pieceAtSrc.map(Piece::isPawn).orElse(false)) {
            return getPossibleDestinationsOfPawn((Pawn) pieceAtSrc.get());
        }
        return pieceAtSrc.map(p ->
                        p.getIteratorsOfPossibleDestinations()
                        .map(i -> proceedUntilBlocked(i))
                        .reduce(Stream::concat)
                        .orElse(Stream.empty())
                        .collect(Collectors.toList())
        ).orElse(new ArrayList<>());
    }

    private List<Position> getPossibleDestinationsOfPawn(final Pawn pawn) {
        return Stream.concat(
            pawn.possibleDiagonalDestinations().map(pos ->
                    this.board.getColorOfPieceAt(pos)
                                .map(color -> (color != pawn.team()) ? pos : null)
            ).flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty)),
            pawn.possibleForwardDestinations().map(pos ->
                    this.board.getColorOfPieceAt(pos).isPresent() ? null : pos
            )
        ).collect(Collectors.toList());
    }

    private Stream<Position> proceedUntilBlocked(final Iterator<Position> i) {
        final List<Position> result = new ArrayList<>();
        while (i.hasNext()) {
            final Position target = i.next();
            final Optional<Player> color = this.board.getColorOfPieceAt(target);
            if (color.isPresent()) {
                if (color.get() == this.owner) {
                    break;
                }
                result.add(target);
                break;
            }
            result.add(target);
        }
        return result.stream();
    }

    //// TODO: 2019-06-26 refactor 2 of the above
}