# App Requirements

## User Stories and Acceptance Criteria

| Feature                          | User Story                                                             | Acceptance Criteria                                                                                                                                                                                                                           | Status |
|----------------------------------|------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|
| **Creating a Note**              | As a user, I want to be able to create a new note.                     | - The user should see a "New Note" button.<br> - Clicking the "New Note" button should open a screen with fields for the note title and content. <br> - After entering the title and content, the user should be able to save the note.       | Done   |
| **Editing a Note**               | As a user, I want to be able to edit an existing note.                 | - The user should be able to select an existing note for editing. <br> - The editing screen should display the current title and content of the selected note.  <br> - After making changes, the user should be able to save the edited note. | Done   |
| **Deleting a Note**              | As a user, I want to be able to delete a note.                         | - The user should be able to select and delete an existing note. <br> - Deleting a note should remove it from the list.                                                                                                                       | Done   |
| **Categorizing Notes**           | As a user, I want to categorize note for better organization.          | - The user should be able to assign categories or tags to each note. <br>  - There should be an option to filter notes based on categories                                                                                                    ||
| **Searching Notes**              | As a user, I want to quickly find specific notes.                      | - The application should provide a search functionality<br> - Users should be able to search notes by titles,content or tags.                                                                                                                 ||
| **Adding Images to Notes**       | As a user, I want to enrich my notes by adding images.                 | - The note creation/editing screen should allow users to attach images to notes.<br> - Images should be displayed within the note.                                                                                                            ||
| **Setting Reminders**            | As a user, I want to set reminders for important notes.                | - Users should be able to set date and time reminders for each note. <br> - The application should notify users when a reminder is due.                                                                                                       ||
| **Sorting and Ordering**         | As a user, I want flexibility in sorting and ordering my notes         | - Notes should be sortable by date, category or alphabetical order. <br> - The user should be able to customize the default sorting preference.                                                                                               ||
| **Markdown Support**             | As a user, I want to format my notes using Markdown                    | - The note editor should support basic Markdown formatting. <br> - Formatted text in the notes should be displayed appropriately                                                                                                              ||
| **Attachments and File Support** | As a user, I want to attach files and documents to my notes            | - The note editor should allow user to attach files such as PDFs, documents or other file types<br> - Users should be able to view and download attachments within the notes.                                                                 ||
| **Voice Notes**                  | As a user, I want the ability to add voice recording to my notes.      | - The application should have a feature for recording and attaching voice notes to written notes. <br> - Users should be able to play back voice recording within the note.                                                                   ||
| **Collaborative Editing**        | As a user, I want to collaborate on notes with others.                 | - Notes can be shared with other users. <br> - Collaborators should be able to view and edit shared notes in real-time.                                                                                                                       ||
| **Version History**              | As a user, I want to track changes and access version history          | - The application should maintain a version history for each note.<br> - users should be able to revert to previous version of a note.                                                                                                        ||
| **Customizable Themes**          | As a user, I want to personalize the look and feel of the application. | - The application should provide a range of customizable themes. <br> - Users can choose different color schemes or create custom themes.                                                                                                     ||
| **Integration with Calendar**    | As a user, I want to link notes to specific calendar events            | - Users can associate notes with calendar events within the application. <br> - Clicking on a note should provide a link to the associated calendar event                                                                                     ||
| **Geotagging Support**           | As a user, I want to associate notes with specific locations           | - Users can geotag notes, linking them to specific geographic locations, <br> - The application should display a map view showing notes based on their location                                                                               ||
| **Export and Share Options**     | As a user, I want to export and share my notes in various formats.     | - Users can export notes as text files, PDFs, or other standard formats. <br> - The application provides sharing options for sending notes via email or other communication channels                                                          ||


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
