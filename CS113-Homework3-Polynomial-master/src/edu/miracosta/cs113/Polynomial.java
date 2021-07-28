package edu.miracosta.cs113;

import java.util.LinkedList;

public class Polynomial
{
    private final static char PLUS_SYMBOL = '+';

    // Class variables
    private LinkedList<Term> termList;

    /**
     * Default constructor, initializes termList to new empty linked list.
     */
    public Polynomial()
    {
        this.termList = new LinkedList();
    }

    public Polynomial(Polynomial polynomial)
    {
        this.termList = new LinkedList();

        if (polynomial != null)
        {
            for (int i = 0; i < polynomial.getNumTerms(); i++)
            {
                termList.add(new Term(polynomial.getTerm(i)));
            }
        }
    }

    public void add(Polynomial polynomial)
    {
        for (int i = 0; i < polynomial.getNumTerms(); i++)
        {
            this.addTerm(polynomial.getTerm(i));
        }
    }

    public void addTerm(Term termToAdd)
    {
        // local variable
        Term localTerm, currentTerm, nextTerm, sum;
        int lastIndex;

        // If list is empty, add term to index 0
        if (this.termList.size() == 0)
        {
            this.termList.add(termToAdd);
        }
        // else if only one element in list
        else if (this.termList.size() == 1)
        {
            localTerm = termList.get(0);
            if (termToAdd.compareTo(localTerm) == 1)
            {
                termList.add(0, termToAdd);
            }
            else if (termToAdd.compareTo(localTerm) == 0)
            {

                sum = termToAdd.plus(localTerm);
                if (sum != null)
                {
                    termList.set(0, sum);
                }
                else
                {
                    termList.remove(0);
                }
            }
            else
            {
                termList.add(termToAdd);
            }

        }
        else  // there is more then 1 element in list
        {
            // loop up too last index
            for (int i = 0; i < this.getNumTerms() - 1; i++)
            {
                currentTerm = this.getTerm(i);
                nextTerm = this.getTerm(i + 1);

                // if term to add exponent bigger then current term's exponent
                if (termToAdd.compareTo(currentTerm) == 1)
                {
                    termList.add(i, termToAdd);
                    return;
                }

                // else if term to Add Exponent matches current term's exponent
                else if (termToAdd.compareTo(currentTerm) == 0)
                {
                    sum = termToAdd.plus(currentTerm);
                    if (sum != null)
                    {
                        termList.set(i, sum);
                    }
                    else
                    {
                        termList.remove(i);
                    }
                    return;
                }

                // if term to add exponent is smaller then current term exponent
                // and larger then the next term exponent
                if (termToAdd.compareTo(currentTerm) == -1
                        && termToAdd.compareTo(nextTerm) == 1)
                {
                    // Insert term in between current and next
                    termList.add(i + 1, termToAdd);
                    return;
                }
            }

            // reached last index
            lastIndex = this.getNumTerms() - 1;
            localTerm = this.getTerm(lastIndex);

            if (termToAdd.compareTo(localTerm) == 0)
            {
                sum = termToAdd.plus(localTerm);
                if (sum != null)
                {
                    termList.set(lastIndex, sum);
                }
                else
                {
                    termList.remove(lastIndex);
                }
            }
            else
            {
                termList.add(termToAdd);
            }
        }
    }

    public Term remove(int index)
    {
        if (index < 0 || index > this.getNumTerms() - 1)
        {
            return null;
        }
        else
        {
            Term temp = new Term(this.getTerm(index));
            this.termList.remove(index);
            return temp;
        }
    }

    public Term getTerm(int index)
    {
        return new Term(this.termList.get(index));
    }

    public void clear()
    {
        this.termList.clear();
    }
    public int getNumTerms()
    {
        return this.termList.size();
    }
    @Override
    public boolean equals(Object other)
    {
        if (other == null || other.getClass() != this.getClass())
        {
            return false;
        }
        else
        {
            Polynomial otherPolynomial = (Polynomial) other;
            if (this.getNumTerms() != ((Polynomial) other).getNumTerms())
            {
                return false;
            }
            else
            {
                for (int i = 0; i < this.getNumTerms(); i++)
                {
                    if (this.getTerm(i).getCoefficient() != otherPolynomial.getTerm(i).getCoefficient()
                            || this.getTerm(i).getExponent() != otherPolynomial.getTerm(i).getExponent())
                    {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    @Override
    public String toString()
    {
        String temp;

        if (this.termList == null || this.termList.size() == 0)
        {
            temp = "0";
        }
        else
        {
            temp = "";

            for (Term term : termList)
            {
                temp += term.toString();
            }

            if (temp.charAt(0) == PLUS_SYMBOL)
            {
                temp = temp.substring(1);
            }
        }

        return temp;
    }
}