package chess.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import chess.controller.converter.StringPositionConverter;
import chess.controller.dto.RoundStatusDTO;
import chess.controller.dto.PieceDTO;
import chess.domain.ChessGame;
import chess.domain.ChessGameImpl;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class ChessController {

    private final StringPositionConverter stringPositionConverter;
    private ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
        this.stringPositionConverter = new StringPositionConverter();
    }

    public List<PieceDTO> startGame() {
        List<Piece> pieces = chessGame.pieces().asList();
        return pieces.stream()
            .map(PieceDTO::new)
            .collect(toList());
    }

    public RoundStatusDTO movablePositions() {
        List<Piece> pieces = chessGame.currentColorPieces();
        return new RoundStatusDTO(
            mapMovablePositions(pieces),
            chessGame.currentColor(),
            chessGame.gameResult(),
            chessGame.isChecked(),
            chessGame.isKingDead()
        );
    }

    private Map<String, List<String>> mapMovablePositions(List<Piece> pieces) {
        return pieces.stream()
            .collect(toMap(
                piece -> piece.currentPosition().columnAndRow(),
                piece -> piece.movablePositions()
                    .stream()
                    .map(Position::columnAndRow)
                    .collect(toList())
            ));
    }

    public void move(String currentPosition, String targetPosition) {
        Position current = stringPositionConverter.convert(currentPosition);
        Position target = stringPositionConverter.convert(targetPosition);
        chessGame.movePiece(current, target);
    }

    public void restart() {
        chessGame = ChessGameImpl.initialGame();
    }
}
