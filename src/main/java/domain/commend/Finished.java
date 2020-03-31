package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import domain.pieces.Pieces;
import domain.team.Team;

public class Finished extends GameState {

    public Finished(Pieces pieces) {
        super(pieces);
    }

    @Override
    public StateStrategy start() {
        throw new CommendTypeException("Start 를 할 수 없습니다.");
    }

    @Override
    public StateStrategy end() {
        throw new CommendTypeException("이미 끝났습니다.");
    }

    @Override
    public StateStrategy move(Team team, String input) {
        throw new CommendTypeException("끝난 상태에서 움직일 수 없습니다.");
    }

    @Override
    public StateStrategy status() {
        throw new CommendTypeException("끝난 상태에서 점수를 볼 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
