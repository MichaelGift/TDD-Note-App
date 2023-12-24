# App Requirements

## User Stories and Acceptance Criteria

| Feature                    | User Story                                                     | Acceptance Criteria                                                                                                                                                                                                                           | Status |
|----------------------------|----------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|
| **Creating a Note**        | As a user, I want to be able to create a new note.             | - The user should see a "New Note" button.<br> - Clicking the "New Note" button should open a screen with fields for the note title and content. <br> - After entering the title and content, the user should be able to save the note.       | Done   |
| **Editing a Note**         | As a user, I want to be able to edit an existing note.         | - The user should be able to select an existing note for editing. <br> - The editing screen should display the current title and content of the selected note.  <br> - After making changes, the user should be able to save the edited note. | Done   |
| **Deleting a Note**        | As a user, I want to be able to delete a note.                 | - The user should be able to select and delete an existing note. <br> - Deleting a note should remove it from the list.                                                                                                                       | Done   |
| **Categorizing Notes**     | As a user, I want to categorize note for better organization.  | - The user should be able to assign categories or tags to each note. <br>  - There should be an option to filter notes based on categories                                                                                                    ||
| **Searching Notes**        | As a user, I want to quickly find specific notes.              | - The application should provide a search functionality<br> - Users should be able to search notes by titles,content or tags.                                                                                                                 ||
| **Adding Images to Notes** | As a user, I want to enrich my notes by adding images.         | - The note creation/editing screen should allow users to attach images to notes.<br> - Images should be displayed within the note.                                                                                                            ||
| **Setting Reminders**      | As a user, I want to set reminders for important notes.        | - Users should be able to set date and time reminders for each note. <br> - The application should notify users when a reminder is due.                                                                                                       ||
| **Sorting and Ordering**   | As a user, I want flexibility in sorting and ordering my notes | - Notes should be sortable by date, category or alphabetical order. <br> - The user should be able to customize the default sorting preference.                                                                                               ||
| **Markdown Support**       | As a user, I want to format my notes using Markdown            | - The note editor should support basic Markdown formatting. <br> - Formatted text in the notes should be displayed appropriately                                                                                                              ||

## Functional Requirements

- The application should support CRUD operations for notes (Create, Read, Update, Delete).
- A note should have a title and content.
- Users should be able to view a list of all created notes.
- The application should provide a clean and intuitive user interface.

## Non-functional Requirements

- The application should be responsive and work on different screen sizes.
- The user interface should follow material design principles.
- The application should handle data persistence reliably.

## Dependencies

- The application should be developed using Kotlin and the latest Android SDK.

## Assumptions and Constraints

- The minimum SDK is 26
- The target SDK is 34
