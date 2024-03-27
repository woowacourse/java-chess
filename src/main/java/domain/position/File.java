package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
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
    H(8),
    ;

    private final int order;

    File(int order) {
        this.order = order;
    }

    public int distance(File target) {
        return Math.abs(order - target.order);
    }

    public boolean isRight(File target) {
        return order - target.order < 0;
    }

    public boolean isLeft(File target) {
        return order - target.order > 0;
    }

    public List<File> betweenFiles(File target) {
        int sourceOrder = order;
        int targetOrder = target.order;
        if (sourceOrder < targetOrder) {
            return makeBetweenFiles(sourceOrder, targetOrder);
        }
        List<File> files = makeBetweenFiles(targetOrder, sourceOrder);
        Collections.reverse(files);
        return files;
    }

    private List<File> makeBetweenFiles(int from, int to) {
        return new ArrayList<>(Arrays.stream(values())
                .filter(file -> isBetween(file.order, from, to))
                .toList());
    }

    private boolean isBetween(int number, int from, int to) {
        return number > from && number < to;
    }
}
