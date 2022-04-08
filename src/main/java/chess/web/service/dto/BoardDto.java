package chess.web.service.dto;

import chess.board.Board;
import chess.board.piece.Piece;

import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {

    private final String turn;
    private final Map<String, String> board;
    private final boolean isFinish;

    public BoardDto(String turn, Map<String, String> board, boolean isFinish) {
        this.turn = turn;
        this.board = board;
        this.isFinish = isFinish;
    }

    public static BoardDto from(Board board) {
        Map<String, String> collect = board.getPieces().getPieces().stream()
                .collect(Collectors.toMap(
                        piece -> piece.getPosition().name(),
                        Piece::getName
                ));
        String turn = board.getTurn()
                .getTeam()
                .value();
        return new BoardDto(turn, collect, board.isDeadKing());
    }

    public Map<String, String> getBoard() {
        return board;
    }

    public String getTurn() {
        return turn;
    }
}
