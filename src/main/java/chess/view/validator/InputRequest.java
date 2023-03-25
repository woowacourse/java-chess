package chess.view.validator;

import java.util.List;

public class InputRequest {
    private final List<String> value;
    private final List<ValidateType> validateTypes;

    public InputRequest(List<ValidateType> validateTypes, List<String> input) {
        this.value = input;
        this.validateTypes = validateTypes;
    }

    public List<String> getValue() {
        return value;
    }

    public boolean contains(ValidateType validateType) {
        return validateTypes.contains(validateType);
    }

    public int size() {
        return this.value.get(0).length();
    }
}
