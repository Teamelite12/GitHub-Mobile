def run_user_code(user_input):
    eval(user_input)  # Vulnerable to code injection!

user_input = input("Enter code to run: ")
run_user_code(user_input)
