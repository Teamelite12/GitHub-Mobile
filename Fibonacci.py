# BAD: Vulnerable to code injection
def run_user_code(user_input):
    eval(user_input)  # Vulnerable! Do not use eval on untrusted input.

user_input = input("Enter code to run: ")
run_user_code(user_input)
