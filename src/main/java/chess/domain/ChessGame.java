package chess.domain;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.Arrays;
import java.util.List;

public class ChessGame {

    private static final int POSITION_SIZE = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final Board board;
    private Team team;

    public ChessGame(final Board board, final Team team) {
        this.board = board;
        this.team = team;
    }

    public void movePiece(String source, String destination) {
        Square src = getSquare(source);
        Square dst = getSquare(destination);

        validateSameTeam(src);
        board.move(src, dst);
        changeTeam();
    }

    private Square getSquare(final String movePosition) {
        List<String> position = Arrays.asList(movePosition.split(""));
        if (position.size() != POSITION_SIZE) {
            throw new IllegalArgumentException("source위치, target 위치는 알파벳(a~h)과 숫자(1~8)로 입력해주세요. 예) a1");
        }
        return Square.of(File.findFileBy(position.get(FILE_INDEX)), Rank.findRankBy(position.get(RANK_INDEX)));
    }


    private void validateSameTeam(final Square src) {
        if (!board.isSameColor(src, team)) {
            throw new IllegalArgumentException("다른 색 말을 움직여 주세요.");
        }
    }

    public void changeTeam() {
        team = Team.change(team);
    }

    public Board getBoard() {
        return board;
    }
}
