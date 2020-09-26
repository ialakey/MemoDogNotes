package com.alakey.memodognotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    //Сортировка по приоритету высокий
    String filterPriorityAsc = "SELECT * FROM note_table ORDER BY priority ASC";
    //Сортировка по приоритету низкий
    String filterPriorityDesc = "SELECT * FROM note_table ORDER BY priority DESC";
    //Сортировка по дате высокая
    String filterDateASC = "SELECT * FROM note_table ORDER BY date ASC";
    //Сортировка по дате низкая
    String filterDateDesc = "SELECT * FROM note_table ORDER BY date DESC";

    @Query(filterPriorityAsc)
    LiveData<List<Note>> getNotesPriorityAsc();

    @Query(filterPriorityDesc)
    LiveData<List<Note>> getNotesPriorityDesc();

    @Query(filterDateASC)
    LiveData<List<Note>> getNotesDateAsc();

    @Query(filterDateDesc)
    LiveData<List<Note>> getNotesDateDesc();

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();
}
