package chess.repository.exchanger;

import chess.domain.chessGame.Turn;
import chess.domain.chessGame.TurnState;

public class TurnConverter implements ObjectConverter<Turn, String> {

    @Override
    public Turn convertToObject(String o) {
        return new Turn(TurnState.of(o));
    }

    @Override
    public String convertToData(Turn o) {
        return o.state();
    }
}
