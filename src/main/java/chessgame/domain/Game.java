package chessgame.domain;

import chessgame.domain.state.Black;
import chessgame.domain.state.Ready;
import chessgame.domain.state.State;
import chessgame.domain.state.White;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final Board board;
    private State state;
    private State turn;
    private String name;

    public Game(Board board,String gameName) {
        this.state = new Ready();
        this.board = board;
        this.name = gameName;
    }

    public Board board() {
        return board;
    }

    public void setState(Command command) {
        state = state.run(command, board);

        if(!state.isEnd()){
            turn = state;
        }
    }

    public void setDbState(String team){
        if(team.equals("White")) {
            state = new White();
        }
        if(team.equals("Black")){
            state = new Black();
        }
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public boolean isEndByKing() {
        return !board.isExistKing(Team.BLACK) || !board.isExistKing(Team.WHITE);
    }

    public Team winTeam() {
        if (board.isExistKing(Team.BLACK)) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public Map<Team, Double> scoreBoard() {
        Map<Team, Double> score = new HashMap<>();
        score.put(Team.BLACK, board.calculateScore(Team.BLACK));
        score.put(Team.WHITE, board.calculateScore(Team.WHITE));
        return score;
    }

    public State getTurn(){
        return turn;
    }

    public String getName(){
        return name;
    }
}
