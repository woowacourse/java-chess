package domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int order;

    File(int order) {
        this.order = order;
    }

    public int distance(File target) {
        return Math.abs(this.order - target.order);
    }

    public List<File> betweenFiles(File target) {
        List<File> files = List.of(File.values());
        int sourceOrder = this.order;
        int targetOrder = target.order;
        if (sourceOrder < targetOrder) {
            return files.subList(sourceOrder, targetOrder - 1);
        }
        List<File> betweenFiles = new ArrayList<>(files.subList(targetOrder, sourceOrder - 1));
        Collections.reverse(betweenFiles);
        return Collections.unmodifiableList(betweenFiles);
    }

    public boolean isSame(File target) {
        return this == target;
    }
}
