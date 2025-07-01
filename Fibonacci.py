def fibonacci(n):
    """Print the first n numbers in the Fibonacci sequence."""
    a, b = 0, 1
    for _ in range(n):
        print(a)
        a, b = b, a + b

if __name__ == "__main__":
    fibonacci(20)