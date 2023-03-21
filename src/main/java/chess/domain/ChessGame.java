package chess.domain;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.Arrays;
import java.util.List;

public class ChessGame {

    private static final int SQUARE_INPUT_LENGTH = 2;

    private final Board board;
    private Team team;

    public ChessGame(final Board board, final Team team) {
        this.board = board;
        this.team = team;
    }

    public void movePiece(final String source, final String destination) {
        Square src = getSquare(source);
        Square dst = getSquare(destination);

        validateSameTeam(src);
        board.move(src, dst);
        changeTeam();
    }

    private Square getSquare(final String moveSquare) {
        List<String> square = Arrays.asList(moveSquare.split(""));
        if (square.size() != SQUARE_INPUT_LENGTH) {
            throw new IllegalArgumentException("source위치, target 위치는 알파벳(a~h)과 숫자(1~8)로 입력해주세요. 예) a1");
        }
        return Square.of(File.findFileBy(square.get(0)), Rank.findRankBy(square.get(1)));
    }

    private void validateSameTeam(final Square src) {
        if (!board.isSameTeam(src, team)) {
            throw new IllegalArgumentException("다른 팀 말을 움직여 주세요.");
        }
    }

    private void changeTeam() {
        team = Team.change(team);
    }

    public Board getBoard() {
        return board;
    }
}
