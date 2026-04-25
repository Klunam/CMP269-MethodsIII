# CMP 269: Programming Methods III
# Python

import random


def exercises_1_basic():
  
  course_name = "CMP269"
  
  number_of_students = 20
  
  print(f"Welcome to {course_name}! There is a toltal of {number_of_students} enrolled in the class.")


def exercise_2_collections():
  colors = ['Blue','Red','Green','Purple','Orange','Yellow']
  colors.append('Grey')

  # Creating a dictinary

  grades = {
    "name": "ALice",
    "gpa": 4.00
  }
  print(f"Colors: {colors}")
  print(f"Dictionary: {grades}")




def exercise_3_logic():
  # Task: Iterate through a list and collect evens
    numbers = [random.randint(1, 100) for _ in range(10)]
    evens = []
    
    for number in numbers:
        if number % 2 == 0:
            evens.append(number)
            
    print(f"Original numbers: {numbers}")
    print(f"Evens list: {evens}")

if __name__ == "__main__":
    print("--- Exercise 1 ---")
    exercises_1_basic()
    print("\n--- Exercise 2 ---")
    exercise_2_collections()
    print("\n--- Exercise 3 ---")
    exercise_3_logic()
    
exercises_1_basic()
exercise_2_collections()
exercise_3_logic()

  




