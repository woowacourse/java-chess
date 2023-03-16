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

    public boolean notValidate(ValidateType validateType) {
        return !validateTypes.contains(validateType);
    }
}
