package chessgame.domain;

import chessgame.domain.state.Ready;
import chessgame.domain.state.State;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final Board board;
    private State state;

    public Game() {
        this.state = new Ready();
        this.board = new Board(ChessBoardFactory.create());
    }

    public Board board() {
        return board;
    }

    public void setState(Command command) {
        state = state.run(command, board);
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public boolean isEndByKing(){
        if(board.isExistKing(Team.BLACK) && board.isExistKing(Team.WHITE)){
            return false;
        }
        return true;
    }

    public Team winTeam() {
        if(board.isExistKing(Team.BLACK)){
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public Map<Team, Double> scoreBoard(){
        Map<Team,Double> score = new HashMap<>();
        score.put(Team.BLACK,board.calculateScore(Team.BLACK));
        score.put(Team.WHITE,board.calculateScore(Team.WHITE));
        return score;
    }
}
