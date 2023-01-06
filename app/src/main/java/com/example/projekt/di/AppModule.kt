package com.example.projekt.di


import android.app.Application
import androidx.room.Room
import com.example.projekt.data.Database
import com.example.projekt.data.PlantRepository
import com.example.projekt.data.PlantRepositoryImpl
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
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "plant_db"
        ).build()
    }


    @Provides
    @Singleton
    fun providePlantRepository(db: Database): PlantRepository {
        return PlantRepositoryImpl(db.plantDao(), db.userPlantDao(), db.notificationDao())
    }

}

