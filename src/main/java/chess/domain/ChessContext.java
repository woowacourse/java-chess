package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.judge.BasicJudge;
import chess.domain.judge.Judge;
import chess.domain.piece.Side;
import chess.exceptions.InvalidInputException;

public class ChessContext {
    private final Board board;
    private final Judge judge;
    private final Map<Side, String> players;
    private Side turn;

    public ChessContext(final Board board) {
        this.board = board;
        this.judge = new BasicJudge(board);
        this.turn = Side.WHITE;
        this.players = new HashMap<>();

        // TODO: 유저 만들기
        players.put(Side.WHITE, "손님");
        players.put(Side.BLACK, "호돌");
    }

    public List<String> findAllAvailablePath(String from) {
        return board.findAllAvailablePath(Position.of(from))
            .stream()
            .map(Position::toString)
            .collect(Collectors.toList());
    }

    public boolean isWhiteTurn() {
        return turn == Side.WHITE;
    }

    public boolean move(String from, String to) {
        try {
            board.move(Position.of(from), Position.of(to));
            turn = turn.changeTurn();
            return true;
        } catch (InvalidInputException e) {
            return false;
        }
    }

    public Board getBoard() {
        return board;
    }

    public Judge getJudge() {
        return judge;
    }

    public Map<Side, String> getPlayers() {
        return players;
    }
}
