package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum File {

    A, B, C, D, E, F, G, H;

    public int distance(File target) {
        List<File> files = Arrays.stream(values()).toList();
        int sourceIndex = files.indexOf(this);
        int targetIndex = files.indexOf(target);
        return Math.abs(sourceIndex - targetIndex);
    }


    public boolean isRight(File target) {
        return forwardDistance(target) < 0;
    }

    public boolean isLeft(File target) {
        return forwardDistance(target) > 0;
    }

    private int forwardDistance(File target) {
        List<File> files = Arrays.stream(values()).toList();
        int sourceIndex = files.indexOf(this);
        int targetIndex = files.indexOf(target);
        return sourceIndex - targetIndex;
    }

    public List<File> betweenFiles(File target) {
        List<File> files = Arrays.stream(values()).toList();
        int sourceIndex = files.indexOf(this);
        int targetIndex = files.indexOf(target);
        if (sourceIndex < targetIndex) {
            return files.subList(sourceIndex + 1, targetIndex);
        }
        List<File> betweenFiles = new ArrayList<>(files.subList(targetIndex + 1, sourceIndex));
        Collections.reverse(betweenFiles);
        return betweenFiles;
    }

    public boolean isSame(File target) {
        return this == target;
    }
}
