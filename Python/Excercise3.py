# CMP 269 excersice 3


def task_1_append_logger():
    print("--- Task 1: Append Logger --- ")
    note = input("Enter a note for log")
    with open('session_log_txt', 'a') as file:
        file.write(note + '\n')

    with open('session_log.txt', 'a') as file:
        print("\n--- Session Log History ---- ")
        print(file.read())



def task_2_word_count_utility():
    print("\n--- Task 2: Word Count Utility ---")

    with open('lehman_motto.txt', 'w') as file:
        file.write("Knowledge is Power. Go Lightning! Python makes data easy.")

    with open('lehman_motto.txt', 'r') as file:
        content = file.read()
        word_count = len(content.split())
        print(f"Word count: {word_count}")


def task_3_api_status_checker():
    print("\n--- Task 3: API Status Checker ---")

    try:
        response = requests.get(
            'https://jsonplaceholder.typicode.com/posts/101',
            timeout=5
        )
        if response.status_code == 200:
            data = response.json()
            print("Post found:")
            print(json.dumps(data, indent=2))
        elif response.status_code == 404:
            print("Error: Post not found.")
        else:
            print(f"Unexpected status code: {response.status_code}")

    except requests.exceptions.Timeout:
        print("Error: The request timed out. Please try again later.")

def task_4_data_filtering():
    print("\n--- Task 4: Data Filtering ---")

    response = requests.get('https://jsonplaceholder.typicode.com/users')
    users = response.json()

    print("Users living in a Suite:")
    for user in users:
        if 'Suite' in user['address']['suite']:
            print(f"  - {user['name']}")


def task_5_integration_report():
    print("\n--- Task 5: Integration Report ---")

    response = requests.get('https://jsonplaceholder.typicode.com/posts/1')
    post = response.json()

    title = post['title']
    body = post['body']

    with open('api_report.txt', 'w') as file:
        file.write("=== API Report ===\n\n")
        file.write(f"TITLE:\n{title}\n\n")
        file.write(f"BODY:\n{body}\n")

    print("Report Generated")


if __name__ == "__main__":
    task_1_append_logger()
    task_2_word_count_utility()
    task_3_api_status_checker()
    task_4_data_filtering()
    task_5_integration_report()