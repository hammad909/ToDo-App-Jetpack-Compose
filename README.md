ToDo App
This is a simple ToDo app that allows users to add and delete tasks. The app is built using Jetpack Compose, MVI pattern, and Firebase for data storage.

Features
Add Tasks: Users can add tasks to their ToDo list.
Delete Tasks: Users can delete tasks when no longer needed.
Firebase Integration: Tasks are stored in Firebase for easy access and persistence.
Jetpack Compose: The app uses Jetpack Compose for modern UI development.
MVI Architecture: The app follows the Model-View-Intent (MVI) architecture for state management.
Tech Stack
Jetpack Compose: UI framework for building native Android UIs.
Firebase: Backend service for storing and managing tasks.
MVI: Architectural pattern used for state management and handling user actions.
Setup Instructions
Clone this repository:

bash
Copy
Edit
git clone https://github.com/your-username/todo-app.git
Open the project in Android Studio.

Ensure your project is connected to Firebase. Follow the steps in the Firebase documentation to set up Firebase for your app.

Sync the project and build.

Run the app on your emulator or physical device.

How it Works
Model: The Todo data model is used to store task details, such as the task description and its status (completed or not).
View: Jetpack Compose is used to display the task list, and to interact with users via buttons to add or delete tasks.
Intent: User interactions (such as adding or deleting tasks) are captured as intents, and the UI responds to these intents accordingly.
Firebase: The tasks are stored in Firebase Realtime Database, providing a backend to store and retrieve tasks.
Screenshots
![WhatsApp Image 2025-02-01 at 12 28 46 AM (1)](https://github.com/user-attachments/assets/0b882463-9388-4005-a984-2264464f6ae2)
![WhatsApp Image 2025-02-01 at 12 28 47 AM](https://github.com/user-attachments/assets/70959b80-be56-475e-9741-5a04997630f4)
![WhatsApp Image 2025-02-01 at 12 28 46 AM](https://github.com/user-attachments/assets/4075c2fb-1ac9-4db9-ae6e-6a8a696b071b)


Contributing
Feel free to fork this repository and contribute. To submit a pull request, ensure your code follows the project's coding style and passes all tests.
