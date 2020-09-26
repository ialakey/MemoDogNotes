# MemoDog: Notes

#### Author: [I_Alakey](https://www.instagram.com/i_alakey)

#### MemoDog: Notes - the project was created as an example of regular notes. This is a simple application that has implemented the following:
  - Adding/editing and deleting notes
  - Sort by date/priority
  - Dark theme (MemoDog theme)

#### The app consists of 3 Windows:

  - The main window consists of a RecyclerView that displays all information about notes, a FloatingActionButton when you click on the button to add notes, as well as a button that POPs up when you click on the menu where you can choose how to sort notes and a settings button where you can find information about the application and change the theme.
  - Add/edit window consists of 2 EditText in which information is entered/displayed (Title and description), a button (date_button) when clicked, which displays the calendar, and RadioGroup, which indicates the priority of the task (from 1 to 4)
  - Settings window - consists of a Switch that switches the app theme, as well as a TextView that displays information about: developer, app version, share, and other apps. The window is designed using CardView and LinearLayout
