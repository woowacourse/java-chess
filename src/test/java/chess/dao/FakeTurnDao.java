package chess.dao;

public class FakeTurnDao implements TurnDaoInterface {

    private String team;

    @Override
    public void save(String team) {
        this.team = team;
    }

    @Override
    public String find() {
        return team;
    }

    @Override
    public void delete() {
        team = null;
    }

    @Override
    public void update(String nowTurn, String nextTurn) {
        if (team.equals(nowTurn)) {
            team = nextTurn;
        }
    }
}
