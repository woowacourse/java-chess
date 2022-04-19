package chess.dto.response;

import chess.turndecider.state.BlackTeam;
import chess.turndecider.state.State;
import chess.turndecider.state.WhiteTeam;

public class StateResponse {

    public static String from(State state) {
        if (state instanceof WhiteTeam) {
            return "white";
        }
        if (state instanceof BlackTeam) {
            return "black";
        }
        return "";
    }
}
