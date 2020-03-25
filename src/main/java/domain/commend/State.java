package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import domain.team.Team;
import java.util.Arrays;

public class State {
    private Team turn;
    private CommendType commendType;

    public State() {
        this.turn = Team.WHITE;
        this.commendType = CommendType.START;
    }

    public void changeTurn() {
        this.turn = findTurn();
    }

    private Team findTurn() {
        if (this.turn == Team.BLACK) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public void changeCommend(String input) {
        commendType = findCommend(input);
    }

    private CommendType findCommend(String input) {
        return Arrays.stream(CommendType.values())
            .filter(type -> type.isCommend(input))
            .findFirst()
            .orElseThrow(() -> new CommendTypeException("잘못된 Commend 입력입니다."));
    }

    public boolean isEnd() {
        return commendType == CommendType.END;
    }

    public boolean isMove() {
        return commendType == CommendType.MOVE;
    }

    public Team getTurn() {
        return this.turn;
    }
}
