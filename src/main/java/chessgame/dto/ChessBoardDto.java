package chessgame.dto;

public record ChessBoardDto(PiecesDto piecesDto) {
    public static final int VERTICAL_START_INDEX = 7;
    public static final int HORIZONTAL_END_INDEX = 8;
    private static final String EMPTY_POINT_VALUE = ".";

    public String findByPointIndex(int horizontal, int vertical) {
        PointDto findPoint = new PointDto(horizontal, vertical);
        return piecesDto.pieceDtos()
                .stream()
                .filter(dto -> dto.pointDto().equals(findPoint))
                .map(PieceDto::convertCaseSensitive)
                .map(PieceDto::pieceSymbol)
                .findFirst()
                .orElse(EMPTY_POINT_VALUE);
    }

}
