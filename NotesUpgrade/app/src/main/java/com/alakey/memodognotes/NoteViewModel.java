package com.alakey.memodognotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> priorityNotesAsc;
    private LiveData<List<Note>> priorityNotesDesc;
    private LiveData<List<Note>> dateNotesAsc;
    private LiveData<List<Note>> dateNotesDesc;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        priorityNotesAsc = repository.getNotesPriorityAsc();
        priorityNotesDesc = repository.getNotesPriorityDesc();
        dateNotesAsc = repository.getNotesDateAsc();
        dateNotesDesc = repository.getNotesDateDesc();
    }

    public LiveData<List<Note>> getNotesPriorityAsc() {
        return priorityNotesAsc;
    }

    public LiveData<List<Note>> getNotesPriorityDesc() {
        return priorityNotesDesc;
    }

    public LiveData<List<Note>> getNotesDateAsc() {
        return dateNotesAsc;
    }

    public LiveData<List<Note>> getNotesDateDesc() {
        return dateNotesDesc;
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

}