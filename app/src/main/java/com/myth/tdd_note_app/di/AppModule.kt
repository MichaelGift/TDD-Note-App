package com.myth.tdd_note_app.di

import android.app.Application
import androidx.room.Room
import com.myth.tdd_note_app.data.data_source.NoteDatabase
import com.myth.tdd_note_app.data.repository.NoteRepositoryImpl
import com.myth.tdd_note_app.domain.repository.NoteRepository
import com.myth.tdd_note_app.domain.usecases.AddNotesUseCase
import com.myth.tdd_note_app.domain.usecases.DeleteNoteUseCase
import com.myth.tdd_note_app.domain.usecases.GetNoteByIdUseCase
import com.myth.tdd_note_app.domain.usecases.GetNotesUseCase
import com.myth.tdd_note_app.domain.usecases.SearchNoteUseCase
import com.myth.tdd_note_app.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNotesRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(repository: NoteRepository): UseCases {
        return UseCases(
            getAllNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNotesUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository),
            search = SearchNoteUseCase(repository)
        )
    }
}