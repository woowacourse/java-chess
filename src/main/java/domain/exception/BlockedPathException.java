package domain.exception;

public class BlockedPathException extends RuntimeException {
    public BlockedPathException() {
        super("이동하려는 길 사이에 다른 장기말이 위치하고 있습니다.");
    }
}
