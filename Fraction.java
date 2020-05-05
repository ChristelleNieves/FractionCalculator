// All code below was written by Christelle Nieves

public class Fraction {
    int numerator;
    int denominator;

    // Two-param constructor
    // Takes in a numerator and denominator
    // If denominator is 0, throws an exception
    public Fraction(int n, int d) {
        if (d == 0) {
            throw new IllegalArgumentException();
        } else if (d < 0) {
            // If denominator is negative, move the negative sign to the numerator
            this.numerator = -n;
            this.denominator = -d;
        } else {
            this.numerator = n;
            this.denominator = d;
        }
    }

    // One-param constructor
    // Represents a whole number
    // Sets the numerator to have a denominator of 1
    public Fraction(int n) {
        this.numerator = n;
        this.denominator = 1;
    }

    // Zero-param constructor
    // Sets fraction to zero (0/1)
    public Fraction() {
        this.numerator = 0;
        this.numerator = 1;
    }

    // Returns the numerator
    public int getNumerator() {
        return numerator;
    }

    // Returns the denominator
    public int getDenominator() {
        return this.denominator;
    }

    // Returns a string representation of the fraction in the form a/b
    public String toString() {
        // If the denominator is 1, just return the numerator
        if (this.denominator == 1) {
            return Integer.toString(this.numerator);
        }
        return (this.numerator + "/" + this.denominator);
    }

    // Returns the double representation of the fraction
    public double toDouble() {
        return (double) this.numerator / (double) this.denominator;
    }

    // Calculates the addition of two fractions
    public Fraction add(Fraction other) {
        int newNumerator = (this.denominator * other.numerator) + (this.numerator * other.denominator);
        int newDenominator = this.denominator * other.denominator;

        return new Fraction(newNumerator, newDenominator);
    }

    // Calculates the subtraction of two fractions
    public Fraction subtract(Fraction other) {
        int newNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);
        int newDenominator = this.denominator * other.denominator;

        return new Fraction(newNumerator, newDenominator);
    }

    // Calculates the product of two fractions
    public Fraction multiply(Fraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;

        return new Fraction(newNumerator, newDenominator);
    }

    // Calculates the quotient of two fractions
    public Fraction divide(Fraction other) {
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;

        return new Fraction(newNumerator, newDenominator);
    }

    // Returns true if two fractions are equal, false otherwise
    public boolean equals(Fraction other) {
        return this.denominator * other.numerator == this.numerator * other.denominator;
    }

    // Returns the GCD of two integers
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    // Reduces the fraction to lowest terms
    public void toLowestTerms() {
        int g = gcd(this.numerator, this.denominator);
        this.numerator /= g;
        this.denominator /= g;
    }
}