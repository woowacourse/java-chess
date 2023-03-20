package chess.view.request;

import chess.controller.request.MoveRequestType;
import java.util.List;

public final class MoveRequest extends MoveRequestType {

    private static final int ORIGIN_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;

    public MoveRequest(List<String> input) {
        super(input.get(ORIGIN_INDEX), input.get(DESTINATION_INDEX));
    }
}
