package domain;

// TODO: 캐싱 기법 고민하기
public record Position(Rank rank, File file) {

    public int rowIndex() {
        return rank.getIndex();
    }

    public int columnIndex() {
        return file.getIndex();
    }
}
