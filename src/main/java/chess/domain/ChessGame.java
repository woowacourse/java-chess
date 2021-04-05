package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.domain.player.Players;
import chess.domain.player.Scores;
import chess.domain.player.Turn;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChessGame {
    private final Players players;
    private final Board board;

    private Turn turn;
    private boolean isGameEnd;

    private ChessGame(final Board board, final Turn turn) {
        this.board = board;
        this.players = Players.init(board);
        this.turn = turn;
        this.isGameEnd = players.anyKingDead(board);
    }

    public static ChessGame initNew() {
        return new ChessGame(BoardInitializer.initiateBoard(), Turn.WHITE);
    }

    public static ChessGame load(final Board board, final Turn turn) {
        return new ChessGame(board, turn);
    }

    public void move(final Position source, final Position target) {
        validateTurn(source);
        board.movePiece(source, target);
        players.move(source, target);
        isGameEnd = players.anyKingDead(board);
        changeTurn();
    }

    public void validateTurn(final Position source) {
        turn.validate(players.ownerOf(source));
    }

    public void changeTurn() {
        turn = turn.change();
    }

    public Scores scores() {
        return players.scores(board);
    }

    public List<String> reachablePositions(final Position source) {
        if (turn.is(players.ownerOf(source))) {
            return board.reachablePositions(source).stream()
                    .map(position -> position.parseAsString())
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    public Board board() {
        return board;
    }

    public String[][] unicodeBoard() {
        return board.parseUnicodeBoard();
    }

    public void setGameEnd() {
        isGameEnd = true;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public List<Owner> winner() {
        return scores().winner();
    }

    public double score(final Owner owner) {
        return scores().getValueOf(owner);
    }

    public Turn turn() {
        return turn;
    }
}
