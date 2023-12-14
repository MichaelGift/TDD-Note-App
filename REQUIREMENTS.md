# App Requirements

## User Stories and Acceptance Criteria

| Feature | User Story | Acceptance Criteria |
|---------|------------|-------------|
| **Creating a Note** | As a user, I want to be able to create a new note. | - The user should see a "New Note" button.<br> - Clicking the "New Note" button should open a screen with fields for the note title and content. <br> - After entering the title and content, the user should be able to save the note. |
| **Editing a Note** | As a user, I want to be able to edit an existing note. | - The user should be able to select an existing note for editing. <br> - The editing screen should display the current title and content of the selected note.  <br> - After making changes, the user should be able to save the edited note. |
| **Deleting a Note** | As a user, I want to be able to delete a note. |  - The user should be able to select and delete an existing note. <br> - Deleting a note should remove it from the list. |


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
