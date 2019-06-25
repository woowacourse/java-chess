package model.game;

import model.board.Hand;
import model.board.Board;
import model.board.Position;
import model.piece.Pawn;
import model.piece.Piece;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final Turn turn = new Turn();
    private final Board board = new Board();
    private Map<Player, Hand> hands = new HashMap<Player, Hand>() {{
        put(Player.WHITE, new Hand(Player.WHITE, board));
        put(Player.BLACK, new Hand(Player.BLACK, board));
    }};

    public Game() {}

    private Turn endTurn() {
        return this.turn.endTurn();
    }

    public boolean isOwnPiece(Position position) {
        return this.board.getColorOfPieceAt(position)
                        .map(color -> color == this.turn.team())
                        .orElse(false);
    }

    public List<Position> getPossiblePositions(Position from) {
        return this.board.getPieceAt(from)
                        .map(p -> hands.get(this.turn.team()).getPossibleDestinations(from))
                        .orElse(new ArrayList<>());
    }

    public double getCurrentScore(Player team) {
        return this.board.getPieces()
                        .filter(p -> p.team() == team)
                        .filter(p -> !p.isPawn())
                        .mapToDouble(Piece::getScore)
                        .sum()
                        + getPawnScore(team);
    }

    private double getPawnScore(Player team) {
        return this.board.getPieces()
                        .filter(p -> p.team() == team)
                        .filter(p -> p.isPawn())
                        .map(p -> (Pawn) p)
                        .collect(Collectors.groupingBy(Piece::x))
                        .values().stream()
                        .flatMap(l ->
                                (l.size() == 1) ? l.stream().map(Pawn::getScore) : l.stream().map(Pawn::getHalfScore)
                        ).reduce(Double::sum)
                        .orElse(.0);
    }

    public Turn turn() {
        return this.turn;
    }

    public Board board() {
        return this.board;
    }
}