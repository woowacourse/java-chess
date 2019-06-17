package chess.domain;

public class Point {

    private final int positionX;
    private final int positionY;

    public Point(char positionX, char positionY) {
        checkPositionX(positionX);
        checkPositionY(positionY);
        this.positionX = changeTypeX(positionX);
        this.positionY = changeTypeY(positionY);
    }

    private void checkPositionX(char positionX) {
        if (positionX < 'a' || positionX > 'h') {
            throw new IllegalArgumentException("X 좌표는 a부터 h까지만 허용합니다.");
        }
    }

    private void checkPositionY(char positionY) {
        if (positionY < '1' || positionY > '8') {
            throw new IllegalArgumentException("Y 좌표는 1부터 8까지만 허용합니다.");
        }
    }

    private int changeTypeX(char x){
        return x - 'a';
    }

    private int changeTypeY(char y){
        return y - '1';
    }
}
