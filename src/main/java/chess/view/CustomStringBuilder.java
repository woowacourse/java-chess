package chess.view;

public class CustomStringBuilder {
    private final StringBuilder stringBuilder = new StringBuilder();

    public void appendLine(String content) {
        stringBuilder.append(content).append('\n');
    }

    public void append(String content) {
        stringBuilder.append(content);
    }

    public void print() {
        System.out.println(stringBuilder);
    }

    public void appendCell(String content) {
        stringBuilder.append(content).append(" ");
    }
}
