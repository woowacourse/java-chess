package chess.domain;

import chess.domain.piece.pawn.Pawn;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        Objects.requireNonNull(pieces, "pieces는 null이 들어올 수 없습니다.");
        this.pieces = new HashMap<>(pieces);
    }

    public static ChessBoard createNewChessBoard() {
        return new ChessBoard(PieceFactory.createNewChessBoard());
    }

    public void movePiece(Position start, Position target) {
        Piece movedPiece = pieceByPosition(start).move(start, target, this);
        pieces.remove(start);
        pieces.put(target, movedPiece);
    }

    public boolean isPositionEmpty(Position position) {
        return !pieces.containsKey(position);
    }

    public Piece pieceByPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new NoSuchElementException("해당 위치에 존재하는 기물이 없습니다.");
        }
        return pieces.get(position);
    }

    public Map<Color, Double> calcualteScoreStatus() {
        Map<Color, Double> result = new EnumMap<>(Color.class);
        for (Color color : result.keySet()) {
            result.put(color, calculateColorScore(color));
        }
        return result;
    }

    private double calculateColorScore(Color color) {
        return calculateColorDefaultScore(color) - 0.5 * 뭉치는 폰 갯수 계산;
    }

    private double calculateColorDefaultScore(Color color) {
        return pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    // position한테 col 리스를 받고,
    // List<Character> cols
    // stream
    // .filter(해당 col의 포지션인지)
    // .filter(동일한 색상인지)

    // count 다더해

    private int duplicatedPawnCount(Color color) {
        pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }

    // 일단 pawn목록을 추출한다.
    // Map<Position, Pawn> maps
    // 얘네를 stream을 돌리는데
    // 하나의 pawn이 maps에서 세로가 같은놈이 있는지 anyMaych
    // 이거의 count를 구하면
    // 중첩된게 있는 폰의 카운트들이 생성된다.

    //  pieces로부터 현재 플레이어의 pawn 목록을 추출한다.
    //  column (a~h)를 순차적으로 확인하며
    //  동일한 column을 지닌 pawn의 개수를 합산한다.
    private double countDuplicatedPawn(Color color) {
        List<Pawn> = pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color) && piece.isPawn())
                .collect(Collections.toList());


    }
}
