It seems like you've provided a detailed set of requirements for a mobile application that involves guessing countries based on their flags. This task involves various components such as displaying flags, allowing users to guess countries in different ways, tracking scores, and handling rotation changes. Here's a breakdown of the tasks:

1. **Initial Screen**:
   - The application starts with four buttons: Guess the Country, Guess-Hints, Guess the Flag, and Advanced Level.

2. **Guess the Country**:
   - Display a random flag image and a list of country names.
   - User selects a country and submits the guess.
   - Display feedback (CORRECT! or WRONG!) in different colors.
   - Change the "Submit" button label to "Next" for advancing to the next screen.

3. **Guess-Hints**:
   - Display a random flag image with dashes representing the country name.
   - User types a character to guess the country name.
   - Provide feedback for correct and incorrect guesses.
   - Allow up to 3 incorrect guesses before showing the correct answer.
   - Change the "Submit" button label to "Next" after completing the round.

4. **Guess the Flag**:
   - Display three random flag images and one correct country name.
   - User selects the flag corresponding to the given country name.
   - Provide feedback for correct and incorrect guesses.
   - Change the "Next" button label to advance to the next round.

5. **Advanced Level**:
   - Display three random flag images and three textboxes for guessing the country names.
   - User types country names in the textboxes and submits the guesses.
   - Provide feedback for correct and incorrect guesses.
   - Allow up to 3 incorrect attempts before showing the correct answers.
   - Change the "Submit" button label to "Next" after completing the round.
   - Implement a scoring system based on correct guesses.

6. **Handling Screen Rotation**:
   - Ensure the application resumes from the same point after device rotation.
   - Preserve the current screen and data without disabling Activity recreation.

These tasks cover the core functionalities and user interactions described in your requirements. Each task involves specific user interactions, feedback mechanisms, and UI changes based on user actions. Additionally, handling screen rotation ensures a seamless user experience across different device orientations.