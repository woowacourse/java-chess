package chess.dao;

public class FakeStateDao implements StateDao {

    private String name;

    @Override
    public void save(String name) {
        this.name = name;
    }

    @Override
    public String find() {
        return name;
    }

    @Override
    public void delete() {
        name = null;
    }

    @Override
    public void update(String now, String next) {
        this.name = next;
    }
}
