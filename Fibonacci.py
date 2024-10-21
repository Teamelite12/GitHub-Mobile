# Function to generate Fibonacci series up to n terms
def fibonacci_series(n):
    fib_series = [0, 1]
    while len(fib_series) < n:
        next_term = fib_series[-1] + fib_series[-2]
        fib_series.append(next_term)
    return fib_series

# Number of terms
n_terms = 20

# Generate and display the Fibonacci series
fib_series = fibonacci_series(n_terms)
print("Fibonacci series up to", n_terms, "terms:")
for term in fib_series:
    print(term)