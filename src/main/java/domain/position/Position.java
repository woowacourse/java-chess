package domain.position;

// TODO: 캐싱 기법 고민하기
public record Position(File file, Rank rank) {

    public int rowIndex() {
        return rank.getIndex();
    }

    public int columnIndex() {
        return file.getIndex();
    }
}
