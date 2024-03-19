package domain;

import java.util.Objects;

public final class File {

    private final int value;

    public File(int value) {
        this.value = value;
    }

    public int subtract(File otherFile) {
        return this.value - otherFile.value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (File) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "File[" +
            "value=" + value + ']';
    }

}
