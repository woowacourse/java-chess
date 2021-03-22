package chess.domain.scorecalculator;

import chess.domain.Score;
import chess.domain.piece.Piece;
import java.util.List;

public interface ScoreCalculator {

    Score calculate(List<Piece> pieces);
}
