package domain.position;

import java.util.Arrays;
import java.util.List;

public enum File {

    A, B, C, D, E, F, G, H;

    public int distance(File target) {
        List<File> files = Arrays.stream(values()).toList();
        int sourceIndex = files.indexOf(this);
        int targetIndex = files.indexOf(target);
        return Math.abs(sourceIndex - targetIndex);
    }

    public boolean isSame(File target) {
        return this == target;
    }
}
