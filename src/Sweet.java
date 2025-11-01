import java.util.Objects;

/**
 * Abstract base class for all sweets (generalized class).
 * Defines the common properties and behaviors for all sweets
 * in the gift.
 */
public abstract class Sweet {

    private final String name;
    private final double weight; // in grams
    private final double sugarContent; // in grams
    private final double chocolateContent; // percentage (0.0 to 1.0)

    /**
     * Constructs a new Sweet.
     *
     * @param name             The name of the sweet (non-empty).
     * @param weight           The weight in grams (positive).
     * @param sugarContent     The sugar content in grams (non-negative).
     * @param chocolateContent The chocolate content percentage (0.0 to 1.0).
     * @throws IllegalArgumentException if any argument is invalid.
     */
    public Sweet(String name, double weight, double sugarContent, double chocolateContent) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must be non-empty.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        if (sugarContent < 0) {
            throw new IllegalArgumentException("Sugar content must be non-negative.");
        }
        if (chocolateContent < 0.0 || chocolateContent > 1.0) {
            throw new IllegalArgumentException("Chocolate content must be between 0.0 and 1.0.");
        }

        this.name = name;
        this.weight = weight;
        this.sugarContent = sugarContent;
        this.chocolateContent = chocolateContent;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getSugarContent() {
        return sugarContent;
    }

    public double getChocolateContent() {
        return chocolateContent;
    }

    /**
     * Returns a string representation of the sweet.
     * We will add more details in the subclasses.
     *
     * @return A formatted string with common sweet details.
     */
    @Override
    public String toString() {
        return String.format(
                "Name: %-20s | Weight: %5.1fg | Sugar: %4.1fg | Choco: %.0f%%",
                name, weight, sugarContent, chocolateContent * 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Sweet sweet = (Sweet) o;
        return Double.compare(sweet.weight, weight) == 0 &&
                Double.compare(sweet.sugarContent, sugarContent) == 0 &&
                Double.compare(sweet.chocolateContent, chocolateContent) == 0 &&
                name.equals(sweet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, sugarContent, chocolateContent);
    }
}