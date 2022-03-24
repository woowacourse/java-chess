package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ChessBoard {

    private final List<List<Piece>> board;
    private final Color turn;

    public ChessBoard() {
        this.board = IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> generatePieces())
                .collect(toList());
        this.turn = Color.WHITE;
    }

    private List<Piece> generatePieces() {
        return IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> new EmptyPiece())
                .collect(toList());
    }

    public void init() {
        for (BoardSetting boardSetting : BoardSetting.values()) {
            fillPieces(boardSetting);
        }
    }

    private void fillPieces(BoardSetting boardSetting) {
        Piece piece = boardSetting.getPiece();
        List<Position> positions = boardSetting.getPositions();

        for (Position position : positions) {
            fillPiece(piece, position);
        }
    }

    private void fillPiece(Piece piece, Position position) {
        board.get(position.getRankIndex()).set(position.getFileIndex(), piece);
    }

    public void move(String source, String target) {
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        Piece sourcePiece = findPiece(sourcePosition);
    }

    private Piece findPiece(Position sourcePosition) {
        int rankIndex = sourcePosition.getRankIndex();
        int fileIndex = sourcePosition.getFileIndex();
        Piece piece = board.get(rankIndex).get(fileIndex);

        if(piece.isEmpty()) {
            throw new IllegalArgumentException("source위치에 기물이 존재하지 않습니다.");
        }

        return piece;
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
