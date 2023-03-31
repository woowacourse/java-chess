package domain.path.pathValidtor;

import domain.path.Path;
import domain.path.pathValidtor.validateTarget.ValidateTarget;
import java.util.List;

public final class PathValidator {

    private final List<ValidateTarget> targets;

    public PathValidator(final List<ValidateTarget> targets) {
        this.targets = targets;
    }

    public void validate(final Path path) {
        for (ValidateTarget target : targets) {
            target.validate(path);
        }
    }
}
