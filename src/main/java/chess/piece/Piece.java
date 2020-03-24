package chess.piece;

public interface Piece {
    /*
    생성자 VS 정적 팩토리 메서드 (정적 팩토리 메서드 승)
    근거 : 상태 변화가 없는 경우 싱글톤 가능하다.
    */
    Piece of(final String name, final boolean team);
}
