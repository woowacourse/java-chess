package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.domain.player.Players;
import chess.domain.player.Scores;
import chess.domain.player.Turn;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class ChessGame {
    private final Players players;
    private final Board board;

    private Turn turn;
    private boolean isGameEnd = false;

    private ChessGame(final Board board, final Turn turn) {
        this.board = board;
        this.players = Players.init(board);
        this.turn = turn;
    }

    public static ChessGame initNew() {
        return new ChessGame(BoardInitializer.initiateBoard(), Turn.WHITE);
    }

    public static ChessGame load(final Board board, final Turn turn){
        return new ChessGame(board, turn);
    }

    public void move(final Position source, final Position target) {
        validateTurn(source);
        board.movePiece(source, target);
        players.move(source, target);
        isGameEnd = players.anyKingDead(board);
        changeTurn();
    }

    public void move(final Map<String, String> request) {
        final Position source = new Position(request.get("source"));
        final Position target = new Position(request.get("target"));
        move(source, target);
    }

    public void validateTurn(final Position source) {
        turn.validate(players.ownerOf(source));
    }

    public void validateTurn(final Map<String, String> request) {
        validateTurn(new Position(request.get("source")));
    }

    public void changeTurn() {
        turn = turn.change();
    }

    public Scores scores() {
        return players.scores(board);
    }

    public List<String> reachablePositions(final Map<String, String> request) {
        final Position source = new Position(request.get("source"));
        return board.reachablePositions(source).stream()
                .map(position -> position.parseAsString())
                .collect(Collectors.toList());
    }

    public Board board() {
        return board;
    }

    public String[][] unicodeBoard() {
        return PieceSymbolMapper.parseBoardAsUnicode(board);
    }

    public void setGameEnd() {
        isGameEnd = true;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public Queue<Owner> winner() {
        return new LinkedList<>(scores().winner());
    }

    public double score(final Owner owner) {
        return scores().getValueOf(owner);
    }

    public Turn turn(){
        return turn;
    }
}
