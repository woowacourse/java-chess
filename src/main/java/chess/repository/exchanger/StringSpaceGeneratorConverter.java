package chess.repository.exchanger;

public class StringSpaceGeneratorConverter implements ObjectConverter<StringSpaceGenerator, String> {

    @Override
    public StringSpaceGenerator convertToObject(String s) {
        return new StringSpaceGenerator(s);
    }

    @Override
    public String convertToData(StringSpaceGenerator stringSpaceGenerator) {
        return stringSpaceGenerator.getChessBoardStates();
    }
}
