package chess.domain.position;

public class FileDifference extends Difference {

    public FileDifference(File from, File to) {
        super(from.calculateDifferenceTo(to));
    }
}
