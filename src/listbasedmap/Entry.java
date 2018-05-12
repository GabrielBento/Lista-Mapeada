package listbasedmap;

public class Entry<E, T> {

    // O primeiro valor
    E first;
    // O segundo valor
    T second;

    public Entry(E f, T s) {
        first = f;
        second = s;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}
