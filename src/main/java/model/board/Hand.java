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

    public Hand(Player owner, Board board) {
        this.owner = Optional.ofNullable(owner)
                .orElseThrow(IllegalArgumentException::new);
        this.board = Optional.ofNullable(board)
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean tryToMoveFromTo(Position from, Position to) {
        if (getPossibleDestinations(from).contains(to)) {
            return this.board.movePieceFromTo(from, to);
        }
        return false;
    }

    public List<Position> getPossibleDestinations(Position from) {
        Optional<Piece> source = this.board.getPieceAt(from);
        if (source.map(Piece::isPawn).orElse(false)) {
            return getPossibleDestinationsOfAPawn((Pawn) source.get());
        }
        return source.map(p ->
                p.findPossiblePositions()
                .map(i -> proceedUntilBlocked(i, this.board))
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .collect(Collectors.toList())
        ).orElse(new ArrayList<>());
    }

    private List<Position> getPossibleDestinationsOfAPawn(Pawn pawn) {
        return Stream.concat(
            pawn.possibleDiagonalPositions().map(pos ->
                    this.board.getColorOfPieceAt(pos)
                                .map(color -> (color != pawn.team()) ? pos : null)
            ).flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty)),
            pawn.possibleForwardPositions().map(pos ->
                    this.board.getColorOfPieceAt(pos).isPresent() ? null : pos
            )
        ).collect(Collectors.toList());
    }

    private Stream<Position> proceedUntilBlocked(Iterator<Position> i, Board board) {
        List<Position> result = new ArrayList<>();
        while (i.hasNext()) {
            Position target = i.next();
            Optional<Player> color = board.getColorOfPieceAt(target);
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
}