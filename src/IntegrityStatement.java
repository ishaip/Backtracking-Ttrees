
public final class IntegrityStatement {
    public static String signature() {
        String names = "Ishai Picus and Itay Genkin"; // <- Fill in your names here!
        if (names.length() == 0) {
            throw new UnsupportedOperationException("You should sign here");
        }
        return names;
    }
}
