package chess.console.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import chess.console.domain.CommandRequest;
import chess.console.view.input.InputView;
import chess.console.domain.Color;
import chess.console.domain.PieceCharacter;
import chess.console.domain.Position;
import chess.console.view.output.OutputView;
import chess.dto.ColorDto;
import chess.dto.PlayerDto;
import chess.dto.PlayerScoresDto;
import chess.dto.PlayersDto;
import chess.dto.PositionDto;

public class ChessView {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void printGameCommandGuide() {
        outputView.printGameCommandGuide();
    }

    public CommandRequest requestGameCommand() {
        return inputView.requestCommand();
    }

    public String requestPieceInitialToPromotion() {
        outputView.printPromotionGuide();
        return inputView.requestPieceInitialToPromotion();
    }

    public void printPlayerScores(final PlayerScoresDto playerScoresDto) {
        final Map<String, Double> playerScores = playerScoresDto.getPlayerScores();
        outputView.printPlayerScores(playerScores);
    }

    public void printChessBoard(final PlayersDto playersDto) {
        final Map<Position, String> chessBoard = new HashMap<>();

        final Map<ColorDto, PlayerDto> playerDtos = playersDto.getPlayerDtos();
        for (final Entry<ColorDto, PlayerDto> entry : playerDtos.entrySet()) {
            final ColorDto colorDto = entry.getKey();
            final PlayerDto playerDto = entry.getValue();
            final Map<Position, String> playerPieces = convertToPlayerPieces(
                    Color.from(colorDto.getColorName()), playerDto);
            chessBoard.putAll(playerPieces);
        }

        printChessBoard(chessBoard);
    }

    private Map<Position, String> convertToPlayerPieces(final Color color, final PlayerDto playerDto) {
        final Map<PositionDto, String> playerPieces = playerDto.getPieces();
        return playerPieces.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> new Position(entry.getKey().getColumn(), entry.getKey().getRow()),
                        entry -> color.convertCase(
                                PieceCharacter.convertToOutputCharacter(entry.getValue())
                        )
                ));
    }

    private void printChessBoard(final Map<Position, String> playerPieces) {
        outputView.printChessBoard(playerPieces);
        outputView.printEmptyLine();
    }
}
