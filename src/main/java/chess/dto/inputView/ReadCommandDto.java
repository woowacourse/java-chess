package chess.dto.inputView;

import java.util.List;

public final class ReadCommandDto {

    private final List<String> result;

    public ReadCommandDto(final List<String> result) {
        this.result = result;
    }

    public List<String> getResult() {
        return result;
    }
}
