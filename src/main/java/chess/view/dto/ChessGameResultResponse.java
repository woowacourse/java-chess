package chess.view.dto;

import chess.model.piece.Camp;
import chess.model.piece.score.PieceScore;

public class ChessGameResultResponse {

    private final PieceScore blackCampScore;
    private final PieceScore whiteCampScore;
    private final Camp winner;

    public ChessGameResultResponse(
            final PieceScore blackCampScore,
            final PieceScore whiteCampScore,
            final Camp winner
    ) {
        this.blackCampScore = blackCampScore;
        this.whiteCampScore = whiteCampScore;
        this.winner = winner;
    }

    public PieceScore getBlackCampScore() {
        return blackCampScore;
    }

    public PieceScore getWhiteCampScore() {
        return whiteCampScore;
    }

    public Camp getWinner() {
        return winner;
    }
}
