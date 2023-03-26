package chess.view;

import chess.dto.inputView.ReadCommandDto;
import chess.dto.outputView.PrintBoardDto;
import chess.dto.outputView.PrintEndMessageDto;
import chess.dto.outputView.PrintErrorMessageDto;
import chess.dto.outputView.PrintInitialMessageDto;
import chess.dto.outputView.PrintTotalScoreDto;
import chess.dto.outputView.PrintWinnerDto;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class IOViewResolver {

    private final Map<Class<?>, Supplier<Object>> inputViewMap = new HashMap<>();
    private final Map<Class<?>, Consumer<Object>> outputViewMap = new HashMap<>();

    public IOViewResolver(final InputView inputView, final OutputView outputView) {
        initInputViewMappings(inputView);
        initOutputViewMappings(outputView);
    }

    private void initInputViewMappings(final InputView inputView) {
        inputViewMap.put(ReadCommandDto.class, inputView::readCommand);
    }

    private void initOutputViewMappings(final OutputView outputView) {
        outputViewMap.put(PrintInitialMessageDto.class, dto -> outputView.printInitialMessage());
        outputViewMap.put(PrintErrorMessageDto.class, dto -> outputView.printErrorMessage((PrintErrorMessageDto) dto));
        outputViewMap.put(PrintBoardDto.class, dto -> outputView.printBoard((PrintBoardDto) dto));
        outputViewMap.put(PrintEndMessageDto.class, dto -> outputView.printEndMessage());
        outputViewMap.put(PrintTotalScoreDto.class, dto -> outputView.printTotalScore((PrintTotalScoreDto) dto));
        outputViewMap.put(PrintWinnerDto.class, dto -> outputView.printWinner((PrintWinnerDto) dto));
    }

    public <T> T inputViewResolve(final Class<T> type) {
        try {
            return type.cast(inputViewMap.get(type).get());
        } catch (NullPointerException e) {
            throw new NotFoundViewException();
        }
    }

    public void outputViewResolve(final Object dto) {
        try {
            outputViewMap.get(dto.getClass()).accept(dto);
        } catch (NullPointerException e) {
            throw new NotFoundViewException();
        }
    }
}
