package edu.miracosta.cs113;

public class Term implements Cloneable, Comparable
{

    // Static Final variables
    private final static char VARIABLE_SYMBOL = 'x';
    private final static char EXPONENT_SYMBOL = '^';
    private final static char PLUS_SYMBOL = '+';
    private final static char MINUS_SYMBOL = '-';

    // Class variables
    private int coefficient;
    private int exponent;

    /**
     * Default constructor for Term class, sets both coefficient and exponent to integer value 1.
     */
    public Term()
    {
        this.setAll(1, 1);
    }
    
    public Term(int coefficient, int exponent)
    {
        this.setAll(coefficient, exponent);
    }
    public Term(Term other)
    {
        if (other == null)
        {
            throw new NullPointerException();
        }
        else
        {
            this.setAll(other.getCoefficient(), other.getExponent());
        }
    }

    public Term(String term)
    {
        int coefficient, exponent;

        if (!term.isEmpty())
        {

            // Term contains variable
            if (term.contains(Character.toString(VARIABLE_SYMBOL)))
            {
                // Split string in between X variable
                String[] splitTerm = term.split(Character.toString(VARIABLE_SYMBOL));

                // Parse left side of variable X
                coefficient = parseCoefficientString(splitTerm[0]);

                // if right side of variable X is available
                if (splitTerm.length == 2)
                {
                    exponent = parseExponentString(splitTerm[1]);
                }
                else
                {
                    exponent = 1;
                }
            }
            else
            { // Term does not contain variable
                coefficient = parseCoefficientString(term);
                exponent = 0;
            }

        }
        else // String is empty assign both coefficient and exponent to zero
        {
            coefficient = exponent = 0;
        }

        this.setAll(coefficient, exponent);
    }
    
    private int parseCoefficientString(String str)
    {
        if (str.length() == 1 && str.charAt(0) == MINUS_SYMBOL)
        {
            return -1;
        }
        else if (str.length() == 1 && str.charAt(0) == PLUS_SYMBOL)
        {
            return 1;
        }
        else
        {
            return Integer.parseInt(str);
        }
    }

    private int parseExponentString(String str)
    {
        return Integer.parseInt(str.substring(1));
    }

    public void setCoefficient(int value)
    {
        this.coefficient = value;
    }
    
    public void setExponent(int value)
    {
        this.exponent = value;
    }

    public void setAll(int coefficient, int exponent)
    {
        this.setCoefficient(coefficient);
        this.setExponent(exponent);
    }

    public int getCoefficient()
    {
        return this.coefficient;
    }

    public int getExponent()
    {
        return this.exponent;
    }

    public Object clone()
    {
        try
        {
            return super.clone();
        } catch (CloneNotSupportedException e)
        {
            return null;
        }
    }

    public int compareTo(Object other)
    {
        Term otherTerm = (Term) other;
        if (this.exponent > otherTerm.getExponent())
        {
            return 1;
        }
        else if (this.exponent < otherTerm.getExponent())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    public boolean equals(Object other)
    {
        if (other == null || other.getClass() != this.getClass())
        {
            return false;
        }
        else
        {
            Term otherTerm = (Term) other;
            return this.coefficient == otherTerm.getCoefficient()
                    && this.exponent == otherTerm.getExponent();
        }
    }

    public String toString()
    {
        String temp = "";

        // Will not run if coefficient is zero
        if (this.coefficient != 0)
        {

            // Handles Coefficient
            if (coefficient > 0)
            { // Adds plus symbol where needed
                temp += PLUS_SYMBOL;
                if (coefficient > 1)
                {
                    temp += this.coefficient;
                }
            }
            else
            { // Add minus symbol where needed
                if (coefficient == -1)
                {
                    temp += MINUS_SYMBOL;
                }
                else
                {
                    temp += this.coefficient;
                }
            }
            // Handles Exponent
            if (this.exponent != 0)
            {
                temp += VARIABLE_SYMBOL;

                if (this.exponent > 1 || this.exponent < -1)
                {
                    temp += EXPONENT_SYMBOL + "" + this.exponent;
                }
            }
        }
        return temp;
    }

    public Term plus(Term other)
    {
        return sumOf(this, other);
    }

    public static Term sumOf(Term termA, Term termB)
    {
        Term temp = null;
        int coefficientSum;

        // if exponents match
        if (termA.exponent == termB.exponent)
        {
            // Add both coefficients
            coefficientSum = termA.coefficient + termB.coefficient;

            if (coefficientSum == 0)
            {
                return null;
            }
            else
            {
                temp = new Term(coefficientSum, termA.exponent);
            }

            //temp = new Term(termA.coefficient + termB.coefficient, termA.exponent);

        }

        return temp;
    }
}