#!/usr/bin/env python3
"""
Simple test for the Fibonacci function.
This demonstrates basic testing practices for the repository.
"""

def fibonacci_sequence(n):
    """Generate fibonacci sequence up to n numbers."""
    a, b = 0, 1
    sequence = []
    for _ in range(n):
        sequence.append(a)
        a, b = b, a + b
    return sequence

def test_fibonacci_sequence():
    """Test the fibonacci sequence generation."""
    # Test first few numbers
    result = fibonacci_sequence(5)
    expected = [0, 1, 1, 2, 3]
    assert result == expected, f"Expected {expected}, got {result}"
    
    # Test edge cases
    assert fibonacci_sequence(0) == []
    assert fibonacci_sequence(1) == [0]
    assert fibonacci_sequence(2) == [0, 1]
    
    # Test longer sequence
    result_10 = fibonacci_sequence(10)
    expected_10 = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
    assert result_10 == expected_10, f"Expected {expected_10}, got {result_10}"
    
    print("All tests passed!")

if __name__ == "__main__":
    test_fibonacci_sequence()