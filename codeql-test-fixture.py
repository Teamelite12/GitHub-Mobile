import os

def test_command_injection(user_input):
    os.system("echo " + user_input)

test_command_injection(input())
