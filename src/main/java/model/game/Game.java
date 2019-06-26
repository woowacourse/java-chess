package model.game;

import model.board.Board;
import model.board.Hand;
import model.board.Position;
import model.piece.Pawn;
import model.piece.Piece;
import service.LogVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    private final Turn turn = new Turn();
    private final Board board = new Board();
    private final Map<Player, Hand> hands = new HashMap<Player, Hand>() {{
        put(Player.WHITE, new Hand(Player.WHITE, board));
        put(Player.BLACK, new Hand(Player.BLACK, board));
    }};

    public Game() {}

    public Game(final List<LogVO> log) {
        Collections.sort(log);
        log.forEach(l -> {
            if (!restore(l.from(), l.to())) {
                throw new FailedToRestoreGameException();
            }
        });
    }

    public boolean isOwnPiece(final Position position) {
        return this.board.getColorOfPieceAt(position)
                        .map(color -> color == this.turn.team())
                        .orElse(false);
    }

    public List<Position> getPossibleDestinationsOf(final Position src) {
        return this.board.getPieceAt(src)
                        .map(p -> hands.get(this.turn.team()).getPossibleDestinations(src))
                        .orElse(new ArrayList<>());
    }

    private boolean tryToMoveFromTo(final Position src, final Position dest, final boolean isNotRestoring) {
        if (this.board.getPieceAt(src)
                        .map(p -> hands.get(this.turn.team()).tryToMoveFromTo(p.position(), dest))
                        .orElse(false)) {
            if (isNotRestoring) {
                GameDAO.holdAndWriteLog(turn, src, dest);
            }
            endTurn();
            return true;
        }
        return false;
    }

    //// TODO: 2019-06-26 i kinda don't like somethin bout this

    public boolean tryToMoveFromTo(final Position src, final Position dest) {
        return tryToMoveFromTo(src, dest, true);
    }

    public boolean restore(final Position src, final Position dest) {
        return tryToMoveFromTo(src, dest, false);
    }

    public double getCurrentScore(final Player team) {
        return this.board.getPieces()
                        .filter(p -> !p.isPawn())
                        .filter(p -> p.team() == team)
                        .mapToDouble(Piece::getScore)
                        .sum()
                        + getPawnScore(team);
    }

    private double getPawnScore(final Player team) {
        return this.board.getPieces()
                        .filter(p -> p.isPawn())
                        .filter(p -> p.team() == team)
                        .map(p -> (Pawn) p)
                        .collect(Collectors.groupingBy(Piece::x))
                        .values().stream()
                        .flatMap(l ->
                                (l.size() == 1) ? l.stream().map(Pawn::getScore) : l.stream().map(Pawn::getHalfScore)
                        ).reduce(Double::sum)
                        .orElse(.0);
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