package chess.domain.location;

public class LocationCacheMissException extends RuntimeException {

    public LocationCacheMissException(int x, int y) {
        super(String.format("캐시에서 해당 위치를 찾을 수 없습니다.(x:%d, y:%d)", x, y));
    }
}
