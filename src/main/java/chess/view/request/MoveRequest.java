package chess.view.request;

import chess.controller.request.MoveRequestType;

public final class MoveRequest extends MoveRequestType {

    private static final int ORIGIN_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;

    public MoveRequest(String input) {
        super(input.split(" ")[ORIGIN_INDEX], input.split(" ")[DESTINATION_INDEX]);
    }
}
