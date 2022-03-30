package chess.dto;

import chess.view.InputOption;

public class RequestDto {
    private InputOption inputOption;
    private String fromPosition;
    private String toPosition;

    private RequestDto(InputOption inputOption) {
        this.inputOption = inputOption;
    }
    private RequestDto(InputOption inputOption, String fromPosition, String toPosition) {
        this(inputOption);
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public static RequestDto of(InputOption inputOption, String fromPosition, String toPosition) {
        return new RequestDto(inputOption, fromPosition, toPosition);
    }

    public static RequestDto from(InputOption inputOption) {
        return new RequestDto(inputOption);
    }

    public InputOption getInputOption() {
        return inputOption;
    }

    public String getFromPosition() {
        return fromPosition;
    }

    public String getToPosition() {
        return toPosition;
    }
}
