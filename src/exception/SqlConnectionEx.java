package exception;

public class SqlConnectionEx extends RuntimeException {
    public SqlConnectionEx() {}
    public SqlConnectionEx(String message) {
        super(message);
    }
}
