package chess.domain;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

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

    public void movePiece(final String sourceInput, final String targetInput) {
        Square source = getSquare(sourceInput);
        Square target = getSquare(targetInput);

        validateSameTeam(source);
        board.move(source, target);
        changeTeam();
    }

    private Square getSquare(final String movePosition) {
        List<String> position = Arrays.asList(movePosition.split(""));
        validatePosition(position);

        File file = File.findFileBy(position.get(FILE_INDEX));
        Rank rank = Rank.findRankBy(position.get(RANK_INDEX));
        return Square.of(file, rank);
    }

    private void validatePosition(final List<String> position) {
        if (position.size() != POSITION_SIZE) {
            throw new IllegalArgumentException("source위치, target 위치는 알파벳(a~h)과 숫자(1~8)로 입력해주세요. 예) a1");
        }
    }

    private void validateSameTeam(final Square source) {
        if (!board.isSameTeam(source, team)) {
            throw new IllegalArgumentException("다른 색 말을 움직여 주세요.");
        }
    }

    public double calculateScoreBy(Team team) {
        return board.calculateTotalScoreBy(team);
    }

    public List<String> determineWinningTeam() {
        if (calculateScoreBy(BLACK) > calculateScoreBy(WHITE)) {
            return List.of(BLACK.name());
        }
        if (calculateScoreBy(BLACK) < calculateScoreBy(WHITE)) {
            return List.of(WHITE.name());
        }
        return List.of(BLACK.name(), WHITE.name());
    }

    public boolean isCheckmate() {
        return board.countKing() < 2;
    }

    private void changeTeam() {
        team = Team.change(team);
    }

    public Board getBoard() {
        return board;
    }

    public Team team() {
        return team;
    }

    public String teamName() {
        return team.name();
    }
}
