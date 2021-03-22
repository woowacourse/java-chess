package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.Map;

public class Result {

    private final Board board;

    public Result(Board board) {
        this.board = board;
    }

    public Score calculateTotalScore(PieceColor color) {
        Map<Piece, Position> pieces = board.remainPieces(color);
        Map<Piece, Position> pawns = board.remainPawns(pieces);

        Score scoreWithoutPawns = calculateScoreWithoutPawn(pieces);
        Score pawnScore = calculatePawnScore(pawns);
        return scoreWithoutPawns.add(pawnScore);
    }

    private Score calculateScoreWithoutPawn(Map<Piece, Position> pieces) {
        return pieces.keySet()
                .stream()
                .filter(piece -> !piece.isPawn())
                .map(piece -> piece.score())
                .reduce(Score::add)
                .orElse(new Score(0))
                ;
    }

    private Score calculatePawnScore(Map<Piece, Position> pawns) {
        double sum = 0;
        for (Column column : Column.values()) {
            int count = (int) pawns.entrySet()
                    .stream()
                    .filter(pawn -> pawn.getValue().isOn(column))
                    .count();
            if (count <= 2 && count != 0) {
                sum += 1;
            }
            if (count > 2) {
                sum += count * 0.5;
            }
        }
        return new Score(sum);
    }

    public String findWinner() {
        if (!board.kingDead()) {
            throw new IllegalArgumentException("아직 승자가 정해지지 않았습니다.");
        }
        return board.getCoordinates()
                .keySet()
                .stream()
                .filter(Piece::isKing)
                .map(piece -> piece.color() + "이 이겼습니다.")
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("우승자가 없습니다."))
                ;
    }

}
