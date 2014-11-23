package mesh.comparators;

public class Number implements Comparable<Number> {
    private int value;

    public Number(int value) { this.value = value; }
    public int compareTo(Number anotherInstance) {
        return this.value - anotherInstance.value;
    }
}