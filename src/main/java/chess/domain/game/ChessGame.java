package chess.domain.game;

import static chess.domain.piece2.Color.BLACK;
import static chess.domain.piece2.Color.WHITE;

import chess.domain.piece2.Piece;
import chess.domain.position.Position;
import chess.dto.GameResultDto;
import chess.dto.MovePositionCommandDto;

public class ChessGame {

    private static final int ONGOING_GAME_KING_COUNT = 2;

    private final ActivePieces chessmen;

    public ChessGame() {
        this.chessmen = new ActivePieces();
    }

    public void moveChessmen(MovePositionCommandDto dto) {
        Piece sourcePiece = chessmen.findByPosition(sourcePositionOf(dto));
        Position targetPosition = targetPositionOf(dto);

        if (chessmen.isOccupied(targetPosition)) {
            attack(sourcePiece, chessmen.findByPosition(targetPosition));
            return;
        }
        sourcePiece.move(targetPosition, chessmen::isOccupied);
    }

    private Position sourcePositionOf(MovePositionCommandDto dto) {
        String source = dto.source();
        return Position.of(source);
    }

    private Position targetPositionOf(MovePositionCommandDto dto) {
        String target = dto.target();
        return Position.of(target);
    }

    private void attack(Piece sourcePiece, Piece targetPiece) {
        sourcePiece.kill(targetPiece, chessmen::isOccupied);
        chessmen.delete(targetPiece);
    }

    public boolean isEnd() {
        return chessmen.countKings() < ONGOING_GAME_KING_COUNT;
    }

    public GameResultDto getGameResult() {
        double whiteScore = chessmen.calculateScore(WHITE);
        double blackScore = chessmen.calculateScore(BLACK);

        return new GameResultDto(whiteScore, blackScore);
    }

    public ActivePieces getChessmen() {
        return chessmen;
    }

    @Override
    public String toString() {
        return "ChessGame{" + "chessmen=" + chessmen + '}';
    }
}
